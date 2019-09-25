package io.nakong.modules.collect.service.impl;

import io.nakong.common.utils.CacheUtils;
import io.nakong.modules.collect.entity.PipeEntity;
import io.nakong.modules.collect.service.PipeService;
import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.openscada.opc.lib.da.*;

import java.util.Date;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
public class CollectThread implements Runnable {

    AccessBase access;
    String item;
    int vt_type;
    PipeService pipeService;

    public CollectThread(PipeService pipeService, AccessBase access, String item, int vt_type) {
        this.access = access;
        this.item = item;
        this.vt_type = vt_type;
        this.pipeService = pipeService;
    }

    @Override
    public void run() {
        try {
            access.addItem(item, new DataCallback() {
                @Override
                public void changed(Item itemValue, ItemState itemState) {
                    try {
                        String value = read(itemState.getValue(), vt_type);
                        // TODO 查询数据库是否配置了该变量，数据添加后记录到cache 中
                        Integer equipId = CacheUtils.getValue(item);
                        // 写数据库
                       // System.out.println("-----获取到的值： " + value);
                        PipeEntity entity = new PipeEntity();
                        entity.setCollecTime(new Date());
                        entity.setCollecValue(value);
                        entity.setInsertTime(new Date());
                        entity.setEquipId(String.valueOf(equipId));
                        pipeService.insert(entity);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            access.bind();
        } catch (JIException e) {
            e.printStackTrace();
        } catch (AddFailedException e) {
            e.printStackTrace();
        }
        access.bind();
    }

    /**
     * 读变量的值 如果是short和int直接返回字符串； 如果是long类型的数组,返回数字内容间加点，对应long，数组，大小为6
     * 如果是float类型的数组,返回数字内容间加逗号，对应float，数组，大小为20
     */
    public static String read(JIVariant jIVariant, int type) {
        String result = "";
        try {
            //  int type = state.getValue().getType();
            // JIVariant.VT_UI4
            if (type == 1) {
                int value = jIVariant.getObjectAsInt();
                return value + "";
            } else if (type == 2) {
                //  JIVariant.VT_I2
                short value = jIVariant.getObjectAsShort();
                return value + "";
            } else if (type == 3) {
                // JIVariant.VT_UI4
                // long
                long value = jIVariant.getObjectAsLong();
                return value + "";
            } else if (type == 4) {
                // JIVariant.VT_I1
                // byte
                char value = jIVariant.getObjectAsChar();
                return value + "";
            }
        } catch (JIException e) {
            e.printStackTrace();
        }
        return result;
      }
    }
