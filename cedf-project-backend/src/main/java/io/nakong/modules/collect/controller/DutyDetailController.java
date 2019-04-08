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
import io.nakong.modules.collect.entity.DutyDetailEntity;
import io.nakong.modules.collect.service.DutyDetailService;



/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-06-28 20:20:43
 */
@RestController
@RequestMapping("collect/dutydetail")
public class DutyDetailController {
    @Autowired
    private DutyDetailService dutyDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:dutydetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dutyDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:dutydetail:info")
    public R info(@PathVariable("id") Integer id){
			DutyDetailEntity dutyDetail = dutyDetailService.selectById(id);

        return R.ok().put("dutyDetail", dutyDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:dutydetail:save")
    public R save(@RequestBody DutyDetailEntity dutyDetail){
			dutyDetailService.insert(dutyDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:dutydetail:update")
    public R update(@RequestBody DutyDetailEntity dutyDetail){
			dutyDetailService.updateById(dutyDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:dutydetail:delete")
    public R delete(@RequestBody Integer[] ids){
			dutyDetailService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
