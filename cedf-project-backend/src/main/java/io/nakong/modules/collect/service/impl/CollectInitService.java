package io.nakong.modules.collect.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import io.nakong.common.utils.CacheUtils;
import io.nakong.config.OpcConfig;
import io.nakong.modules.collect.entity.EquipColorEntity;
import io.nakong.modules.collect.entity.PipeEntity;
import io.nakong.modules.collect.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.openscada.opc.lib.common.AlreadyConnectedException;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.common.NotConnectedException;
import org.openscada.opc.lib.da.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
@Service
public class CollectInitService {

    protected Logger logger = LoggerFactory.getLogger(CollectInitService.class);

    @Autowired
    OpcConfig opcConfig;

    /**
     * 累计流量
     */
    @Autowired
    PipeService pipeService;

    @Autowired
    PipeSsService pipeSsService;

    @Autowired
    PipeLjService pipeLjService;


    @Autowired
    PressService pressService;

    @Autowired
    PressLdService  pressLdService;

    @Autowired
    TempService tempService;

    @Autowired
    PowerService  powerService;

    @Autowired
    EquipColorService  colorService;

    private static int period = 1000 * 60;

   @PostConstruct
    public void printProperties() {

//       初始化对象放到cache对象中
         initEquipColor();
        // 连接信息
        final ConnectionInformation ci = new ConnectionInformation();
        // 192.168.0.193
        ci.setHost(opcConfig.getHost());         // 电脑IP
        ci.setDomain("");                  // 域，为空就行
        ci.setUser(opcConfig.getUser());             // 电脑上自己建好的用户名
        ci.setPassword(opcConfig.getPassword());          // 用户名的密码
//        ci.setProgId("Kepware.KEPServerEX.V5");
        ci.setClsid(opcConfig.getClsid()); // KEPServer的注册表ID，可以在“组件服务”里看到
        final Server server = new Server(ci, Executors.newSingleThreadScheduledExecutor());
        String[] items = opcConfig.getItemDetail();
        IService service =  null;

        // 把变量名称 从数据库取出放到 cache中
        try {
            server.connect();
                if(ArrayUtils.isNotEmpty(items)){
                    for (String item : items) {
                        // 查询数据库对应的变量名 和设备关系
                        final AccessBase access = new SyncAccess(server, period);
                        String [] detail = item.split("-");
                        String paramName = detail[0];
                        Integer dataType = Integer.parseInt(detail[1]);
                        int serviceType = Integer.parseInt(detail[2]);
                        //     public CollectThread(IService insertService, AccessBase access, String item, int vt_type,int collecType) {
                        service = getServiceImpl(serviceType);
                        new Thread(new CollectThread(service, access, paramName,dataType,serviceType)).run();
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (JIException e) {
            e.printStackTrace();
        } catch (AlreadyConnectedException e) {
            e.printStackTrace();
        } catch (NotConnectedException e) {
            e.printStackTrace();
        } catch (DuplicateGroupException e) {
            e.printStackTrace();
        }

        // 开始设置 变量 来插入数据库
        // 插入累计流量 pipeService
        // 插入瞬时流量
        // 插入电压
        // 电量
        //

    }

    private void initEquipColor() {
        List<EquipColorEntity> equips =  colorService.selectAllRecord();
        if(CollectionUtils.isNotEmpty(equips)){
            for (EquipColorEntity ec : equips) {
                CacheUtils.setValue(ec.getVarname().split("-")[0],ec.getId());CacheUtils.getValue("BY_KYZ.G1.排气压力_空压机1-1-1");
            }
        }

    }
//    压力  1
//    瞬时流量 2
//    累积流量 3
//    电量 4
//    压力露点 5
//    温度 6
    private IService getServiceImpl(int serviceType) {
         switch (serviceType) {
             case 1:
                 return pressService;
             case 2:
                 return pipeService;
             case 3:
                 return pipeLjService;
             case 4:
                 return powerService;
             case 5:
                 return pressLdService;
             case 6:
                 return tempService;
             case 7:
                 return pipeSsService;
         }
         return null;
    }
}
