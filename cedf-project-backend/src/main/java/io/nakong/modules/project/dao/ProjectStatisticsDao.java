package io.nakong.modules.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.nakong.common.utils.Query;
import io.nakong.modules.project.vo.ProjectCategory;
import io.nakong.modules.project.vo.ProjectStatisticsCategoryVo;

@Mapper
public interface ProjectStatisticsDao extends BaseMapper<ProjectStatisticsCategoryVo> {

	ProjectStatisticsCategoryVo getProjectStatisticsAmount(@Param("startDate")String startDate, @Param("endDate")String endDate,@Param("projectName")String projectName);

	List<ProjectCategory> getProjectStatisticsByCategory(@Param("startDate")String startDate, @Param("endDate")String endDate);

	int getProjectStatisticsByProjectNoCount(@Param("query")Query q);

	ProjectStatisticsCategoryVo getProjectStatisticsDonateAmount(@Param("query")Query q );

	List<ProjectCategory> getProjectStatisticsDonateAmountList(@Param("query")Query q);

	ProjectStatisticsCategoryVo getProjectStatisticsReceiveAmount(@Param("query")Query q);

	List<ProjectCategory> getProjectStatisticsReceiveAmountList(@Param("query")Query q);

	int getProjectStatisticsDonateAmountListCount(@Param("query")Query q);

	int getProjectStatisticsReceiveAmountListCount(@Param("query")Query q);

	List<ProjectCategory> getProjectStatisticsByProjectNoList(@Param("query")Query q);

	ProjectStatisticsCategoryVo getProjectStatisticsAmountByProjectNo(@Param("startDate")String startDate, @Param("endDate")String endDate,@Param("projectName")String projectNam);
}