package io.nakong.modules.collect.service.impl;

import io.nakong.modules.collect.entity.EquipmentTypeEntity;
import io.nakong.modules.collect.service.EquipmentTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;

import io.nakong.modules.collect.dao.TipsDao;
import io.nakong.modules.collect.entity.TipsEntity;
import io.nakong.modules.collect.service.TipsService;


@Service("tipsService")
public class TipsServiceImpl extends ServiceImpl<TipsDao, TipsEntity> implements TipsService {

    @Autowired
    EquipmentTypeService equipmentTypeService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String type = (String)params.get("type");
        Page<TipsEntity> page = this.selectPage(
                new Query<TipsEntity>(params).getPage(),
                new EntityWrapper<TipsEntity>().like(StringUtils.isNotEmpty(type),"equip_type",type)
        );

        if (page != null && page.getRecords().size() > 0 ) {
            List<TipsEntity> dataList = page.getRecords();
            for (TipsEntity entity : dataList) {
                if (entity.getPeriodType() != null ) {
                    entity.setPeriodTypeName(getPeriodName(entity.getPeriodType()));
                }
            }
        }

        return new PageUtils(page);
    }

    //         units: {'0': '秒', '1': '分', '2': '时'}
    private String getPeriodName(int peroidType) {
        String retValue = "秒";
        switch (peroidType) {
            case 0:
                retValue = "秒";
                break;
            case 1:
                retValue = "分";
                break;
            case 2:
                retValue = "时";
                break;
           default:
                    break;

        }
        return retValue;
    }
}
