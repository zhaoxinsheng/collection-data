package io.nakong.modules.collect.service.impl;

import io.nakong.modules.collect.entity.TempEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;

import io.nakong.modules.collect.dao.TempDao;
import io.nakong.modules.collect.service.TempService;


@Service("tempService")
public class TempServiceImpl extends ServiceImpl<TempDao, TempEntity> implements TempService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TempEntity> page = this.selectPage(
                new Query<TempEntity>(params).getPage(),
                new EntityWrapper<TempEntity>()
        );

        return new PageUtils(page);
    }

}
