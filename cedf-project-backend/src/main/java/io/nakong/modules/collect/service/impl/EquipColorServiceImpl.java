package io.nakong.modules.collect.service.impl;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.dao.EquipColorDao;
import io.nakong.modules.collect.entity.EquipColorEntity;
import io.nakong.modules.collect.service.EquipColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


@Service("equipColorService")
public class EquipColorServiceImpl extends ServiceImpl<EquipColorDao, EquipColorEntity> implements EquipColorService {


    @Autowired
    EquipColorDao  equipColorDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EquipColorEntity> page = this.selectPage(
                new Query<EquipColorEntity>(params).getPage(),
                new EntityWrapper<EquipColorEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public EquipColorEntity queryByCollectType(String equipId, int collectType) {
        return equipColorDao.queryByCollectType(equipId, collectType);
    }

    @Override
   public EquipColorEntity selectByVarName(String varName){
        return equipColorDao.selectByVarName(varName);
    }

    @Override
    public List<EquipColorEntity> selectAllRecord(){
        return equipColorDao.selectAllRecord();
    }
}
