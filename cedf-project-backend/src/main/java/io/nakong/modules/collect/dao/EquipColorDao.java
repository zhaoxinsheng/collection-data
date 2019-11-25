package io.nakong.modules.collect.dao;


import io.nakong.modules.collect.entity.EquipColorEntity;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@cowain.com.cn
 * @date 2019-08-24 13:29:25
 */
@Mapper
public interface EquipColorDao extends BaseMapper<EquipColorEntity> {

    EquipColorEntity queryByCollectType(@Param("equipId") String equipId, @Param("collectType") int collectType);

    /**
     * 根据变量获取设备
     * @param varname
     * @return
     */
    EquipColorEntity  selectByVarName(@Param("varname") String varname);

    /**
     * 获取所有数据
     * @return
     */
    List<EquipColorEntity> selectAllRecord();
}
