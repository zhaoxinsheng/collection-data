package io.nakong.modules.collect.service;

import com.baomidou.mybatisplus.service.IService;
import io.nakong.common.utils.PageUtils;
import io.nakong.modules.collect.entity.CommonEntity;

import java.util.Map;

/**
 * @author tom
 */
public interface LineService extends IService<CommonEntity> {

    PageUtils queryPage(Map<String, Object> params);

    CommonEntity getPressDayList( String dateStr);

    CommonEntity getPressMonthList( String dateStr);

    CommonEntity getPressYearList( String dateStr);

    CommonEntity getTempDayList( String dateStr);

    CommonEntity getTempMonthList( String dateStr);

    CommonEntity getTempYearList( String dateStr);

    CommonEntity getTempLdDayList( String dateStr);

    CommonEntity getTempLdMonthList( String dateStr);

    CommonEntity getTempLdYearList( String dateStr);

    CommonEntity getPipeDayList(String dateStr);

    CommonEntity getPipeMonthList(String dateStr);

    CommonEntity getPipeYearList(String dateStr);

    CommonEntity getPowerDayList(String dateStr);

    CommonEntity getPowerMonthList(String dateStr);

    CommonEntity getPowerYearList(String dateStr);
}

