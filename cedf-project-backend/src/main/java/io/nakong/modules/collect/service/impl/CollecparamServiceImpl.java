package io.nakong.modules.collect.service.impl;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.dao.CollecparamDao;
import io.nakong.modules.collect.entity.CollecparamEntity;
import io.nakong.modules.collect.service.CollecparamService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


@Service("collecparamService")
public class CollecparamServiceImpl extends ServiceImpl<CollecparamDao, CollecparamEntity> implements CollecparamService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CollecparamEntity> page = this.selectPage(
                new Query<CollecparamEntity>(params).getPage(),
                new EntityWrapper<CollecparamEntity>()
        );

        return new PageUtils(page);
    }

}
