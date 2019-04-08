package io.nakong.modules.project.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import io.nakong.common.utils.Query;
import io.nakong.modules.project.vo.ProjectCategory;
import io.nakong.modules.project.vo.ProjectStatisticsCategoryVo;

public interface ProjectStatisticsService extends IService<ProjectStatisticsCategoryVo> {

	ProjectStatisticsCategoryVo getProjectStatisticsAmount(String startDate, String endDate,String projectName);

	List<ProjectCategory> getProjectStatisticsByCategory(String startDate, String endDate); 
	
	ProjectStatisticsCategoryVo getProjectStatisticsDonateAmount(Query q);

	List<ProjectCategory> getProjectStatisticsDonateAmountList(Query q);
	

	ProjectStatisticsCategoryVo getProjectStatisticsReceiveAmount(Query q);

	List<ProjectCategory> getProjectStatisticsReceiveAmountList(Query q);
 
	public int getProjectStatisticsDonateAmountListCount(Query q);
	 
	public int getProjectStatisticsReceiveAmountListCount(Query q);

	List<ProjectCategory> getProjectStatisticsByProjectNoList(Query q);

	int getProjectStatisticsByProjectNoCount(Query q);

	ProjectStatisticsCategoryVo getProjectStatisticsAmountByProjectNo(String startDate, String endDate,String projectName);
   
}
