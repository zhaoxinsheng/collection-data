package io.nakong.modules.collect.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.dao.PipeDao;
import io.nakong.modules.collect.dao.PipeSsDao;
import io.nakong.modules.collect.entity.PipeEntity;
import io.nakong.modules.collect.entity.PipeSsEntity;
import io.nakong.modules.collect.service.PipeService;
import io.nakong.modules.collect.service.PipeSsService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("pipeSsService")
public class PipeSsServiceImpl extends ServiceImpl<PipeSsDao, PipeSsEntity> implements PipeSsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PipeSsEntity> page = this.selectPage(
                new Query<PipeSsEntity>(params).getPage(),
                new EntityWrapper<PipeSsEntity>()
        );

        return new PageUtils(page);
    }

}
