package io.nakong.modules.collect.service.impl;

import io.nakong.modules.collect.entity.EquipmentEntity;
import io.nakong.modules.collect.entity.EquipmentTypeEntity;
import io.nakong.modules.collect.service.EquipmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;

import io.nakong.modules.collect.dao.EquipmentDao;
import io.nakong.modules.collect.service.EquipmentService;


@Service("equipmentService")
public class EquipmentServiceImpl extends ServiceImpl<EquipmentDao, EquipmentEntity> implements EquipmentService {

    @Autowired
    private EquipmentTypeService equipmentTypeService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("name");
        Page<EquipmentEntity> page = this.selectPage(
                new Query<EquipmentEntity>(params).getPage(),
                new EntityWrapper<EquipmentEntity>()
                        .like(org.apache.commons.lang.StringUtils.isNotBlank(name),"name", name)
        );

        if (page != null && page.getRecords().size() > 0 ) {
             List<EquipmentEntity> dataList = page.getRecords();
             for (EquipmentEntity entity : dataList) {
                  if (entity.getCollecType() != null ) {
                      EquipmentTypeEntity equipmentTypeEntity =   equipmentTypeService.selectById(entity.getCollecType());
                      entity.setCollecTypeName(equipmentTypeEntity.getName());
                  }
             }
        }
        return new PageUtils(page);
    }

}
