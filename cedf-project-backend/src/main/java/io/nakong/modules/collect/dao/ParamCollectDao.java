package io.nakong.modules.collect.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.lettuce.core.dynamic.annotation.Param;
import io.nakong.modules.collect.entity.ParamCollectEntity;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-06-28 20:20:43
 */
@Mapper
public interface ParamCollectDao extends BaseMapper<ParamCollectEntity> {

	ParamCollectEntity queryByCollectTypeId(@Param("collectTypeId") String collectTypeId);
	
}
