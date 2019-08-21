package io.nakong.modules.collect.controller;

import java.util.Arrays;
import java.util.Map;

import io.nakong.modules.collect.entity.PipeEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.modules.collect.service.PipeService;
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
@RequestMapping("collect/pipe")
public class PipeController {
    @Autowired
    private PipeService pipeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:pipe:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pipeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:pipe:info")
    public R info(@PathVariable("id") String id){
			PipeEntity pipe = pipeService.selectById(id);

        return R.ok().put("pipe", pipe);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:pipe:save")
    public R save(@RequestBody PipeEntity pipe){
			pipeService.insert(pipe);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:pipe:update")
    public R update(@RequestBody PipeEntity pipe){
			pipeService.updateById(pipe);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:pipe:delete")
    public R delete(@RequestBody String[] ids){
			pipeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
