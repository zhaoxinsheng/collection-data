package io.nakong.modules.project.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;
import io.nakong.modules.project.entity.ProjectEntity;
import io.nakong.modules.project.vo.ProjectVo;

/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:14
 */
public interface ProjectService extends IService<ProjectEntity> {

	PageUtils queryPage(Map<String, Object> params) throws Exception;
	
	List<ProjectEntity> queryProjectList(Map<String, Object> params) throws Exception;

	R insertProject(ProjectVo project, Long userId) throws Exception;

	ProjectVo selectByIdV2(Integer projectId) throws Exception;

	R updateProject(ProjectVo project, Long userId) throws Exception;

	void deleteProject(List<Integer> ids) throws Exception;

	void importByExcel(InputStream file, Long userId) throws Exception;

	List<ProjectEntity> selectList(EntityWrapper<ProjectEntity> projectEntity) throws Exception;
}
