package io.nakong.modules.collect.controller;

import java.util.Arrays;
import java.util.Map;

import io.nakong.modules.collect.entity.PwPriceEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.modules.collect.service.PwPriceService;
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
@RequestMapping("collect/pwprice")
public class PwPriceController {
    @Autowired
    private PwPriceService pwPriceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:pwprice:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pwPriceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:pwprice:info")
    public R info(@PathVariable("id") String id){
			PwPriceEntity pwPrice = pwPriceService.selectById(id);

        return R.ok().put("pwPrice", pwPrice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:pwprice:save")
    public R save(@RequestBody PwPriceEntity pwPrice){
			pwPriceService.insert(pwPrice);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:pwprice:update")
    public R update(@RequestBody PwPriceEntity pwPrice){

        PwPriceEntity  oldPrice = pwPriceService.selectById(pwPrice.getId());
          if (oldPrice == null) {
              pwPriceService.insert(pwPrice);
          } else {
              pwPriceService.updateById(pwPrice);
          }
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:pwprice:delete")
    public R delete(@RequestBody String[] ids){
			pwPriceService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
