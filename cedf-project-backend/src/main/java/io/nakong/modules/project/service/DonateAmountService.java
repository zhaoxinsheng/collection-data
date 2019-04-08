package io.nakong.modules.project.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import io.nakong.common.utils.PageUtils;
import io.nakong.modules.project.entity.DonateAmountEntity;

/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:15
 */
public interface DonateAmountService extends IService<DonateAmountEntity> {

    PageUtils queryPage(Map<String, Object> params) throws Exception;
    public void updateByProjectId(Integer projectId,String Donator);
}

