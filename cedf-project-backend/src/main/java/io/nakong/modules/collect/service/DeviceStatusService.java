package io.nakong.modules.collect.service;

import com.baomidou.mybatisplus.service.IService;
import io.nakong.common.utils.PageUtils;
import io.nakong.modules.collect.entity.PressEntity;

import java.util.Map;

/**
 *
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2019-05-29 21:58:32
 */
public interface DeviceStatusService extends IService<PressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
