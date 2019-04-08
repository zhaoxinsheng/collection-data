package io.nakong.modules.project.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.project.dao.DonateAmountDao;
import io.nakong.modules.project.dao.ProjectDao;
import io.nakong.modules.project.entity.DonateAmountEntity;
import io.nakong.modules.project.entity.ProjectEntity;
import io.nakong.modules.project.service.DonateAmountService;


@Service("donateAmountService")
public class DonateAmountServiceImpl extends ServiceImpl<DonateAmountDao, DonateAmountEntity> implements DonateAmountService {
    @Autowired
    private DonateAmountDao donateAmountDao;
    
    @Autowired
    ProjectDao  projectDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) throws Exception{
        String contractNo = (String)params.get("contractNo");
        if (StringUtils.isNotEmpty(contractNo)) {
            params.put("contractNo", "%"+contractNo+"%");
        }

        String donator = (String) params.get("donator");
        if (StringUtils.isNotEmpty(donator)) {
            params.put("donator", "%"+donator+"%");
        }

        String startDateStr = (String) params.get("startDate");
        if (StringUtils.isNotEmpty(startDateStr)) {
            Date startDate = DateUtils.parseDate((String) params.get("startDate"), new String[] { "yyyy-MM-dd" });
            params.put("startDate", startDate);
        } else {
            params.put("startDate", null);
        }

        String endDateStr = (String) params.get("endDate");
        if (StringUtils.isNotEmpty(endDateStr)) {
            Date endDate = DateUtils.parseDate((String) params.get("endDate"), new String[] { "yyyy-MM-dd" });
            params.put("endDate", endDate);
        } else {
            params.put("endDate", null);
        }

        Query q = new Query(params);
        List<DonateAmountEntity> donateAmountList = donateAmountDao.queryListByCriteria(q);
        
    	if ( !CollectionUtils.isEmpty(donateAmountList)) {
			for (DonateAmountEntity project : donateAmountList) {
				ProjectEntity projectEntity = projectDao.queryProjectById(project.getProjectId());
				if (projectEntity != null) {
					project.setProjectName(projectEntity.getProjectName());
				}
			}
		}
    	
        Long donateAmountCount = donateAmountDao.countByCriteria(q);
        // 设置数据部分
        q.getPage().setRecords(donateAmountList);
        // 设置总条数
        q.getPage().setTotal(donateAmountCount.intValue());

        return new PageUtils(q.getPage());
    }

    public void updateByProjectId(Integer projectId,String donator){
        donateAmountDao.updateByProjectId(projectId,donator);
    }

}
