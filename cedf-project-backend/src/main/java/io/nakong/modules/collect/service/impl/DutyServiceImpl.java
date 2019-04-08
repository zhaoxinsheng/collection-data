package io.nakong.modules.collect.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.dao.DutyDao;
import io.nakong.modules.collect.entity.DutyEntity;
import io.nakong.modules.collect.service.DutyService;


@Service("dutyService")
public class DutyServiceImpl extends ServiceImpl<DutyDao, DutyEntity> implements DutyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DutyEntity> page = this.selectPage(
                new Query<DutyEntity>(params).getPage(),
                new EntityWrapper<DutyEntity>()
        );

        return new PageUtils(page);
    }

}
