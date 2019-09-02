package io.nakong.modules.collect.service.impl;

import com.google.common.base.Joiner;
import io.nakong.modules.collect.entity.EquipColorEntity;
import io.nakong.modules.collect.entity.EquipmentEntity;
import io.nakong.modules.collect.entity.EquipmentTypeEntity;
import io.nakong.modules.collect.service.EquipColorService;
import io.nakong.modules.collect.service.EquipmentTypeService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    EquipmentDao equipmentDao;

    @Autowired
    EquipColorService equipColorService;

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
                 Map<String,Object> map = new HashMap<>();
                 map.put("equip_Id",entity.getId());
                 List<EquipColorEntity> entities = equipColorService.selectByMap(map);
                 List<String> typeList = new ArrayList<>();
                 if (CollectionUtils.isNotEmpty(entities)) {
                     entities.stream().forEach(x->{
                         EquipmentTypeEntity equipmentTypeEntity =   equipmentTypeService.selectById(x.getCollectType());
                         if (equipmentTypeEntity != null) {
                             typeList.add(equipmentTypeEntity.getName());
                         }

                     });
                 }
                 entity.setCollecTypeName(Joiner.on(",").skipNulls().join(typeList.toArray()));
             }
        }
        return new PageUtils(page);
    }

    @Override
    public int insertReturnId(EquipmentEntity entity){
        return equipmentDao.insertReturnId(entity);
    }
}
