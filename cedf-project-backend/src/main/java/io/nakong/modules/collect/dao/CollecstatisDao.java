package io.nakong.modules.collect.dao;

import io.nakong.modules.collect.entity.CollecstatisEntity;
import io.nakong.modules.collect.entity.CompareDataEntity;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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

    List<CollecstatisEntity>  comparisonChartByDay(@Param("compareType") Integer compareType, @Param("equipId")String equipId, @Param("startDate")Date startDate,@Param("endDate") Date endDate);
    List<CollecstatisEntity>  comparisonChartByWeek(@Param("compareType")Integer compareType, @Param("equipId")String equipId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
    List<CollecstatisEntity>  comparisonChartByMonth(@Param("compareType")Integer compareType, @Param("equipId")String equipId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);
    List<CollecstatisEntity>  comparisonChartByYear(@Param("compareType")Integer compareType, @Param("equipId")String equipId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);

    CompareDataEntity getCollectData(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("collectType")Integer collectType) ;

    List<CompareDataEntity> getAirCollectData(@Param("startDate") Date startDate,  @Param("endDate")Date endDate);

    List<CompareDataEntity> getAirPowerCollectData(@Param("startDate") Date startDate,  @Param("endDate")Date endDate);

    List<CompareDataEntity> getPowerCollectData(@Param("startDate") Date startDate,  @Param("endDate")Date endDate);

    List<CompareDataEntity> getSumAirData(@Param("startDate") Date startDate,  @Param("endDate")Date endDate);

    List<CompareDataEntity> sumPowerData(@Param("startDate") Date startDate,  @Param("endDate")Date endDate) ;

    List<CompareDataEntity> sumPipeData(@Param("startDate") Date startDate,  @Param("endDate")Date endDate);

    List<CompareDataEntity> sumTempData(@Param("startDate") Date startDate,  @Param("endDate")Date endDate);

    List<CompareDataEntity> alarmData(@Param("startDate") Date startDate,  @Param("endDate")Date endDate) ;

     List<CompareDataEntity> airPowerRate(@Param("startDate") Date startDate,  @Param("endDate")Date endDate) ;

    List<CompareDataEntity> pressData(@Param("startDate") Date startDate,  @Param("endDate")Date endDate) ;

    List<CompareDataEntity> pipeData(@Param("startDate") Date startDate,  @Param("endDate")Date endDate) ;

    List<CompareDataEntity> powerData(@Param("startDate") Date startDate,  @Param("endDate")Date endDate) ;

    List<CompareDataEntity> ldData(@Param("startDate") Date startDate,  @Param("endDate")Date endDate) ;

    List<CompareDataEntity> tempData(@Param("startDate") Date startDate,  @Param("endDate")Date endDate) ;


    List<CompareDataEntity>  airCostChartByDay(@Param("compareType") Integer compareType, @Param("equipId")String[] equipId, @Param("startDate")Date startDate,@Param("endDate") Date endDate);
    List<CompareDataEntity>  airCostByWeek(@Param("compareType")Integer compareType, @Param("equipId")String[] equipId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
    List<CompareDataEntity>  airCostByMonth(@Param("compareType")Integer compareType, @Param("equipId")String[] equipId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);
    List<CompareDataEntity>  airCostByYear(@Param("compareType")Integer compareType, @Param("equipId")String[] equipId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);


}
