package io.nakong.modules.collect.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.dao.TipsDao;
import io.nakong.modules.collect.entity.TipsEntity;
import io.nakong.modules.collect.service.TipsService;


@Service("tipsService")
public class TipsServiceImpl extends ServiceImpl<TipsDao, TipsEntity> implements TipsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TipsEntity> page = this.selectPage(
                new Query<TipsEntity>(params).getPage(),
                new EntityWrapper<TipsEntity>()
        );

        return new PageUtils(page);
    }

}
