package io.nakong.modules.collect.service;
import com.baomidou.mybatisplus.service.IService;
import io.nakong.common.utils.PageUtils;
import io.nakong.modules.collect.entity.CollecstatisEntity;

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

    List<CollecstatisEntity>   comparisonChartByDay(Integer compareType, String equipId, Date startDate, Date endDate);


    List<CollecstatisEntity>   comparisonChartByWeek(Integer compareType, String equipId, Date startDate, Date endDate);


    List<CollecstatisEntity>   comparisonChartByMonth(Integer compareType, String equipId, Date startDate, Date endDate);

    List<CollecstatisEntity>   comparisonChartByYear(Integer compareType, String equipId, Date startDate, Date endDate);
}

