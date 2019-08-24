package io.nakong.modules.collect.controller;

import java.util.Arrays;
import java.util.Map;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;
import io.nakong.modules.collect.entity.CollecparamEntity;
import io.nakong.modules.collect.service.CollecparamService;
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
 * @date 2019-08-24 14:34:43
 */
@RestController
@RequestMapping("collect/collecparam")
public class CollecparamController {
    @Autowired
    private CollecparamService collecparamService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:collecparam:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = collecparamService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:collecparam:info")
    public R info(@PathVariable("id") Integer id){
			CollecparamEntity collecparam = collecparamService.selectById(id);

        return R.ok().put("collecparam", collecparam);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:collecparam:save")
    public R save(@RequestBody CollecparamEntity collecparam){
			collecparamService.insert(collecparam);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:collecparam:update")
    public R update(@RequestBody CollecparamEntity collecparam){
			collecparamService.updateById(collecparam);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:collecparam:delete")
    public R delete(@RequestBody Integer[] ids){
			collecparamService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
