package io.nakong.modules.collect.controller;

import java.util.Arrays;
import java.util.Map;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;
import io.nakong.modules.collect.entity.EquipParamEntity;
import io.nakong.modules.collect.service.EquipParamService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@cowain.com.cn
 * @date 2019-08-24 14:22:53
 */
@RestController
@RequestMapping("collect/equipparam")
public class EquipParamController {
    @Autowired
    private EquipParamService equipParamService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:equipparam:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = equipParamService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:equipparam:info")
    public R info(@PathVariable("id") Integer id){
			EquipParamEntity equipParam = equipParamService.selectById(id);

        return R.ok().put("equipParam", equipParam);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:equipparam:save")
    public R save(@RequestBody EquipParamEntity equipParam){
			equipParamService.insert(equipParam);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:equipparam:update")
    public R update(@RequestBody EquipParamEntity equipParam){
			equipParamService.updateById(equipParam);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:equipparam:delete")
    public R delete(@RequestBody Integer[] ids){
			equipParamService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
