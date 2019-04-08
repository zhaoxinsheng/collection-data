package io.nakong.modules.collect.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.dao.CollectTypeDao;
import io.nakong.modules.collect.entity.CollectTypeEntity;
import io.nakong.modules.collect.service.CollectTypeService;


@Service("collectTypeService")
public class CollectTypeServiceImpl extends ServiceImpl<CollectTypeDao, CollectTypeEntity> implements CollectTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CollectTypeEntity> page = this.selectPage(
                new Query<CollectTypeEntity>(params).getPage(),
                new EntityWrapper<CollectTypeEntity>()
        );

        return new PageUtils(page);
    }

}
