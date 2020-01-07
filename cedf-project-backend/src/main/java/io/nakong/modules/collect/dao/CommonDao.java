package io.nakong.modules.collect.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.nakong.modules.collect.entity.CollecstatisEntity;
import io.nakong.modules.collect.entity.CommonEntity;
import io.nakong.modules.collect.entity.CommonSingleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tom
 */
@Mapper
public interface CommonDao   extends BaseMapper<CommonEntity> {

    List<CommonSingleEntity> getPressDayList(@Param("date") String dateStr);

    List<CommonSingleEntity> getPressMonthList(@Param("date") String dateStr);

    List<CommonSingleEntity> getPressYearList(@Param("date") String dateStr);

    List<CommonSingleEntity> getTempDayList(@Param("date") String dateStr);

    List<CommonSingleEntity> getTempMonthList(@Param("date") String dateStr);

    List<CommonSingleEntity> getTempYearList(@Param("date") String dateStr);

    List<CommonSingleEntity> getTempLdDayList(@Param("date") String dateStr);

    List<CommonSingleEntity> getTempLdMonthList(@Param("date") String dateStr);

    List<CommonSingleEntity> getTempLdYearList(@Param("date") String dateStr);
}
