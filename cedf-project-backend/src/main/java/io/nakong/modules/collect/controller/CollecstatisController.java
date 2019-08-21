package io.nakong.modules.collect.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import io.nakong.common.utils.DateUtils;
import io.nakong.modules.collect.entity.DataEntity;
import io.nakong.modules.collect.entity.EquipmentEntity;
import io.nakong.modules.collect.entity.EquipmentTypeEntity;
import io.nakong.modules.collect.service.EquipmentService;
import io.nakong.modules.collect.service.EquipmentTypeService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.modules.collect.entity.CollecstatisEntity;
import io.nakong.modules.collect.service.CollecstatisService;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;



/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@cowain.com.cn
 * @date 2019-08-18 21:50:18
 */
@RestController
@RequestMapping("collect/collecstatis")
public class CollecstatisController {
    @Autowired
    private CollecstatisService collecstatisService;

    @Autowired
    private EquipmentService equipmentService;

    /**
     * 对比曲线
     */
    @RequestMapping("/comparisonchart/{collectType}/{equipIds}/{dateType}")
    public R comparisonChart(@PathVariable("collectType") Integer collectType,@PathVariable("equipIds") String  equipIds,@PathVariable("dateType") Integer dateType){

        // dateType(1 当天 2 本周 3 本月 4 本年）
        // compareType 1 压力 Pa  2 电量  kW·h 3 流量 m³ 4 温度 ℃
        String [] dateArray = null;
        Date startDate ;
        Date endDate ;
        String [] equidArray = equipIds.split(",");
        List<DataEntity> dataX = new ArrayList<>();
        String unitName = getUnitName(collectType);
        if (dateType != 0) {
            switch (dateType) {
                case 1:
                    dateArray = DateUtils.getDayHours();
                    startDate = DateUtils.getStartTimeofCurrentDay();
                    endDate = DateUtils.getEndTimeofCurrentDay();
                    for (String equipId : equidArray) {
                        DataEntity entity = getDataEntity(equipId,  collecstatisService.comparisonChartByDay(collectType,equipId,startDate,endDate));
                        dataX.add(entity);
                    }

                    break;
                case 2:
                    dateArray = DateUtils.getDayOfWeek();
                    startDate = DateUtils.getFirstDayofWeek();
                    endDate = DateUtils.getEndDayOfWeek();

                    for (String equipId : equidArray) {
                        DataEntity entity = getDataEntity(equipId, collecstatisService.comparisonChartByWeek(collectType,equipId,startDate,endDate));
                        dataX.add(entity);
                    }
                    break;
                case 3:

                    dateArray = DateUtils.getDayOfMonth();
                    startDate = DateUtils.getFirstDayOfMonth();
                    endDate = DateUtils.getEndDayOfMonth();
                    for (String equipId : equidArray) {
                        DataEntity entity = getDataEntity(equipId,  collecstatisService.comparisonChartByMonth(collectType,equipId,startDate,endDate));
                        dataX.add(entity);
                    }
                    break;
                case 4:

                    dateArray =  DateUtils.getMonthOfYear();
                    startDate = DateUtils.getFirstDayOfYear();
                    endDate = DateUtils.getEndDayOfYear();
                    for (String equipId : equidArray) {
                        DataEntity entity = getDataEntity(equipId, collecstatisService.comparisonChartByYear(collectType,equipId,startDate,endDate));
                        dataX.add(entity);
                    }
                    break;
                default:
                    break;
            }
        }

        return R.ok().put("data", dataX).put("xais",dateArray).put("unitName",unitName);
    }

    /**
     *  1 同比去年 2 同比上月 3 同步上一季度
     *
     */
    @RequestMapping("/comparisonmutichart/{compareType}/{collecType}")
    public R comparisonMutichart(@PathVariable("compareType") Integer compareType,@PathVariable("collecType") Integer collecType){

        if (compareType == 1 ) {

        }
        Date currentStartDate = null;
        Date currentEndDate = null;
        Date compareStartDate = null;
        Date compareEndDate = null;

        switch (compareType) {
            case 1:
                currentStartDate = DateUtils.getFirstDayOfYear();
                currentEndDate = DateUtils.getEndDayOfYear();
                compareStartDate = DateUtils.getFirstDayOfLastYear();
                compareEndDate = DateUtils.getEndDayOfLastYear();


               break;
            case 2:
                currentStartDate = DateUtils.getFirstDayOfMonth();
                currentEndDate = DateUtils.getEndDayOfMonth();
                compareStartDate = DateUtils.getFirstDayOfLastMonth();
                compareEndDate = DateUtils.getEndDayOfLastMonth();
                break;
            case 3:
                currentStartDate = DateUtils.getStartDateOfQuarter();
                currentEndDate = DateUtils.getEndDateOfQuarter();
                compareStartDate = DateUtils.getFirstDayOfLastMonth();
                compareEndDate = DateUtils.getStartDateOfLastQuarter();
                break;
            default:
                    break;

        }
//        CollecstatisEntity collecstatis = collecstatisService.selectById(id);

        return R.ok().put("data", null);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wms:collecstatis:save")
    public R save(@RequestBody CollecstatisEntity collecstatis){
			collecstatisService.insert(collecstatis);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wms:collecstatis:update")
    public R update(@RequestBody CollecstatisEntity collecstatis){
			collecstatisService.updateById(collecstatis);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wms:collecstatis:delete")
    public R delete(@RequestBody Integer[] ids){
			collecstatisService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    private DataEntity getDataEntity(String equipId, List<CollecstatisEntity> collecstatisEntitys) {
        Integer[] values = null;
        if (CollectionUtils.isNotEmpty(collecstatisEntitys)) {
            values = collecstatisEntitys.stream().map(x->x.getCollectData()).collect(Collectors.toList()).toArray(new  Integer[collecstatisEntitys.size()]);
        }
        EquipmentEntity equipmentEntity = equipmentService.selectById(equipId);
        DataEntity entity = new DataEntity();
        entity.setId(equipId);
        entity.setName(equipmentEntity.getName());
        entity.setColor(equipmentEntity.getColor());
        entity.setData(values);
        return entity;
    }

    private String getUnitName(int compareType) {
        String unitName = null;
        switch (compareType) {
            case 1:
                unitName = "Pa";
                break;
            case 2:
                unitName = "kW·h";
                break;
            case 3:
                unitName = "m³";
                break;
            case 4:
                unitName = "℃";
                break;
            default:
                break;

        }
        return unitName;
    }


}
