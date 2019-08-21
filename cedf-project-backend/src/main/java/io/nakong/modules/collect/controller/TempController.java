package io.nakong.modules.collect.controller;

import java.util.Arrays;
import java.util.Map;

import io.nakong.modules.collect.entity.TempEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.modules.collect.service.TempService;
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
@RequestMapping("collect/temp")
public class TempController {
    @Autowired
    private TempService tempService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:temp:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tempService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:temp:info")
    public R info(@PathVariable("id") String id){
			TempEntity temp = tempService.selectById(id);

        return R.ok().put("temp", temp);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:temp:save")
    public R save(@RequestBody TempEntity temp){
			tempService.insert(temp);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:temp:update")
    public R update(@RequestBody TempEntity temp){
			tempService.updateById(temp);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:temp:delete")
    public R delete(@RequestBody String[] ids){
			tempService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
