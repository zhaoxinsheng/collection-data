package io.nakong.modules.collect.controller;

import java.util.Arrays;
import java.util.Map;

import io.nakong.modules.collect.entity.PowerEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.modules.collect.service.PowerService;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;



/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2019-05-29 21:58:32
 */
@RestController
@RequestMapping("collect/power")
public class PowerController {
    @Autowired
    private PowerService powerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:power:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = powerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:power:info")
    public R info(@PathVariable("id") String id){
			PowerEntity power = powerService.selectById(id);

        return R.ok().put("power", power);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:power:save")
    public R save(@RequestBody PowerEntity power){
			powerService.insert(power);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:power:update")
    public R update(@RequestBody PowerEntity power){
			powerService.updateById(power);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:power:delete")
    public R delete(@RequestBody String[] ids){
			powerService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
