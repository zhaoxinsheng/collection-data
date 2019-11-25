package io.nakong.modules.collect.controller;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;
import io.nakong.modules.collect.entity.HomeEntity;
import io.nakong.modules.collect.entity.HomePipeEntity;
import io.nakong.modules.collect.service.HomeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 *
 * @author tom
 * @email zhaoxinsheng@cowain.com.cn
 * @date 2019-08-24 14:44:22
 */
@RestController
@RequestMapping("collect/home")
public class HomeController {


    @Autowired
    HomeService homeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(){

        HomeEntity  homeEntity = homeService.getAllList();
        return R.ok().put("data", homeEntity);
    }

    /**
     * 瞬时/累积列表
     */
    @RequestMapping("/pipelist")
    public R pipelist(){
        Map<String, ArrayList>    homePipeEntity = homeService.getAllPipeList();
        return R.ok().put("data", homePipeEntity);
    }


    /**
     * 总管压力列表
     */
    @RequestMapping("/totalpresslist")
    public R totalpresslist(){
        Map<String,ArrayList<Float>>   homePipeEntity = homeService.getTotalpresslist();
        return R.ok().put("data", homePipeEntity);
    }

    /**
     * 总管温度列表
     */
    @RequestMapping("/totaltemplist")
    public R totaltemplist(){
        Map<String,ArrayList<Float>>    homePipeEntity = homeService.getTotaltemplist();
        return R.ok().put("data", homePipeEntity);
    }

}
