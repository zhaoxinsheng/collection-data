package io.nakong.modules.project.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import io.nakong.common.utils.PageUtils;
import io.nakong.modules.project.entity.ReceiverAmountEntity;
import io.nakong.modules.project.vo.ReceiverAmountVo;

/**
 *
 * @author robot
 */

public interface ReceiverAmountService extends IService<ReceiverAmountEntity> {
    PageUtils selectReceiverAmountList(Map<String, Object> paramMap);

    ReceiverAmountVo selectReceiverAmount(String id);
}
