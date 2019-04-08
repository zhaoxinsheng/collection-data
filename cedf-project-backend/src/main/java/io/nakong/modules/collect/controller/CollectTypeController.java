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
import io.nakong.modules.collect.entity.CollectTypeEntity;
import io.nakong.modules.collect.service.CollectTypeService;



/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-06-28 20:20:43
 */
@RestController
@RequestMapping("collect/collecttype")
public class CollectTypeController {
    @Autowired
    private CollectTypeService collectTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:collecttype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = collectTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:collecttype:info")
    public R info(@PathVariable("id") Integer id){
			CollectTypeEntity collectType = collectTypeService.selectById(id);

        return R.ok().put("collectType", collectType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:collecttype:save")
    public R save(@RequestBody CollectTypeEntity collectType){
			collectTypeService.insert(collectType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:collecttype:update")
    public R update(@RequestBody CollectTypeEntity collectType){
			collectTypeService.updateById(collectType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:collecttype:delete")
    public R delete(@RequestBody Integer[] ids){
			collectTypeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
