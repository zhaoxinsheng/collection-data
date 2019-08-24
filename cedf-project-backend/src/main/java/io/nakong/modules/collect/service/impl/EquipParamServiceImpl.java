package io.nakong.modules.collect.service.impl;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.dao.EquipParamDao;
import io.nakong.modules.collect.entity.EquipParamEntity;
import io.nakong.modules.collect.service.EquipParamService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;



@Service("equipParamService")
public class EquipParamServiceImpl extends ServiceImpl<EquipParamDao, EquipParamEntity> implements EquipParamService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EquipParamEntity> page = this.selectPage(
                new Query<EquipParamEntity>(params).getPage(),
                new EntityWrapper<EquipParamEntity>()
        );

        return new PageUtils(page);
    }

}
