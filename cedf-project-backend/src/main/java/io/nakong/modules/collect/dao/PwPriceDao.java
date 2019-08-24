package io.nakong.modules.collect.dao;

import io.nakong.modules.collect.entity.PwPriceEntity;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2019-05-29 21:58:32
 */
@Mapper
public interface PwPriceDao extends BaseMapper<PwPriceEntity> {

      PwPriceEntity   getBasePriceByDatePriod(@Param("startDate") Date startDate, @Param("endDate")Date endDate);
	
}
