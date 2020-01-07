package io.nakong.modules.collect.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.dao.CommonDao;
import io.nakong.modules.collect.dao.PipeLjDao;
import io.nakong.modules.collect.entity.CommonEntity;
import io.nakong.modules.collect.entity.CommonSingleEntity;
import io.nakong.modules.collect.entity.PipeLjEntity;
import io.nakong.modules.collect.service.LineService;
import io.nakong.modules.collect.service.PipeLjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author tom
 */
@Component
public class LineServiceImpl extends ServiceImpl<CommonDao, CommonEntity> implements LineService {

    @Autowired
    CommonDao  commonDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CommonEntity> page = this.selectPage(
                new Query<CommonEntity>(params).getPage(),
                new EntityWrapper<CommonEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public CommonEntity getPressDayList( String dateStr) {

//        List<CommonSingleEntity> entityList = commonDao.getPressDayList(dateStr);
//        if ( !CollectionUtils.isEmpty(entityList)) {
//            CommonEntity commonEntity = new CommonEntity();
//
//            entityList.stream().forEach(
//                    x->{
//                        commonEntity.getxDataList().add(x.getDateInt());
//                        commonEntity.getDataList().add(x.getValue());
//                        commonEntity.setTotalData(x.getValue() + (commonEntity.getTotalData() == null? 0 : commonEntity.getTotalData()) );
//                    }
//            );
//            long count = entityList.stream().count();
//            commonEntity.setAvgData(commonEntity.getTotalData() / count);
//            return commonEntity;
//        }
//        return null;

        return getRetList(commonDao.getPressDayList(dateStr));
    }

    @Override
    public CommonEntity getPressMonthList( String dateStr) {
        return getRetList(commonDao.getPressMonthList(dateStr));
    }

    @Override
    public CommonEntity getPressYearList( String dateStr)  {
        return getRetList(commonDao.getPressYearList(dateStr));
//        return commonDao.getPressYearList();
    }

    @Override
    public CommonEntity getTempDayList( String dateStr) {
        return getRetList(commonDao.getTempDayList(dateStr));
//        return commonDao.getTempDayList();
    }

    @Override
    public CommonEntity getTempMonthList( String dateStr) {
        return getRetList(commonDao.getTempMonthList(dateStr));
//        return commonDao.getTempMonthList();
    }

    @Override
    public CommonEntity getTempYearList( String dateStr) {
        return getRetList(commonDao.getTempYearList(dateStr));
//        return commonDao.getTempYearList();
    }

    @Override
    public CommonEntity getTempLdDayList( String dateStr) {
        return getRetList(commonDao.getTempLdDayList(dateStr));
//        return commonDao.getTempLdDayList();
    }

    @Override
    public CommonEntity getTempLdMonthList( String dateStr) {
        return getRetList(commonDao.getTempLdMonthList(dateStr));
//        return commonDao.getTempLdMonthList();
    }

    @Override
    public CommonEntity getTempLdYearList( String dateStr) {
        return getRetList(commonDao.getTempLdYearList(dateStr));
//        return commonDao.getTempLdYearList();
    }


    public CommonEntity   getRetList(List<CommonSingleEntity> entityList) {
        if ( !CollectionUtils.isEmpty(entityList)) {
            CommonEntity commonEntity = new CommonEntity();

            entityList.stream().forEach(
                    x->{
                        commonEntity.getxDataList().add(x.getDateInt());
                        commonEntity.getDataList().add(x.getValue());
                        commonEntity.setTotalData(x.getValue() + (commonEntity.getTotalData() == null? 0 : commonEntity.getTotalData()) );
                    }
            );
            long count = entityList.stream().count();
            commonEntity.setAvgData(commonEntity.getTotalData() / count);
            return commonEntity;
        }
        return null;
    }
}