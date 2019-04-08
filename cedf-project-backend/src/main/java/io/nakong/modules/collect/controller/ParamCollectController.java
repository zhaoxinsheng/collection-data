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
import io.nakong.modules.collect.entity.ParamCollectEntity;
import io.nakong.modules.collect.service.ParamCollectService;



/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-06-28 20:20:43
 */
@RestController
@RequestMapping("collect/paramcollect")
public class ParamCollectController {
    @Autowired
    private ParamCollectService paramCollectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:paramcollect:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = paramCollectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{collectTypeId}")
    @RequiresPermissions("collect:paramcollect:info")
    public R info(@PathVariable("collectTypeId") String collectTypeId){
			ParamCollectEntity paramCollect = paramCollectService.queryByCollectTypeId(collectTypeId);

        return R.ok().put("paramCollect", paramCollect);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:paramcollect:save")
    public R save(@RequestBody ParamCollectEntity paramCollect){
    	
    	ParamCollectEntity existRecord = paramCollectService.queryByCollectTypeId(paramCollect.getCollectTypeId());
    	if (existRecord != null) {
    		paramCollectService.updateById(paramCollect);
    	} else {
    		paramCollectService.insert(paramCollect);
    	}
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:paramcollect:update")
    public R update(@RequestBody ParamCollectEntity paramCollect){
			paramCollectService.updateById(paramCollect);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:paramcollect:delete")
    public R delete(@RequestBody Integer[] ids){
			paramCollectService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
