package io.nakong.modules.collect.dao;

import io.nakong.modules.collect.entity.CollecstatisEntity;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@cowain.com.cn
 * @date 2019-08-18 21:50:18
 */
@Mapper
public interface CollecstatisDao extends BaseMapper<CollecstatisEntity> {

    List<CollecstatisEntity>  comparisonChartByDay(Integer compareType, String equipId, Date startDate, Date endDate);
    List<CollecstatisEntity>  comparisonChartByWeek(Integer compareType, String equipId, Date startDate, Date endDate);
    List<CollecstatisEntity>  comparisonChartByMonth(Integer compareType, String equipId, Date startDate, Date endDate);
    List<CollecstatisEntity>  comparisonChartByYear(Integer compareType, String equipId, Date startDate, Date endDate);


}
