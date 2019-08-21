package io.nakong.modules.collect.service.impl;

import io.nakong.modules.collect.entity.PressLdEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;

import io.nakong.modules.collect.dao.PressLdDao;
import io.nakong.modules.collect.service.PressLdService;


@Service("pressLdService")
public class PressLdServiceImpl extends ServiceImpl<PressLdDao, PressLdEntity> implements PressLdService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PressLdEntity> page = this.selectPage(
                new Query<PressLdEntity>(params).getPage(),
                new EntityWrapper<PressLdEntity>()
        );

        return new PageUtils(page);
    }

}
