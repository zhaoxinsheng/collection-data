package io.nakong.modules.project.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import io.nakong.common.utils.PageUtils;
import io.nakong.modules.project.entity.ProjectTypeEntity;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-22 14:17:37
 */
public interface ProjectTypeService extends IService<ProjectTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

