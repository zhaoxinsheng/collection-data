package io.nakong.modules.collect.controller;

import java.util.Arrays;
import java.util.Map;

import io.nakong.modules.collect.entity.PressLdEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.modules.collect.service.PressLdService;
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
@RequestMapping("collect/pressld")
public class PressLdController {
    @Autowired
    private PressLdService pressLdService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:pressld:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pressLdService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:pressld:info")
    public R info(@PathVariable("id") String id){
			PressLdEntity pressLd = pressLdService.selectById(id);

        return R.ok().put("pressLd", pressLd);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:pressld:save")
    public R save(@RequestBody PressLdEntity pressLd){
			pressLdService.insert(pressLd);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:pressld:update")
    public R update(@RequestBody PressLdEntity pressLd){
			pressLdService.updateById(pressLd);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:pressld:delete")
    public R delete(@RequestBody String[] ids){
			pressLdService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
