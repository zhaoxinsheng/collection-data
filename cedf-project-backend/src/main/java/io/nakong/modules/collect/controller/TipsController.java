package io.nakong.modules.collect.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.modules.collect.entity.TipsEntity;
import io.nakong.modules.collect.service.TipsService;
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
@RequestMapping("collect/tips")
public class TipsController {
    @Autowired
    private TipsService tipsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:tips:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tipsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:tips:info")
    public R info(@PathVariable("id") String id){
			TipsEntity tips = tipsService.selectById(id);

        return R.ok().put("tips", tips);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:tips:save")
    public R save(@RequestBody TipsEntity tips){
        tips.setInsertTime(new Date());
        tips.setStatus(1);
	    tipsService.insert(tips);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:tips:update")
    public R update(@RequestBody TipsEntity tips){
			tipsService.updateById(tips);
        return R.ok();
    }

    /**
     * 修改状态
     */
    @RequestMapping("/updateStatus")
    @RequiresPermissions("collect:tips:update")
    public R updateStatus(@RequestBody String id){
//        tipsService.updateById(tips);
        TipsEntity tipsEntity =  tipsService.selectById(id);
        TipsEntity updateEntity = new TipsEntity();
        updateEntity.setId(id);
        if (tipsEntity != null) {
           if( tipsEntity.getStatus() == 1 ) {
               updateEntity.setStatus(2);
           } else {
               updateEntity.setStatus(1);
           }
            tipsService.updateById(updateEntity);
        }
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:tips:delete")
    public R delete(@RequestBody String[] ids){
			tipsService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
