package io.nakong.modules.collect.service.impl;

import io.nakong.modules.collect.entity.PipeEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;

import io.nakong.modules.collect.dao.PipeDao;
import io.nakong.modules.collect.service.PipeService;


@Service("pipeService")
public class PipeServiceImpl extends ServiceImpl<PipeDao, PipeEntity> implements PipeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PipeEntity> page = this.selectPage(
                new Query<PipeEntity>(params).getPage(),
                new EntityWrapper<PipeEntity>()
        );

        return new PageUtils(page);
    }

}
