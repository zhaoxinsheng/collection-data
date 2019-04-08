package io.nakong.modules.project.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import io.nakong.common.utils.R;
import io.nakong.modules.project.entity.AreaEntity;
import io.nakong.modules.project.service.AreaService;



/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-24 16:43:00
 */
@RestController
@RequestMapping("project/area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    /**
     * 列表
     */
    @RequestMapping("/select")
    @RequiresPermissions("project:base:areaSelect")
    public R list(@RequestParam Map<String, Object> params){

        String parentAreaId = (String) params.get("parentAreaId");
        EntityWrapper wrapper = new EntityWrapper<AreaEntity>();
        //排除全国
        // wrapper.ne("area_code", "000000");

        if (StringUtils.isNotBlank(parentAreaId)) {
            wrapper.eq("parent_id", parentAreaId);
        } else {
            //找省一级别
            wrapper.eq("area_level", 2);
        }

        wrapper.orderBy("area_code", true);
        List<AreaEntity> areaList = areaService.selectList(wrapper);
        AreaEntity defaultSelect = new AreaEntity();
        defaultSelect.setAreaName("--选择--");
        areaList.add(0, defaultSelect);
        return R.ok().put("areaList", areaList);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{baseAreaId}")
    @RequiresPermissions("generator:area:info")
    public R info(@PathVariable("baseAreaId") String baseAreaId){
			AreaEntity area = areaService.selectById(baseAreaId);

        return R.ok().put("area", area);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:area:save")
    public R save(@RequestBody AreaEntity area){
			areaService.insert(area);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:area:update")
    public R update(@RequestBody AreaEntity area){
			areaService.updateById(area);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:area:delete")
    public R delete(@RequestBody String[] baseAreaIds){
			areaService.deleteBatchIds(Arrays.asList(baseAreaIds));

        return R.ok();
    }

}
