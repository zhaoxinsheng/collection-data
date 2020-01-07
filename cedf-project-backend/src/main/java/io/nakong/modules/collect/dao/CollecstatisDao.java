package io.nakong.modules.collect.dao;

import com.baomidou.mybatisplus.plugins.Page;
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

    List<CollecstatisEntity>  comparisonChartByDay(@Param("compareType") Integer compareType, @Param("list")List<String> equipId, @Param("startDate")Date startDate,@Param("endDate") Date endDate);
    List<CollecstatisEntity>  comparisonChartByWeek(@Param("compareType")Integer compareType, @Param("list")List<String> equipId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
    List<CollecstatisEntity>  comparisonChartByMonth(@Param("compareType")Integer compareType, @Param("list")List<String> equipId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);
    List<CollecstatisEntity>  comparisonChartByYear(@Param("compareType")Integer compareType, @Param("list")List<String> equipId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);

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

    List<CompareDataEntity> pressDataPageList(io.nakong.common.page.Page<CompareDataEntity> page) ;

    List<CompareDataEntity> pipeDataPageList(io.nakong.common.page.Page<CompareDataEntity> page) ;

    List<CompareDataEntity> powerDataPageList(io.nakong.common.page.Page<CompareDataEntity> page) ;

    List<CompareDataEntity> ldDataPageList(io.nakong.common.page.Page<CompareDataEntity> page) ;

    List<CompareDataEntity> tempDataPageList(io.nakong.common.page.Page<CompareDataEntity> page) ;


    List<CompareDataEntity>  airCostChartByDay(@Param("compareType") Integer compareType, @Param("equipId")String[] equipId, @Param("startDate")Date startDate,@Param("endDate") Date endDate);
    List<CompareDataEntity>  airCostByWeek(@Param("compareType")Integer compareType, @Param("equipId")String[] equipId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
    List<CompareDataEntity>  airCostByMonth(@Param("compareType")Integer compareType, @Param("equipId")String[] equipId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);
    List<CompareDataEntity>  airCostByYear(@Param("compareType")Integer compareType, @Param("equipId")String[] equipId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);

    /**
     * 流量分页数据
     * @param startDate
     * @param endDate
     * @return
     */
    io.nakong.common.page.Page<CompareDataEntity>  sumPipePageList(@Param("startDate") Date startDate, @Param("endDate")Date endDate);
    List<CompareDataEntity> queryListPipePageList(io.nakong.common.page.Page<CompareDataEntity> page);

    // 气电比
    List<CompareDataEntity> powerRatePageList(io.nakong.common.page.Page<CompareDataEntity> pageParam) ;
    List<CompareDataEntity> powerAllRatePageList(io.nakong.common.page.Page<CompareDataEntity> pageParam) ;

    void dayPressStatisData();

    void dayPipeStatisData();

    void dayPowerStatisData();

    void dayTempStatisData();

    void dayPressLdStatisData();

    void weekPressStatisData();

    void monthPressStatisData();

    void yearPressStatisData();

    void deleteWeekPressStatisData();

    void deleteMonthPressStatisData();

    void deleteYearPressStatisData();

    CompareDataEntity getCurMonthCollectData(@Param("collectType") Integer collectType);

    CompareDataEntity getLastMonthCollectData(@Param("collectType") Integer collectType);

    CompareDataEntity getCurQuarCollectData(@Param("collectType") Integer collectType);

    CompareDataEntity getLastQuarCollectData(@Param("collectType") Integer collectType);

    CompareDataEntity getLastYearCollectData(@Param("collectType") Integer collectType);

    CompareDataEntity getCurYearCollectData(@Param("collectType") Integer collectType);
}
