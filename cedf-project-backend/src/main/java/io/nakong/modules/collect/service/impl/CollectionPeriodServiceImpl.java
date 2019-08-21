package io.nakong.modules.collect.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.modules.collect.service.CollectPeriodService;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.dao.CollecPeridDao;
import io.nakong.modules.collect.entity.CollecPeridEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("collectionPeriodService")
public class CollectionPeriodServiceImpl extends ServiceImpl<CollecPeridDao, CollecPeridEntity> implements CollectPeriodService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CollecPeridEntity> page = this.selectPage(
                new Query<CollecPeridEntity>(params).getPage(),
                new EntityWrapper<CollecPeridEntity>()
        );

        return new PageUtils(page);
    }

}
