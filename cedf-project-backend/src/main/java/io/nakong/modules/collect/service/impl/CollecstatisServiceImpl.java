package io.nakong.modules.collect.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;

import io.nakong.modules.collect.dao.CollecstatisDao;
import io.nakong.modules.collect.entity.CollecstatisEntity;
import io.nakong.modules.collect.service.CollecstatisService;


@Service("collecstatisService")
public class CollecstatisServiceImpl extends ServiceImpl<CollecstatisDao, CollecstatisEntity> implements CollecstatisService {

    @Autowired
    CollecstatisDao  collecstatisDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CollecstatisEntity> page = this.selectPage(
                new Query<CollecstatisEntity>(params).getPage(),
                new EntityWrapper<CollecstatisEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CollecstatisEntity>   comparisonChartByDay(Integer compareType, String equipId, Date startDate, Date endDate) {
        return collecstatisDao.comparisonChartByDay(compareType, equipId, startDate, endDate);
    }

    @Override
    public List<CollecstatisEntity>   comparisonChartByWeek(Integer compareType, String equipId, Date startDate, Date endDate) {
        return collecstatisDao.comparisonChartByWeek(compareType, equipId, startDate, endDate);
    }
    @Override
    public List<CollecstatisEntity>   comparisonChartByMonth(Integer compareType, String equipId, Date startDate, Date endDate) {
        return collecstatisDao.comparisonChartByMonth(compareType, equipId, startDate, endDate);
    }
    @Override
    public List<CollecstatisEntity>   comparisonChartByYear(Integer compareType, String equipId, Date startDate, Date endDate) {
        return collecstatisDao.comparisonChartByYear(compareType, equipId, startDate, endDate);
    }
}
