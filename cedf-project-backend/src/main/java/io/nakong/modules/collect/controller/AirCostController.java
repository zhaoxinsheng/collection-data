package io.nakong.modules.collect.controller;

import java.util.Arrays;
import java.util.Map;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;
import io.nakong.modules.collect.entity.AirCostEntity;
import io.nakong.modules.collect.service.AirCostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@cowain.com.cn
 * @date 2019-08-24 14:44:22
 */
@RestController
@RequestMapping("collect/aircost")
public class AirCostController {
    @Autowired
    private AirCostService airCostService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:aircost:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = airCostService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:aircost:info")
    public R info(@PathVariable("id") Integer id){
			AirCostEntity airCost = airCostService.selectById(id);

        return R.ok().put("airCost", airCost);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:aircost:save")
    public R save(@RequestBody AirCostEntity airCost){
			airCostService.insert(airCost);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:aircost:update")
    public R update(@RequestBody AirCostEntity airCost){
			airCostService.updateById(airCost);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:aircost:delete")
    public R delete(@RequestBody Integer[] ids){
			airCostService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
