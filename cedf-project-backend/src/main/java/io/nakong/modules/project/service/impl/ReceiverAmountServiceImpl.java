package io.nakong.modules.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.project.dao.ReceiverAmountDao;
import io.nakong.modules.project.dao.ReceiverDao;
import io.nakong.modules.project.entity.ReceiverAmountEntity;
import io.nakong.modules.project.entity.ReceiverEntity;
import io.nakong.modules.project.service.ReceiverAmountService;
import io.nakong.modules.project.vo.ReceiverAmountVo;

/**
 *
 * @author robot
 */
@Service("receiverAmountService")
public class ReceiverAmountServiceImpl extends ServiceImpl<ReceiverAmountDao, ReceiverAmountEntity> implements ReceiverAmountService {
	@Autowired
	private ReceiverAmountDao receiverAmountDao;
	@Autowired
	private ReceiverDao receiverDao;

	@Override
	public PageUtils selectReceiverAmountList(Map<String, Object> paramMap) {
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int limit = Integer.valueOf(String.valueOf(paramMap.get("limit")));
		if (paramMap.get("projectNo") != null) {
			paramMap.put("projectNo", String.valueOf(paramMap.get("projectNo")).trim());
		}
		if (paramMap.get("receiveCompany") != null) {
			paramMap.put("receiveCompany", String.valueOf(paramMap.get("receiveCompany")).trim());
		}

		Query query = new Query(paramMap);
		List<ReceiverAmountVo> receiverAmounts = receiverAmountDao.selectReceiverAmountList(query);
		int totalCount = receiverAmountDao.selectReceiverAmountCount(query);
		return new PageUtils(receiverAmounts, totalCount, limit, page);
	}

	@Override
	public ReceiverAmountVo selectReceiverAmount(String id) {
		ReceiverAmountEntity receiverAmount = receiverAmountDao.selectById(id);
		ReceiverEntity receiver = receiverDao.selectById(receiverAmount.getReceiverId());
		ReceiverAmountVo receiverAmountVo = new ReceiverAmountVo();
		BeanUtils.copyProperties(receiver, receiverAmountVo);
		BeanUtils.copyProperties(receiverAmount, receiverAmountVo);
		return receiverAmountVo;
	}
}
