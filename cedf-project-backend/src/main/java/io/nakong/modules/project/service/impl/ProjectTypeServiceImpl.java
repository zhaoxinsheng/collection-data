package io.nakong.modules.project.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.project.dao.ProjectTypeDao;
import io.nakong.modules.project.entity.ProjectTypeEntity;
import io.nakong.modules.project.service.ProjectTypeService;


@Service("projectTypeService")
@EnableCaching
public class ProjectTypeServiceImpl extends ServiceImpl<ProjectTypeDao, ProjectTypeEntity> implements ProjectTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProjectTypeEntity> page = this.selectPage(
                new Query<ProjectTypeEntity>(params).getPage(),
                new EntityWrapper<ProjectTypeEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	@Cacheable(value="projectType")
	public ProjectTypeEntity selectById(Serializable id) {
		// TODO Auto-generated method stub
		return super.selectById(id);
	}

	@Override
	@CacheEvict(value="areaCache",key="#entity.getId()")
	public boolean insertOrUpdate(ProjectTypeEntity entity) {
		// TODO Auto-generated method stub
		return super.insertOrUpdate(entity);
	}

	@Override
	@CacheEvict(value="areaCache",key="#entity.getId()")
	public boolean insertOrUpdateAllColumn(ProjectTypeEntity entity) {
		// TODO Auto-generated method stub
		return super.insertOrUpdateAllColumn(entity);
	}

	@Override
	@CacheEvict(value="areaCache",key="#id")
	public boolean deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return super.deleteById(id);
	}


	@Override
	@CacheEvict(value="areaCache",key="#entity.getId()")
	public boolean updateById(ProjectTypeEntity entity) {
		// TODO Auto-generated method stub
		return super.updateById(entity);
	}

	@Override
	@CacheEvict(value="areaCache",key="#entity.getId()")
	public boolean update(ProjectTypeEntity entity, Wrapper<ProjectTypeEntity> wrapper) {
		// TODO Auto-generated method stub
		return super.update(entity, wrapper);
	}

}
