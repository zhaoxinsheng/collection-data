package io.nakong.modules.collect.dao;


import io.nakong.modules.collect.entity.EquipColorEntity;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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
}
