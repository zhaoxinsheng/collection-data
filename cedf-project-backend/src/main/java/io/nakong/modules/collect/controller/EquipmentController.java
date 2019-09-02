package io.nakong.modules.collect.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.nakong.modules.collect.entity.EquipColorEntity;
import io.nakong.modules.collect.entity.EquipmentEntity;
import io.nakong.modules.collect.entity.EquipmentTypeEntity;
import io.nakong.modules.collect.service.EquipColorService;
import io.nakong.modules.collect.service.EquipmentTypeService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.modules.collect.service.EquipmentService;
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
@RequestMapping("collect/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipColorService equipColorService;

    @Autowired
    private EquipmentTypeService equipmentTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("collect:equipment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = equipmentService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 采集类型列表
     */
    @RequestMapping("/typelist")
//    @RequiresPermissions("collect:equipment:list")
    public R typeList(@RequestParam Map<String, Object> params){
        String type = (String)params.get("type");
        EntityWrapper wrapper = new EntityWrapper<EquipmentEntity>();
        wrapper.eq(true,"type",1);
        return R.ok().put("data", wrapJsonArray(equipmentTypeService.selectList(wrapper)));
    }


    /**
     * 采集类型列表
     */
    @RequestMapping("/equiplist")
//    @RequiresPermissions("collect:equipment:list")
    public R equiplist(@RequestParam Map<String, Object> params){
        String collecType = (String)params.get("collecType");
        EntityWrapper wrapper = new EntityWrapper<EquipmentEntity>();
        wrapper.eq(true,"collec_type",collecType);
        wrapper.orderBy("insert_date", true);
        return R.ok().put("data", wrapEquipJsonArray(equipmentService.selectList(wrapper)));
    }

    /**unitlist
     * 采集类型列表
     */
    @RequestMapping("/unitlist")
    @RequiresPermissions("collect:equipment:list")
    public R unitlist(@RequestParam Map<String, Object> params){
      //  String type = (String)params.get("type");
        EntityWrapper wrapper = new EntityWrapper<EquipmentTypeEntity>();
        wrapper.eq(true,"type",2);
        return R.ok().put("data", wrapUnitJsonArray(equipmentTypeService.selectList(wrapper)));
    }

    private JSONArray wrapUnitJsonArray(List<EquipmentTypeEntity> dataList) {
        JSONArray jsonObject = new JSONArray();
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (EquipmentTypeEntity entity : dataList) {
                Map singleMap = new HashMap();
                singleMap.put(entity.getId(),entity.getName());
                JSONObject singleObject = new JSONObject();
                singleObject.put(String.valueOf(entity.getId()),entity.getName());
                jsonObject.add(singleObject);

            }
        }
        return jsonObject;
    }

    private JSONArray wrapJsonArray(List<EquipmentTypeEntity> dataList) {
        JSONArray jsonObject = new JSONArray();
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (EquipmentTypeEntity entity : dataList) {
                JSONObject singleObject = new JSONObject();
                singleObject.put("id",entity.getId());
                singleObject.put("name",entity.getName());
                jsonObject.add(singleObject);
            }
        }
        return jsonObject;
    }

    private JSONArray wrapEquipJsonArray(List<EquipmentEntity> dataList) {
        JSONArray jsonObject = new JSONArray();
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (EquipmentEntity entity : dataList) {
                JSONObject singleObject = new JSONObject();
                singleObject.put("id",entity.getId());
                singleObject.put("name",entity.getName());
                jsonObject.add(singleObject);
            }
        }
        return jsonObject;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("collect:equipment:info")
    public R info(@PathVariable("id") String id){

			EquipmentEntity equipment = equipmentService.selectById(id);
			Map<String,Object> map = new HashMap<>();
			map.put("equip_id",id);
            equipment.setDetail(equipColorService.selectByMap(map));

        return R.ok().put("equipment", equipment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("collect:equipment:save")
    public R save(@RequestBody EquipmentEntity equipment){
       // equipment.setId(UUID.randomUUID().toString().replaceAll("-",""));
	   int equipId =  equipmentService.insertReturnId(equipment);
        List <EquipColorEntity> equipList = equipment.getDetail();
        if(equipId > 0 &&  CollectionUtils.isNotEmpty(equipList) ) {
            equipList = equipList.stream().map(x->{
                x.setEquipId(equipment.getId());
                x.setInsertTime(new Date());
                return x;
            }).collect(Collectors.toList());
            equipColorService.insertBatch(equipList);
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("collect:equipment:update")
    public R update(@RequestBody EquipmentEntity equipment){
			equipmentService.updateById(equipment);

			if (equipment != null) {
                int equipId = equipment.getId();
                List<EquipColorEntity> detail = equipment.getDetail();
                if (CollectionUtils.isNotEmpty(detail)) {
                    Map<String ,Object> param = new HashMap<>();
                    param.put("equip_id",equipId);
                    equipColorService.deleteByMap(param);
                }
                List <EquipColorEntity> equipList = equipment.getDetail();
                if( CollectionUtils.isNotEmpty(equipList) ) {
//                    equipment.getDetail().stream().map(x->{
//                        x.setEquipId(equipId);
//                        return x;
//                    });
                    equipList = equipList.stream().map(x->{
                        x.setEquipId(equipment.getId());
                        return x;
                    }).collect(Collectors.toList());
                    equipColorService.insertBatch(equipList);
                }
            }

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("collect:equipment:delete")
    public R delete(@RequestBody String[] ids){
			equipmentService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
