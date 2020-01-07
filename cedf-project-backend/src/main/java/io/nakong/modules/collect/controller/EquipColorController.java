package io.nakong.modules.collect.controller;

import java.util.Arrays;
import java.util.Map;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;
import io.nakong.modules.collect.entity.EquipColorEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.nakong.modules.collect.service.EquipColorService;

/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@cowain.com.cn
 * @date 2019-08-24 13:29:25
 */
@RestController
@RequestMapping("wms/equipcolor")
public class EquipColorController {
    @Autowired
    private EquipColorService equipColorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wms:equipcolor:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = equipColorService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wms:equipcolor:info")
    public R info(@PathVariable("id") Integer id){
			EquipColorEntity equipColor = equipColorService.selectById(id);

        return R.ok().put("equipColor", equipColor);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wms:equipcolor:save")
    public R save(@RequestBody EquipColorEntity equipColor){
			equipColorService.insert(equipColor);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wms:equipcolor:update")
    public R update(@RequestBody EquipColorEntity equipColor){
			equipColorService.updateById(equipColor);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wms:equipcolor:delete")
    public R delete(@RequestBody Integer[] ids){
			equipColorService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }




}
