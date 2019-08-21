package io.nakong.modules.collect.service.impl;

import io.nakong.modules.collect.entity.PwPriceEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;

import io.nakong.modules.collect.dao.PwPriceDao;
import io.nakong.modules.collect.service.PwPriceService;


@Service("pwPriceService")
public class PwPriceServiceImpl extends ServiceImpl<PwPriceDao, PwPriceEntity> implements PwPriceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PwPriceEntity> page = this.selectPage(
                new Query<PwPriceEntity>(params).getPage(),
                new EntityWrapper<PwPriceEntity>()
        );

        return new PageUtils(page);
    }

}
