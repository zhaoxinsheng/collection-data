package io.nakong.modules.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nakong.common.utils.Query;
import io.nakong.modules.project.dao.ProjectStatisticsDao;
import io.nakong.modules.project.service.ProjectStatisticsService;
import io.nakong.modules.project.vo.ProjectCategory;
import io.nakong.modules.project.vo.ProjectStatisticsCategoryVo;


@Service("projectStatisticsService")
public class ProjectStatisticsServiceImpl  extends ServiceImpl<ProjectStatisticsDao, ProjectStatisticsCategoryVo> implements ProjectStatisticsService{

	
	
	@Autowired
	private ProjectStatisticsDao projectStatisticsDao;
	
	@Override
	public ProjectStatisticsCategoryVo getProjectStatisticsAmount(String startDate, String endDate,String projectName) {
		return projectStatisticsDao.getProjectStatisticsAmount(startDate,endDate,projectName);
	}

	@Override
	public List<ProjectCategory> getProjectStatisticsByCategory(String startDate, String endDate) {
		return projectStatisticsDao.getProjectStatisticsByCategory(startDate,endDate);
	}

	@Override
	public List<ProjectCategory> getProjectStatisticsByProjectNoList(Query q) {
		return projectStatisticsDao.getProjectStatisticsByProjectNoList(q);
	}

	@Override
	public ProjectStatisticsCategoryVo getProjectStatisticsDonateAmount(Query q) {
		return projectStatisticsDao.getProjectStatisticsDonateAmount(q);
	}

	@Override
	public List<ProjectCategory> getProjectStatisticsDonateAmountList(Query q) {
	      return  projectStatisticsDao.getProjectStatisticsDonateAmountList(q);
	}
	
	@Override
	public int getProjectStatisticsDonateAmountListCount(Query q) {
	    return projectStatisticsDao.getProjectStatisticsDonateAmountListCount(q);
	}

	@Override
	public ProjectStatisticsCategoryVo getProjectStatisticsReceiveAmount(Query q) {
		return projectStatisticsDao.getProjectStatisticsReceiveAmount(q);
	}

	@Override
	public List<ProjectCategory> getProjectStatisticsReceiveAmountList(Query q) {
		return projectStatisticsDao.getProjectStatisticsReceiveAmountList(q);
	}
	
	@Override
	public int getProjectStatisticsReceiveAmountListCount(Query q) {
		return projectStatisticsDao.getProjectStatisticsReceiveAmountListCount(q);
	}


	@Override
	public ProjectStatisticsCategoryVo getProjectStatisticsAmountByProjectNo(String startDate, String endDate,
			String projectName) {
		return projectStatisticsDao.getProjectStatisticsAmountByProjectNo(startDate, endDate, projectName);
	}

	@Override
	public int getProjectStatisticsByProjectNoCount(Query q) {
		return  projectStatisticsDao.getProjectStatisticsByProjectNoCount(q);
	}
 
}
