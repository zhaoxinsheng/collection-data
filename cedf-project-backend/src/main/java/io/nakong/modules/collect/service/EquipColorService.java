package io.nakong.modules.collect.service;

import com.baomidou.mybatisplus.service.IService;
import io.nakong.common.utils.PageUtils;
import io.nakong.modules.collect.entity.EquipColorEntity;


import java.util.Map;

/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@cowain.com.cn
 * @date 2019-08-24 13:29:25
 */
public interface EquipColorService extends IService<EquipColorEntity> {

    PageUtils queryPage(Map<String, Object> params);

    EquipColorEntity queryByCollectType(String equipId,int collectType);
}

