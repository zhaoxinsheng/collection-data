package io.nakong.modules.project.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import io.nakong.common.utils.PageUtils;
import io.nakong.modules.project.entity.DonatorEntity;
import io.nakong.modules.project.entity.ReceiverEntity;
import io.nakong.modules.project.vo.ProjectSummaryVo;

/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:14
 */
public interface ProjectSummaryService extends IService<ProjectSummaryVo> {

    PageUtils queryPage(Map<String, Object> params) throws Exception;

    void deleteProject(Integer projectId) throws Exception;

    List<ReceiverEntity> queryReceiversByProjectId(Integer projectId);

    DonatorEntity queryDonatorById(Integer donatorId);

    File exportProjectsByCondition(Map<String, Object> params) throws Exception;

}

