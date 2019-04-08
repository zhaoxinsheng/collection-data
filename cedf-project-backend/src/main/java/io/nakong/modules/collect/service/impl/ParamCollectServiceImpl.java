package io.nakong.modules.collect.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.dao.ParamCollectDao;
import io.nakong.modules.collect.entity.ParamCollectEntity;
import io.nakong.modules.collect.service.ParamCollectService;


@Service("paramCollectService")
public class ParamCollectServiceImpl extends ServiceImpl<ParamCollectDao, ParamCollectEntity> implements ParamCollectService {
	
	@Autowired
	ParamCollectDao  paramCollectDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ParamCollectEntity> page = this.selectPage(
                new Query<ParamCollectEntity>(params).getPage(),
                new EntityWrapper<ParamCollectEntity>()
        );

        return new PageUtils(page);
    }
    
    @Override
    public ParamCollectEntity queryByCollectTypeId(String collectTypeId) {
       return	paramCollectDao.queryByCollectTypeId(collectTypeId);
    }

}
