package io.nakong.modules.project.dao;

import io.nakong.modules.project.entity.DonatorEntity;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:14
 */
@Mapper
public interface DonatorDao extends BaseMapper<DonatorEntity> {
	
}
