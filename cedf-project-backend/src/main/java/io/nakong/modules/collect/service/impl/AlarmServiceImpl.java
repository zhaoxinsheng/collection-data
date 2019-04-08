package io.nakong.modules.collect.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.dao.AlarmDao;
import io.nakong.modules.collect.entity.AlarmEntity;
import io.nakong.modules.collect.service.AlarmService;


@Service("alarmService")
public class AlarmServiceImpl extends ServiceImpl<AlarmDao, AlarmEntity> implements AlarmService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AlarmEntity> page = this.selectPage(
                new Query<AlarmEntity>(params).getPage(),
                new EntityWrapper<AlarmEntity>()
        );

        return new PageUtils(page);
    }

}
