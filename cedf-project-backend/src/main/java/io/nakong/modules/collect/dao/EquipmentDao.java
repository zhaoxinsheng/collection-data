package io.nakong.modules.collect.dao;

import io.nakong.modules.collect.entity.EquipmentEntity;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2019-05-29 21:58:32
 */
@Mapper
public interface EquipmentDao extends BaseMapper<EquipmentEntity> {

    public int insertReturnId(EquipmentEntity entity);
}
