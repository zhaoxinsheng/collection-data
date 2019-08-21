package io.nakong.modules.collect.controller;

import java.util.Arrays;
import java.util.Map;

import io.nakong.modules.collect.entity.PressEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.modules.collect.service.PressService;
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
@RequestMapping("collect/press")
public class PressController {
    @Autowired
    private PressService pressService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:press:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pressService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:press:info")
    public R info(@PathVariable("id") String id){
			PressEntity press = pressService.selectById(id);

        return R.ok().put("press", press);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:press:save")
    public R save(@RequestBody PressEntity press){
			pressService.insert(press);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:press:update")
    public R update(@RequestBody PressEntity press){
			pressService.updateById(press);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:press:delete")
    public R delete(@RequestBody String[] ids){
			pressService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
