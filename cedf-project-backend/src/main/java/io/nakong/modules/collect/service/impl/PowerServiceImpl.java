package io.nakong.modules.collect.service.impl;

import io.nakong.modules.collect.entity.PowerEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;

import io.nakong.modules.collect.dao.PowerDao;
import io.nakong.modules.collect.service.PowerService;


@Service("powerService")
public class PowerServiceImpl extends ServiceImpl<PowerDao, PowerEntity> implements PowerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PowerEntity> page = this.selectPage(
                new Query<PowerEntity>(params).getPage(),
                new EntityWrapper<PowerEntity>()
        );

        return new PageUtils(page);
    }

}
