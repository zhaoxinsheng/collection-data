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
import io.nakong.modules.collect.entity.EquipmentTypeEntity;
import io.nakong.modules.collect.service.EquipmentTypeService;



/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-06-28 20:20:43
 */
@RestController
@RequestMapping("collect/equipmenttype")
public class EquipmentTypeController {
    @Autowired
    private EquipmentTypeService equipmentTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:equipmenttype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = equipmentTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:equipmenttype:info")
    public R info(@PathVariable("id") Integer id){
			EquipmentTypeEntity equipmentType = equipmentTypeService.selectById(id);

        return R.ok().put("equipmentType", equipmentType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:equipmenttype:save")
    public R save(@RequestBody EquipmentTypeEntity equipmentType){
			equipmentTypeService.insert(equipmentType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:equipmenttype:update")
    public R update(@RequestBody EquipmentTypeEntity equipmentType){
			equipmentTypeService.updateById(equipmentType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:equipmenttype:delete")
    public R delete(@RequestBody Integer[] ids){
			equipmentTypeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
