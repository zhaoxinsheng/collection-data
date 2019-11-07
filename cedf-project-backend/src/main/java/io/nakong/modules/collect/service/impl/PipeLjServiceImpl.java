package io.nakong.modules.collect.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.dao.PipeLjDao;
import io.nakong.modules.collect.dao.PipeSsDao;
import io.nakong.modules.collect.entity.PipeLjEntity;
import io.nakong.modules.collect.entity.PipeSsEntity;
import io.nakong.modules.collect.service.PipeLjService;
import io.nakong.modules.collect.service.PipeSsService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("pipeLjService")
public class PipeLjServiceImpl extends ServiceImpl<PipeLjDao, PipeLjEntity> implements PipeLjService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PipeLjEntity> page = this.selectPage(
                new Query<PipeLjEntity>(params).getPage(),
                new EntityWrapper<PipeLjEntity>()
        );

        return new PageUtils(page);
    }

}
