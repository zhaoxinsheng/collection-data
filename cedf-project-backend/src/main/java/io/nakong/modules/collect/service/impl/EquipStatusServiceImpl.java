package io.nakong.modules.collect.service.impl;

import io.nakong.modules.collect.entity.EquipStatusEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;

import io.nakong.modules.collect.dao.EquipStatusDao;
import io.nakong.modules.collect.service.EquipStatusService;


@Service("equipStatusService")
public class EquipStatusServiceImpl extends ServiceImpl<EquipStatusDao, EquipStatusEntity> implements EquipStatusService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EquipStatusEntity> page = this.selectPage(
                new Query<EquipStatusEntity>(params).getPage(),
                new EntityWrapper<EquipStatusEntity>()
        );

        return new PageUtils(page);
    }

}
