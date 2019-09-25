package io.nakong.opc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.*;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.Executors;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
public class TestItems {

    public static void main(String[] args) throws Exception {
        // 连接信息
        final ConnectionInformation ci = new ConnectionInformation();

         Properties p  = PropertiesLoaderUtils.loadAllProperties("server.properties");

        InputStream stream = TestItems.class.getClassLoader().getResourceAsStream("server.properties");
//        InputStream is  = TestItems.class.getResourceAsStream("server.properties");
//        Properties p = new Properties();
//        p.load(is);

        ci.setHost(p.getProperty("host"));         // 电脑IP
        ci.setDomain(p.getProperty("domain"));                  // 域，为空就行
        ci.setUser(p.getProperty("user"));             // 电脑上自己建好的用户名
        ci.setPassword(p.getProperty("password"));          // 用户名的密码
        ci.setClsid(p.getProperty("clsid")); // KEPServer的注册表ID，可以在“组件服务”里看到

        final String itemId = "Simulation Examples.Functions.Ramp2";    // 项的名字按实际，没有实际PLC，用的模拟器：simulator
        // 启动服务
        final Server server = new Server(ci, Executors.newSingleThreadScheduledExecutor());

        try {
            // 连接到服务
            server.connect();
            // add sync access, poll every 500 ms，启动一个同步的access用来读取地址上的值，线程池每500ms读值一次
            // 这个是用来循环读值的，只读一次值不用这样
            final AccessBase access = new SyncAccess(server, 500);
            access.addItem(itemId, new DataCallback() {
                @Override
                public void changed(Item item, ItemState itemState) {
                    int type = 0;
                    try {
                        type = itemState.getValue().getType();
                        if (type == JIVariant.VT_I4) {  // float 数据
                            float value = 0;
                            try {
                                value = itemState.getValue().getObjectAsInt();
                            } catch (JIException e) {
                                e.printStackTrace();
                            }
                            // 写数据库
                            System.out.println("-----float 类型值： " + value);
                        }
                    } catch (JIException e) {
                        e.printStackTrace();
                    }
                }
            });
            access.bind();
            Thread.sleep(1 * 1000);
            access.unbind();
        } catch (final JIException e) {
            System.out.println(String.format("%08X: %s", e.getErrorCode(), server.getErrorMessage(e.getErrorCode())));
        }
    }
}
