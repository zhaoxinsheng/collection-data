package io.nakong.modules.project.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import io.nakong.common.utils.PageUtils;
import io.nakong.modules.project.entity.ReceiverEntity;

/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:14
 */
public interface ReceiverService extends IService<ReceiverEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

