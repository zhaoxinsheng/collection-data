package io.nakong.modules.collect.service.impl;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.dao.AirCostDao;
import io.nakong.modules.collect.entity.AirCostEntity;
import io.nakong.modules.collect.service.AirCostService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;



@Service("airCostService")
public class AirCostServiceImpl extends ServiceImpl<AirCostDao, AirCostEntity> implements AirCostService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AirCostEntity> page = this.selectPage(
                new Query<AirCostEntity>(params).getPage(),
                new EntityWrapper<AirCostEntity>()
        );

        return new PageUtils(page);
    }

}
