package io.nakong.modules.project.controller;

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
import io.nakong.modules.project.entity.DonatorEntity;
import io.nakong.modules.project.service.DonatorService;



/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:14
 */
@RestController
@RequestMapping("project/donator")
public class DonatorController {
    @Autowired
    private DonatorService donatorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:donator:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = donatorService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:donator:info")
    public R info(@PathVariable("id") Integer id){
			DonatorEntity donator = donatorService.selectById(id);

        return R.ok().put("donator", donator);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:donator:save")
    public R save(@RequestBody DonatorEntity donator){
			donatorService.insert(donator);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:donator:update")
    public R update(@RequestBody DonatorEntity donator){
			donatorService.updateById(donator);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:donator:delete")
    public R delete(@RequestBody Integer[] ids){
			donatorService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
