package io.nakong.modules.collect.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;
import io.nakong.modules.collect.entity.DutyEntity;
import io.nakong.modules.collect.service.DutyService;



/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-06-28 20:20:43
 */
@RestController
@RequestMapping("collect/duty")
public class DutyController {
    @Autowired
    private DutyService dutyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:duty:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dutyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:duty:info")
    public R info(@PathVariable("id") Integer id){
			DutyEntity duty = dutyService.selectById(id);

        return R.ok().put("duty", duty);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:duty:save")
    public R save(@RequestBody DutyEntity duty){
			dutyService.insert(duty);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:duty:update")
    public R update(@RequestBody DutyEntity duty){
			dutyService.updateById(duty);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:duty:delete")
    public R delete(@RequestBody Integer[] ids){
			dutyService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
