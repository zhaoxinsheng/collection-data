package io.nakong.modules.collect.service.impl;

import io.jsonwebtoken.lang.Collections;
import io.nakong.modules.collect.entity.EquipColorEntity;
import io.nakong.modules.collect.entity.PowerEntity;
import io.nakong.modules.collect.service.EquipColorService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    EquipColorService equipColorService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String sqlWhere = " id in (select max(t.id) id  from   t_power  t  where 1=1 ";
        if (params != null && params.get("startDate") != null && !"".equals(params.get("startDate"))) {
            sqlWhere += " and collec_time >=  " + params.get("startDate");
        }
        if (params != null && params.get("endDate") != null && !"".equals(params.get("endDate"))) {
            sqlWhere += " and collec_time <=  " + params.get("endDate");
        }

        sqlWhere += " group by t.equip_id )";

        Page<PowerEntity> page = this.selectPage(
                new Query<PowerEntity>(params).getPage(),
                new EntityWrapper<PowerEntity>().addFilterIfNeed(true,sqlWhere)
        );

        if(page != null && !Collections.isEmpty(page.getRecords())){
            page.getRecords().stream().forEach(x->{
                EquipColorEntity equipColorEntity = equipColorService.selectById(x.getId());
                if(equipColorEntity != null){
                    x.setName(equipColorEntity.getVarname());
                    x.setUnitName(equipColorEntity.getUnit());
                }
            } );
        }
        return new PageUtils(page);
    }

}
