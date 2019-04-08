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
import io.nakong.modules.project.dao.AreaDao;
import io.nakong.modules.project.entity.AreaEntity;
import io.nakong.modules.project.service.AreaService;


@Service("areaService")
@EnableCaching
public class AreaServiceImpl extends ServiceImpl<AreaDao, AreaEntity> implements AreaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AreaEntity> page = this.selectPage(
                new Query<AreaEntity>(params).getPage(),
                new EntityWrapper<AreaEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	@Cacheable(value="areaCache")
	public AreaEntity selectById(Serializable id) {
		System.out.println("Read Area from DB");
		// TODO Auto-generated method stub
		return super.selectById(id);
	}

	@Override
	@CacheEvict(value="areaCache",key="#entity.getId()")
	public boolean updateById(AreaEntity entity) {
		// TODO Auto-generated method stub
		return super.updateById(entity);
	}

	@Override
	@CacheEvict(value="areaCache",key="#entity.getId()")
	public boolean update(AreaEntity entity, Wrapper<AreaEntity> wrapper) {
		// TODO Auto-generated method stub
		return super.update(entity, wrapper);
	}

}
