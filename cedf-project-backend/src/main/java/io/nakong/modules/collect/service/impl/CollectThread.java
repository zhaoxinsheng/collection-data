package io.nakong.modules.collect.service.impl;

import com.baomidou.mybatisplus.service.IService;
import io.nakong.common.utils.CacheUtils;
import io.nakong.modules.collect.entity.*;
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
    // 数据类型
    int vt_type;
    IService insertService;
    // 采集类型
    /**
        1	press	压力
        2	power	电流
        3	pipe	流量
        4	temp	温度

        8	pipess	瞬时流量
        9	pipelj	累积流量
        10	templd	露点
    **/
    int collecType;

    public CollectThread(IService insertService, AccessBase access, String item, int vt_type,int collecType) {
        this.access = access;
        this.item = item;
        this.vt_type = vt_type;
        this.insertService = insertService;
        this.collecType = collecType;
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
                        equipId = equipId == null ? 1 : equipId;
                        insertValue(value,equipId);
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

    private void insertValue(String value,Integer equipId) {
        // 写数据库
        /**
         * 1  压力
         * 2 流量
         * 3 累计流量
         * 4 电量
         * 5 温度
         * 6 瞬时流量
         * 7 逻辑状态
         */
        switch (collecType) {
            case 1:
                insertPressValue(value,equipId);
                break;
            case 2:
                insertPipeValue(value,equipId);
                break;
            case 3:
                insertLJllValue(value,equipId);
                break;
            case 4:
                insertPowerValue(value,equipId);
                break;
            case 5:
                insertPressLDValue(value,equipId);
                break;
            case 6:
                insertTempValue(value,equipId);
                break;
            case 7:
                insertSsllValue(value,equipId);
                break;
            case 8:
                insertEquipStatusValue(value,equipId);
                break;
        }
    }

    // 插入管道流量 数据
    private void insertPipeValue(String value,Integer equipId) {
        PipeEntity entity = new PipeEntity();
        entity.setCollecTime(new Date());
        entity.setCollecValue(value);
        entity.setInsertTime(new Date());
        entity.setEquipId(String.valueOf(equipId));
        insertService.insert(entity);
    }

    // 插入管道流量 数据
    private void insertEquipStatusValue(String value,Integer equipId) {
        EquipStatusEntity entity = new EquipStatusEntity();
        entity.setCollecTime(new Date());
        entity.setCollecValue(value);
        entity.setInsertTime(new Date());
        entity.setEquipId(String.valueOf(equipId));
        insertService.insert(entity);
    }


    // 插入累积管道流量 数据
//    private void insertPipeSumValue(String value,Integer equipId) {
//        PipeEntity entity = new PipeEntity();
//        entity.setCollecTime(new Date());
//        entity.setCollecValue(value);
//        entity.setInsertTime(new Date());
//        entity.setEquipId(String.valueOf(equipId));
//        insertService.insert(entity);
//    }

    // 插入压力数据
    private void insertPressValue(String value,Integer equipId) {
        PressEntity entity = new PressEntity();
        entity.setCollecTime(new Date());
        entity.setCollecValue(value);
        entity.setInsertTime(new Date());
        entity.setEquipId(String.valueOf(equipId));
        insertService.insert(entity);
    }
    // 插入压力露点数据
    private void insertPressLDValue(String value,Integer equipId) {
        PressLdEntity entity = new PressLdEntity();
        entity.setCollecTime(new Date());
        entity.setCollecValue(value);
        entity.setInsertTime(new Date());
        entity.setEquipId(String.valueOf(equipId));
        insertService.insert(entity);
    }

    // 插入电量数据
    private void insertPowerValue(String value,Integer equipId) {
        PowerEntity  entity = new PowerEntity();
        entity.setCollecTime(new Date());
        entity.setCollecValue(value);
        entity.setInsertTime(new Date());
        entity.setEquipId(String.valueOf(equipId));
        insertService.insert(entity);
    }

    // 插入温度数据
    private void insertTempValue(String value,Integer equipId) {
        TempEntity  entity  =  new TempEntity();
        entity.setCollecTime(new Date());
        entity.setCollecValue(value);
        entity.setInsertTime(new Date());
        entity.setEquipId(String.valueOf(equipId));
        insertService.insert(entity);
    }
    // 插入瞬时流量
    private void insertSsllValue(String value,Integer equipId) {
        PipeSsEntity entity = new PipeSsEntity();
        entity.setCollecTime(new Date());
        entity.setCollecValue(value);
        entity.setInsertTime(new Date());
        entity.setEquipId(String.valueOf(equipId));
        insertService.insert(entity);
    }
    // 插入累计流量
    private void insertLJllValue(String value,Integer equipId) {
        PipeLjEntity entity = new PipeLjEntity();
        entity.setCollecTime(new Date());
        entity.setCollecValue(value);
        entity.setInsertTime(new Date());
        entity.setEquipId(String.valueOf(equipId));
        insertService.insert(entity);
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
            // float 类型
            if (type == 1) {
                float value = jIVariant.getObjectAsFloat();
                return value + "";
            } else if (type == 2) {
                //  JIVariant.VT_I2
                short value = jIVariant.getObjectAsShort();
                return value + "";
            } else if (type == 3) {
                // JIVariant.VT_UI4bbx
                // long
               // long value = jIVariant.getType();//jIVariant.getObjectAsLong();
                int value = jIVariant.getObjectAsInt();//jIVariant.getObjectAsLong();
                 // tObjectAsLong();
                return value + "";
            } else if (type == 4) {
                // byte
                char value = jIVariant.getObjectAsChar();
                return value + "";
            } else if (type == 5) {
                // boolean
                boolean value = jIVariant.getObjectAsBoolean();
                return value? "1" : "0";
            }
        } catch (JIException e) {
            e.printStackTrace();
        }
        return result;
      }
    }
