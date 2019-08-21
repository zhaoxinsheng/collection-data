package io.nakong.modules.collect.service.impl;

import io.nakong.modules.collect.entity.PressEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;

import io.nakong.modules.collect.dao.PressDao;
import io.nakong.modules.collect.service.PressService;


@Service("pressService")
public class PressServiceImpl extends ServiceImpl<PressDao, PressEntity> implements PressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PressEntity> page = this.selectPage(
                new Query<PressEntity>(params).getPage(),
                new EntityWrapper<PressEntity>()
        );

        return new PageUtils(page);
    }

}
