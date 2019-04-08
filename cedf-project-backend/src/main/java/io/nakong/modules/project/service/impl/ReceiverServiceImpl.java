package io.nakong.modules.project.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.project.dao.AreaDao;
import io.nakong.modules.project.dao.ReceiverDao;
import io.nakong.modules.project.entity.AreaEntity;
import io.nakong.modules.project.entity.ReceiverEntity;
import io.nakong.modules.project.service.ReceiverService;


@Service("receiverService")
public class ReceiverServiceImpl extends ServiceImpl<ReceiverDao, ReceiverEntity> implements ReceiverService {

	@Autowired
	AreaDao areaDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ReceiverEntity> page = this.selectPage(
                new Query<ReceiverEntity>(params).getPage(),
                new EntityWrapper<ReceiverEntity>()
        );

        return new PageUtils(page);
    }
    
    public ReceiverEntity  selectById(Integer id) {
    	ReceiverEntity receiver = super.selectById(id);
    	assembleFullAddress(receiver);
    	return receiver;
    }
    
	/**
	 * 填充省市县信息
	 */
	private void assembleFullAddress(ReceiverEntity receiverEntity) {
		 
	       if (receiverEntity == null) {
	    	    return ;
	       }
			AreaEntity province = areaDao.selectById(receiverEntity.getProvinceId());
			String prefixString = "";
			if (province != null) {
				prefixString += province.getAreaName();
			}
			AreaEntity city = areaDao.selectById(receiverEntity.getCityId());
			if (city != null) {
				prefixString += city.getAreaName();
			}
			AreaEntity county = areaDao.selectById(receiverEntity.getCountyId());
			if (county != null) {
				prefixString += county.getAreaName();
			}
			receiverEntity.setAddressDetail(prefixString + receiverEntity.getAddressDetail());
	}
}
