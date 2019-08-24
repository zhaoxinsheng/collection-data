package io.nakong.modules.collect.service;

import com.baomidou.mybatisplus.service.IService;
import io.nakong.common.utils.PageUtils;
import io.nakong.modules.collect.entity.EquipParamEntity;


import java.util.Map;

/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@cowain.com.cn
 * @date 2019-08-24 14:22:53
 */
public interface EquipParamService extends IService<EquipParamEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

