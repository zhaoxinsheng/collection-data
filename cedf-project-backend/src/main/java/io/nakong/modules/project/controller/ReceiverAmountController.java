package io.nakong.modules.project.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;
import io.nakong.modules.project.entity.ReceiverAmountEntity;
import io.nakong.modules.project.service.ReceiverAmountService;
import io.nakong.modules.sys.controller.AbstractController;

/**
 *
 * @author robot
 * @date 2018/5/26
 */
@RestController
@RequestMapping("project/receiverAmount")
public class ReceiverAmountController extends AbstractController {
	@Autowired
	private ReceiverAmountService receiverAmountService;

	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> paramMap) throws ParseException {
		PageUtils page = receiverAmountService.selectReceiverAmountList(paramMap);
		return R.ok().put("list", page);
	}

	@RequestMapping("/save")
	public R save(@RequestBody ReceiverAmountEntity receiverAmount) throws ParseException {
		receiverAmount.setInsertTime(new Date());
		receiverAmount.setInsertUser(getUserId());
		receiverAmountService.insert(receiverAmount);
		return R.ok();
	}

	@RequestMapping("/info/{id}")
	public R info(@PathVariable(name = "id") String id) throws ParseException {
		return R.ok().put("receiverAmount", receiverAmountService.selectReceiverAmount(id));
	}

	@RequestMapping("/update")
	public R update(@RequestBody ReceiverAmountEntity receiverAmount) throws ParseException {
		receiverAmountService.updateAllColumnById(receiverAmount);
		return R.ok();
	}

	@RequestMapping("/delete/{id}")
	public R delete(@PathVariable(name = "id") Integer id) throws ParseException {
		receiverAmountService.deleteById(id);
		return R.ok();
	}
}
