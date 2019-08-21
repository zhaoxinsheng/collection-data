package io.nakong.modules.collect.controller;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;
import io.nakong.modules.collect.entity.TipsEntity;
import io.nakong.modules.collect.service.TipsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2019-05-29 21:58:32
 */
@RestController
@RequestMapping("collect/period")
public class CollecPeriodController {
    @Autowired
    private TipsService tipsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:period:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tipsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:period:info")
    public R info(@PathVariable("id") String id){
			TipsEntity tips = tipsService.selectById(id);

        return R.ok().put("tips", tips);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:period:save")
    public R save(@RequestBody TipsEntity tips){
			tipsService.insert(tips);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:period:update")
    public R update(@RequestBody TipsEntity tips){
			tipsService.updateById(tips);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:tips:delete")
    public R delete(@RequestBody String[] ids){
			tipsService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
