package io.nakong.modules.collect.service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import io.nakong.common.utils.PageUtils;
import io.nakong.modules.collect.entity.CollecstatisEntity;
import io.nakong.modules.collect.entity.CompareDataEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@cowain.com.cn
 * @date 2019-08-18 21:50:18
 */
public interface CollecstatisService extends IService<CollecstatisEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CollecstatisEntity>   comparisonChartByDay(Integer compareType, List<String> equipId, Date startDate, Date endDate);


    List<CollecstatisEntity>   comparisonChartByWeek(Integer compareType, List<String> equipId, Date startDate, Date endDate);


    List<CollecstatisEntity>   comparisonChartByMonth(Integer compareType, List<String> equipId, Date startDate, Date endDate);

    List<CollecstatisEntity>   comparisonChartByYear(Integer compareType, List<String> equipId, Date startDate, Date endDate);

    CompareDataEntity getCollectData(Date startDate, Date endDate, Integer collectType);

    List<CompareDataEntity> getAirCollectData(Date startDate, Date endDate);

    List<CompareDataEntity> getAirPowerCollectData(Date startDate, Date endDate);


    List<CompareDataEntity> getPowerCollectData(Date startDate, Date endDate);

    List<CompareDataEntity> alarmData(Date startDate, Date endDate);



    List<CompareDataEntity> getSumAirData(Date startDate, Date endDate);

    List<CompareDataEntity> sumPowerData(Date startDate, Date endDate);

    List<CompareDataEntity> sumPipeData(Date startDate, Date endDate);

    /**
     * 分页处理
     * @return
     */
    List<CompareDataEntity>  sumPipePageList(io.nakong.common.page.Page<CompareDataEntity> pageParam);

    List<CompareDataEntity> sumTempDataPageList(io.nakong.common.page.Page<CompareDataEntity> pageParam);

    List<CompareDataEntity> airPowerRate(Date startDate, Date endDate);

    List<CompareDataEntity> pressDataPageList(io.nakong.common.page.Page<CompareDataEntity> pageParam);

    List<CompareDataEntity> pipeDataPageList(io.nakong.common.page.Page<CompareDataEntity> pageParam);
    List<CompareDataEntity> powerDataPageList(io.nakong.common.page.Page<CompareDataEntity> pageParam);

    List<CompareDataEntity> ldDataPageList(io.nakong.common.page.Page<CompareDataEntity> pageParam);
    List<CompareDataEntity> tempDataPageList(io.nakong.common.page.Page<CompareDataEntity> pageParam);


    List<CompareDataEntity>   airCostChartByDay(Integer compareType,  String[] equidArray, Date startDate, Date endDate);


    List<CompareDataEntity>   airCostChartByWeek(Integer compareType,  String[] equidArray, Date startDate, Date endDate);


    List<CompareDataEntity>   airCostChartByMonth(Integer compareType, String[] equidArray, Date startDate, Date endDate);

    List<CompareDataEntity>   airCostChartByYear(Integer compareType, String[] equidArray, Date startDate, Date endDate);

    // 气电比 数据
    List<CompareDataEntity> powerRatePageList(io.nakong.common.page.Page<CompareDataEntity> pageParam);
    // 所有设备的气电比数据
    List<CompareDataEntity> powerAllRatePageList(io.nakong.common.page.Page<CompareDataEntity> pageParam);


    CompareDataEntity getCurMonthCollectData(Integer collectType);

    CompareDataEntity getLastMonthCollectData(Integer collectType);

    CompareDataEntity getCurQuarCollectData(Integer collectType);

    CompareDataEntity getLastQuarCollectData(Integer collectType);

    CompareDataEntity getLastYearCollectData(Integer collectType);

    CompareDataEntity getCurYearCollectData(Integer collectType);


    // 统计 每天 晚上 1点 统计
    int  dayStatisData() ;

    // 统计 每天 晚上 2点 周统计数据
    int  weekStatisData() ;

    // 统计 每天 晚上 3点 月统计数据
    int  monthStatisData() ;


    // 统计 每天 晚上  4点 年统计数据

    int  yearStatisData() ;

}

