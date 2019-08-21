package io.nakong.modules.collect.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.modules.collect.service.EquipmentTypeService;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.dao.EquipmentTypeDao;
import io.nakong.modules.collect.entity.EquipmentTypeEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("equipmentTypeService")
public class EquipmentTypeServiceImpl extends ServiceImpl<EquipmentTypeDao, EquipmentTypeEntity> implements EquipmentTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EquipmentTypeEntity> page = this.selectPage(
                new Query<EquipmentTypeEntity>(params).getPage(),
                new EntityWrapper<EquipmentTypeEntity>()
        );

        return new PageUtils(page);
    }

}
