package io.nakong.modules.collect.service.impl;

import io.nakong.modules.collect.entity.CompareDataEntity;
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
    @Override
   public  CompareDataEntity getCollectData(Date startDate, Date endDate, Integer collectType) {
       return  collecstatisDao.getCollectData(startDate, endDate, collectType);
   }

    @Override
    public List<CompareDataEntity> getAirCollectData(Date startDate, Date endDate) {
        return collecstatisDao.getAirCollectData(startDate, endDate);
    }

    @Override
    public List<CompareDataEntity> getAirPowerCollectData(Date startDate, Date endDate) {
        return collecstatisDao.getAirPowerCollectData(startDate, endDate);
    }

    @Override
    public List<CompareDataEntity> getPowerCollectData(Date startDate, Date endDate) {
        return collecstatisDao.getPowerCollectData(startDate, endDate);
    }

    @Override
    public List<CompareDataEntity> alarmData(Date startDate, Date endDate) {
        return collecstatisDao.alarmData(startDate, endDate);
    }

    @Override
    public List<CompareDataEntity> getSumAirData(Date startDate, Date endDate) {
       return   collecstatisDao.getSumAirData(startDate, endDate);
    }

    @Override
    public List<CompareDataEntity> sumPowerData(Date startDate, Date endDate) {
        return   collecstatisDao.sumPowerData(startDate, endDate);
    }

    @Override
    public List<CompareDataEntity> sumPipeData(Date startDate, Date endDate) {
        return   collecstatisDao.sumPipeData(startDate, endDate);
    }

//    @Override
//    public List<CompareDataEntity> sumTempData(Date startDate, Date endDate) {
//        return   collecstatisDao.sumTempData(startDate, endDate);
//    }

    @Override
    public List<CompareDataEntity> airPowerRate(Date startDate, Date endDate) {
        return   collecstatisDao.airPowerRate(startDate, endDate);
    }

    @Override
    public List<CompareDataEntity> pressDataPageList(io.nakong.common.page.Page<CompareDataEntity> pageParam) {
        return   collecstatisDao.pressDataPageList(pageParam);
    }

    @Override
    public List<CompareDataEntity> pipeDataPageList(io.nakong.common.page.Page<CompareDataEntity> pageParam) {
        return   collecstatisDao.pipeDataPageList(pageParam);
    }

    @Override
    public List<CompareDataEntity> powerDataPageList(io.nakong.common.page.Page<CompareDataEntity> pageParam) {
        return   collecstatisDao.powerDataPageList(pageParam);
    }

    @Override
    public List<CompareDataEntity> ldDataPageList(io.nakong.common.page.Page<CompareDataEntity> pageParam) {
        return   collecstatisDao.ldDataPageList(pageParam);
    }

    @Override
    public List<CompareDataEntity> tempDataPageList(io.nakong.common.page.Page<CompareDataEntity> pageParam) {
        return   collecstatisDao.tempDataPageList(pageParam);
    }

    @Override
    public List<CompareDataEntity> airCostChartByDay(Integer compareType,  String[] equidArray, Date startDate, Date endDate) {
        return   collecstatisDao.airCostChartByDay(compareType,equidArray,startDate,endDate);
    }

    @Override
    public List<CompareDataEntity> airCostChartByWeek(Integer compareType,  String[] equidArray , Date startDate, Date endDate) {
        return  collecstatisDao.airCostByWeek(compareType,equidArray,startDate,endDate);
    }

    @Override
    public List<CompareDataEntity> airCostChartByMonth(Integer compareType, String[] equidArray, Date startDate, Date endDate) {
        return  collecstatisDao.airCostByMonth(compareType,equidArray,startDate,endDate);
    }

    @Override
    public List<CompareDataEntity> airCostChartByYear(Integer compareType,  String[] equidArray, Date startDate, Date endDate) {
        return  collecstatisDao.airCostByYear(compareType,equidArray,startDate,endDate);
    }

    @Override
    public  List<CompareDataEntity> sumPipePageList(io.nakong.common.page.Page<CompareDataEntity> page) {
        return  collecstatisDao.queryListPipePageList(page);

    }

    @Override
    public List<CompareDataEntity> sumTempDataPageList(io.nakong.common.page.Page<CompareDataEntity> pageParam) {
        return null;
    }
}
