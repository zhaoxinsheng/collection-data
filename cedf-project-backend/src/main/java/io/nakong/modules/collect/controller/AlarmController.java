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
import io.nakong.modules.collect.entity.AlarmEntity;
import io.nakong.modules.collect.service.AlarmService;



/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-06-28 20:20:43
 */
@RestController
@RequestMapping("collect/alarm")
public class AlarmController {
    @Autowired
    private AlarmService alarmService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:alarm:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = alarmService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:alarm:info")
    public R info(@PathVariable("id") Integer id){
			AlarmEntity alarm = alarmService.selectById(id);

        return R.ok().put("alarm", alarm);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:alarm:save")
    public R save(@RequestBody AlarmEntity alarm){
			alarmService.insert(alarm);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:alarm:update")
    public R update(@RequestBody AlarmEntity alarm){
			alarmService.updateById(alarm);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:alarm:delete")
    public R delete(@RequestBody Integer[] ids){
			alarmService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
