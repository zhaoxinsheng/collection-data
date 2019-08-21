package io.nakong.modules.collect.controller;

import java.util.Arrays;
import java.util.Map;

import io.nakong.modules.collect.entity.EquipStatusEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.modules.collect.service.EquipStatusService;
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
@RequestMapping("collect/equipstatus")
public class EquipStatusController {
    @Autowired
    private EquipStatusService equipStatusService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:equipstatus:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = equipStatusService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:equipstatus:info")
    public R info(@PathVariable("id") String id){
			EquipStatusEntity equipStatus = equipStatusService.selectById(id);

        return R.ok().put("equipStatus", equipStatus);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:equipstatus:save")
    public R save(@RequestBody EquipStatusEntity equipStatus){
			equipStatusService.insert(equipStatus);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:equipstatus:update")
    public R update(@RequestBody EquipStatusEntity equipStatus){
			equipStatusService.updateById(equipStatus);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:equipstatus:delete")
    public R delete(@RequestBody String[] ids){
			equipStatusService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
