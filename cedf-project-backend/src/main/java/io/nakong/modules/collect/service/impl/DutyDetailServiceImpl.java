package io.nakong.modules.collect.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.dao.DutyDetailDao;
import io.nakong.modules.collect.entity.DutyDetailEntity;
import io.nakong.modules.collect.service.DutyDetailService;


@Service("dutyDetailService")
public class DutyDetailServiceImpl extends ServiceImpl<DutyDetailDao, DutyDetailEntity> implements DutyDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DutyDetailEntity> page = this.selectPage(
                new Query<DutyDetailEntity>(params).getPage(),
                new EntityWrapper<DutyDetailEntity>()
        );

        return new PageUtils(page);
    }

}
