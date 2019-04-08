package io.nakong.modules.project.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.project.dao.DonatorDao;
import io.nakong.modules.project.entity.DonatorEntity;
import io.nakong.modules.project.service.DonatorService;


@Service("donatorService")
public class DonatorServiceImpl extends ServiceImpl<DonatorDao, DonatorEntity> implements DonatorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DonatorEntity> page = this.selectPage(
                new Query<DonatorEntity>(params).getPage(),
                new EntityWrapper<DonatorEntity>()
        );

        return new PageUtils(page);
    }

}
