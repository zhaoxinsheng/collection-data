package io.nakong.modules.collect.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import io.nakong.common.utils.DateUtils;
import io.nakong.modules.collect.entity.*;
import io.nakong.modules.collect.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    PwPriceService pwPriceService;

    @Autowired
    private EquipColorService equipColorService;

    /**
     * 对比曲线
     */
    @RequestMapping("/comparisonmutichart/dbqx/{collectType}/{equipIds}/{dateType}")
    public R comparisonChart(@PathVariable("collectType") Integer collectType,@PathVariable("equipIds") String  equipIds,@PathVariable("dateType") Integer dateType){

        // dateType(1 当天 2 本周 3 本月 4 本年）
        // collectType 1 压力 Pa  2 电量  kW·h 3 流量 m³ 4 温度 ℃
        String [] dateArray = null;
        String [] equidArray = equipIds.split(",");
        List<DataEntity> dataX = new ArrayList<>();
        String unitName = getUnitName(collectType);
        if (dateType != 0) {
            dateArray = getChartData(dateType, dateArray, collectType, equidArray, dataX);
        }

        return R.ok().put("data", dataX).put("xais",dateArray).put("unitName",unitName);
    }

    /** 系统环比曲线
     *
     *  1 同比去年 2 同比上月 3 同步上一季度
     *
     */
    @RequestMapping("/comparisonmutichart/xthb/{compareType}/{collectType}")
    public R comparisonMutichart(@PathVariable("compareType") Integer compareType,@PathVariable("collectType") Integer collectType){


        // collectType 1 压力 Pa  2 电量  kW·h 3 流量 m³ 4 温度 ℃
        String unitName = getUnitName(collectType);
        CompareDataEntity entity = null,entityLastYear = null;
        switch (compareType) {
            case 1:
                 entity = collecstatisService.getCollectData(DateUtils.getFirstDayOfYear(),DateUtils.getEndDayOfYear(),collectType);
                 entityLastYear = collecstatisService.getCollectData(DateUtils.getFirstDayOfLastYear(),DateUtils.getEndDayOfLastYear(),collectType);
               break;
            case 2:
                 entity = collecstatisService.getCollectData(DateUtils.getFirstDayOfMonth(),DateUtils.getEndDayOfMonth(),collectType);
                 entityLastYear = collecstatisService.getCollectData(DateUtils.getFirstDayOfLastMonth(),DateUtils.getEndDayOfLastMonth(),collectType);
                break;
            case 3:
                 entity = collecstatisService.getCollectData(DateUtils.getStartDateOfQuarter(),DateUtils.getEndDateOfQuarter(),collectType);
                 entityLastYear = collecstatisService.getCollectData(DateUtils.getFirstDayOfLastMonth(),DateUtils.getStartDateOfLastQuarter(),collectType);
                break;
            default:
                    break;

        }

        return R.ok().put("currentData", entity).put("lastData",entityLastYear).put("unitName",unitName);
    }


    /**
     *   用气量图
     *
     */
    @RequestMapping("/comparisonmutichart/yql/{startDate}/{endDate}")
    public R airchart(@PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){

        String unitName = getUnitName(3);
        Date startDate = DateUtils.stringToDate(startDateStr,DateUtils.DATE_PATTERN);
        Date endDate = DateUtils.stringToDate(endDateStr,DateUtils.DATE_PATTERN);
       List<CompareDataEntity>   entities = collecstatisService.getAirCollectData(startDate,endDate);
        return R.ok().put("data", getCompareDataEntities(entities)).put("unitName",unitName);
    }

    /**
     *   系统累计气电比例图
     *
     */
    @RequestMapping("/comparisonmutichart/airpower/{startDate}/{endDate}")
    public R airpowerchart(@PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){

        Date startDate = DateUtils.stringToDate(startDateStr,DateUtils.DATE_PATTERN);
        Date endDate = DateUtils.stringToDate(endDateStr,DateUtils.DATE_PATTERN);
        String unitName = getUnitName(3);
        List<CompareDataEntity>   entities = collecstatisService.getAirPowerCollectData(startDate,endDate);
        return R.ok().put("data", getCompareDataEntities(entities)).put("unitName",unitName);
    }

    /**
     *   系统用电比例图
     *
     */
    @RequestMapping("/comparisonmutichart/power/{startDate}/{endDate}")
    public R powerchart(@PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){

        String unitName = getUnitName(2);
        Date startDate = DateUtils.stringToDate(startDateStr,DateUtils.DATE_PATTERN);
        Date endDate = DateUtils.stringToDate(endDateStr,DateUtils.DATE_PATTERN);
        List<CompareDataEntity>   entities = collecstatisService.getPowerCollectData(startDate,endDate);
        return R.ok().put("data", getCompareDataEntities(entities)).put("unitName",unitName);
    }


    /**
     *   累计流量报表
     *
     */
    @RequestMapping("/comparisonmutichart/airData/{dateType}/{startDate}/{endDate}")
    public R sumAirData(@PathVariable("dateType") int dateType,@PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){
        String unitName = getUnitName(3);
        Date startDate = new Date();
        Date endDate = new Date();

        if (dateType == 0){
             startDate = DateUtils.stringToDate(startDateStr,DateUtils.DATE_PATTERN);
             endDate = DateUtils.stringToDate(endDateStr,DateUtils.DATE_PATTERN);
        } else {
            getDateByCondition(dateType,startDate,endDate);
        }
        List<CompareDataEntity>   entitys = collecstatisService.getSumAirData(startDate,endDate);
        List<AirDataEntity> dataRet = new ArrayList<>();
        if (entitys != null) {
            dataRet = getDateListMap(entitys);
        }
        return R.ok().put("data", dataRet).put("unitName",unitName);
    }

    /**
     *   能耗报表
     *
     */
    @RequestMapping("/comparisonmutichart/powerData/{dateType}/{startDate}/{endDate}")
    public R sumPowerData(@PathVariable("dateType") int dateType,@PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){
        String unitName = getUnitName(2);

        Date startDate = new Date();
        Date endDate = new Date();
        setDate (dateType,startDateStr ,endDateStr,startDate,endDate);

        List<CompareDataEntity>   entities = collecstatisService.sumPowerData(startDate,endDate);
        List<AirDataEntity> dataRet = new ArrayList<>();
        if (entities != null) {
            dataRet = getDateListMap(entities);
        }
        return R.ok().put("data", dataRet).put("unitName",unitName);
    }

    /**
     *   流量报表
     *
     */
    @RequestMapping("/comparisonmutichart/pipeData/{dateType}/{startDate}/{endDate}")
    public R sumPipeData(@PathVariable("dateType") int dateType,@PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){
        String unitName = getUnitName(3);

        Date startDate = new Date();
        Date endDate = new Date();
        setDate (dateType,startDateStr ,endDateStr,startDate,endDate);

        List<CompareDataEntity>   entitys = collecstatisService.sumPipeData(startDate,endDate);
        List<AirDataEntity> dataRet = new ArrayList<>();
        if (entitys != null) {
            dataRet = getDateListMap(entitys);
        }
        return R.ok().put("data", dataRet).put("unitName",unitName);
    }

    /**
     *   排气温度报表
     *
     */
    @RequestMapping("/comparisonmutichart/tempData/{dateType}/{startDate}/{endDate}")
    public R sumTempData(@PathVariable("dateType") int dateType,@PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){
        String unitName = getUnitName(4);

        Date startDate = new Date();
        Date endDate = new Date();
        setDate (dateType,startDateStr ,endDateStr,startDate,endDate);

        List<CompareDataEntity>   entitys = collecstatisService.sumTempData(startDate,endDate);
        List<AirDataEntity> dataRet = new ArrayList<>();
        if (entitys != null) {
            dataRet = getDateListMap(entitys);
        }
        return R.ok().put("data", dataRet).put("unitName",unitName);
    }

    /**
     *   报警报表
     *
     */
    @RequestMapping("/comparisonmutichart/alarmData/{dateType}/{startDate}/{endDate}")
    public R alarmData(@PathVariable("dateType") int dateType,@PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){
        String unitName = getUnitName(3);
        Date startDate = new Date();
        Date endDate = new Date();
        setDate (dateType,startDateStr ,endDateStr,startDate,endDate);

        List<CompareDataEntity>   entitys = collecstatisService.alarmData(startDate,endDate);
        List<AirDataEntity> dataRet = new ArrayList<>();
        if (entitys != null) {
            dataRet = getDateListMap(entitys);
        }
        return R.ok().put("data", dataRet).put("unitName",unitName);
    }


    /**
     *   气电比报表
     *
     */
    @RequestMapping("/comparisonmutichart/airPowerRate/{dateType}/{startDate}/{endDate}")
    public R airPowerRate(@PathVariable("dateType") int dateType,@PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){
        String unitName = getUnitName(3);
        Date startDate = new Date();
        Date endDate = new Date();
        setDate (dateType,startDateStr ,endDateStr,startDate,endDate);

        List<CompareDataEntity>   entitys = collecstatisService.airPowerRate(startDate,endDate);
        if (CollectionUtils.isNotEmpty(entitys)) {
             entitys.stream().map(x->{
                EquipmentEntity equipmentEntity = equipmentService.selectById(x.getEquipId());
                if (equipmentEntity != null) {
                    x.setName(equipmentEntity.getName());
                }
                x.setRate(x.getData() / x.getPowerData());
                return x;
            });
        }
        return R.ok().put("data", entitys).put("unitName",unitName);
    }


    /**
     *   压力数据
     *
     */
    @RequestMapping("/comparisonmutichart/pressData/{startDate}/{endDate}")
    public R pressData(@PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){
        String unitName = getUnitName(1);
        Date startDate = new Date();
        Date endDate = new Date();
        setSimpleDate(startDateStr,endDateStr,startDate,endDate);
        List<CompareDataEntity>   entitys = collecstatisService.pressData(startDate,endDate);
        getEntities(entitys);
        return R.ok().put("data", entitys).put("unitName",unitName);
    }

//    /**
//     *   压力数据
//     *
//     */
//    @RequestMapping("/comparisonmutichart/pipeData/{startDate}/{endDate}")
//    public R pressData(@PathVariable("startDate") Date startDate,@PathVariable("endDate") Date endDate){
//        String unitName = getUnitName(1);
//        List<CompareDataEntity>   entitys = collecstatisService.pipeData(startDate,endDate);
//        getEntities(entitys);
//        return R.ok().put("data", entitys).put("unitName",unitName);
//    }

    /**
     *   耗电量数据
     *
     */
    @RequestMapping("/comparisonmutichart/powerDetailData/{startDate}/{endDate}")
    public R powerData(@PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){
        String unitName = getUnitName(1);
        Date startDate = new Date();
        Date endDate = new Date();
        setSimpleDate(startDateStr,endDateStr,startDate,endDate);

        List<CompareDataEntity>   entities = collecstatisService.powerData(startDate,endDate);
        getEntities(entities);
        return R.ok().put("data", entities).put("unitName",unitName);
    }

    /**
     *   压力露点数据
     *
     */
    @RequestMapping("/comparisonmutichart/ldData/{startDate}/{endDate}")
    public R ldData(@PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){
        String unitName = getUnitName(1);

        Date startDate = new Date();
        Date endDate = new Date();
        setSimpleDate(startDateStr,endDateStr,startDate,endDate);

        List<CompareDataEntity>   entities = collecstatisService.ldData(startDate,endDate);
        getEntities(entities);
        return R.ok().put("data", entities).put("unitName",unitName);
    }


    /**
     *   温度数据
     *
     */
    @RequestMapping("/comparisonmutichart/tempDetailData/{startDate}/{endDate}")
    public R tempData(@PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){
        String unitName = getUnitName(1);

        Date startDate = new Date();
        Date endDate = new Date();
        setSimpleDate(startDateStr,endDateStr,startDate,endDate);

        List<CompareDataEntity>   entities = collecstatisService.tempData(startDate,endDate);
        getEntities(entities);
        return R.ok().put("data", entities).put("unitName",unitName);
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
     *   空气成本图表报表
     *
     */
    @RequestMapping("/comparisonmutichart/airCost/{dateType}/{equipIds}/{startDate}/{endDate}")
    public R airCost(@PathVariable("dateType") int dateType , @PathVariable("equipIds") String equipIds, @PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){

        // dateType(1 当天 2 本周 3 本月 4 本年）
        // collectType 1 压力 Pa  2 电量  kW·h 3 流量 m³ 4 温度 ℃
        String [] dateArray = null;
        int collectType = 3;
        String [] equidArray = equipIds.split(",");
        List<DataEntity> dataX = new ArrayList<>();


        Date startDate = new Date();
        Date endDate = new Date();
        setDate (dateType,startDateStr ,endDateStr,startDate,endDate);

        String unitName = getUnitName(collectType);
        if (dateType != 0) {
            dateArray = getChartData(dateType, dateArray, collectType, equidArray, dataX);
        }
        return R.ok().put("data", dataX).put("xais",dateArray).put("unitName",unitName);

    }
    /**
     *   空气成本报表
     *   excel 数据
     *
     */
    @RequestMapping("/comparisonmutichart/airCostexcel/{dateType}/{equipIds}/{startDate}/{endDate}")
    public R airCostexcel(@PathVariable("dateType") int dateType , @PathVariable("equipIds") String equipIds,  @PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){

        // dateType(1 当天 2 本周 3 本月 4 本年）
        // collectType 1 压力 Pa  2 电量  kW·h 3 流量 m³ 4 温度 ℃
        String [] dateArray = null;
        int collectType = 3;

        Date startDate = new Date();
        Date endDate = new Date();
        setDate (dateType,startDateStr ,endDateStr,startDate,endDate);

        String [] equidArray = equipIds.split(",");
        List<DataEntity> dataX = new ArrayList<>();
        String unitName = getUnitName(collectType);
        if (dateType != 0) {
            dateArray = getAirCostChartData(dateType, dateArray, collectType, equidArray, dataX);
        }
        return R.ok().put("data", dataX).put("unitName",unitName);
    }


    /**
     * 空气成本 excel 数据 装备类
     * @param dateType
     * @param dateArray
     * @param collectType
     * @param equidArray
     * @param dataX
     * @return
     */
    private String[] getAirCostChartData(int dateType, String[] dateArray, int collectType, String[] equidArray, List<DataEntity> dataX) {
        Date startDate;
        Date endDate;
        switch (dateType) {
            case 1:
                dateArray = DateUtils.getDayHours();
                startDate = DateUtils.getStartTimeofCurrentDay();
                endDate = DateUtils.getEndTimeofCurrentDay();
                PwPriceEntity pwPriceEntity = pwPriceService.getBasePriceByDatePriod(startDate,endDate);
                List<CompareDataEntity>   airCostList = collecstatisService.airCostChartByDay(collectType, equidArray, startDate, endDate);
                returnAirCostDetail(pwPriceEntity, airCostList);

                break;
            case 2:
                dateArray = DateUtils.getDayOfWeek();
                startDate = DateUtils.getFirstDayofWeek();
                endDate = DateUtils.getEndDayOfWeek();

                pwPriceEntity = pwPriceService.getBasePriceByDatePriod(startDate,endDate);
                airCostList = collecstatisService.airCostChartByWeek(collectType, equidArray, startDate, endDate);
                returnAirCostDetail(pwPriceEntity, airCostList);
                break;
            case 3:

                dateArray = DateUtils.getDayOfMonth();
                startDate = DateUtils.getFirstDayOfMonth();
                endDate = DateUtils.getEndDayOfMonth();
                 pwPriceEntity = pwPriceService.getBasePriceByDatePriod(startDate,endDate);
                 airCostList = collecstatisService.airCostChartByMonth(collectType, equidArray, startDate, endDate);
                returnAirCostDetail(pwPriceEntity, airCostList);
                break;
            case 4:

                dateArray = DateUtils.getMonthOfYear();
                startDate = DateUtils.getFirstDayOfYear();
                endDate = DateUtils.getEndDayOfYear();
                pwPriceEntity = pwPriceService.getBasePriceByDatePriod(startDate,endDate);
                airCostList = collecstatisService.airCostChartByYear(collectType, equidArray, startDate, endDate);
                returnAirCostDetail(pwPriceEntity, airCostList);
                break;
            default:
                break;
        }
        return dateArray;
    }

    private void returnAirCostDetail(PwPriceEntity pwPriceEntity, List<CompareDataEntity> airCostList) {
        if (CollectionUtils.isNotEmpty(airCostList)) {
            airCostList.stream().map(x -> {

                EquipmentEntity equipmentEntity = equipmentService.selectById(x.getEquipId());
                if (equipmentEntity != null) {
                    x.setName(equipmentEntity.getName());
                    x.setUnitName(equipmentEntity.getCollecUnit());
                }
                x.setData(x.getData());
                x.setPowerData(x.getPowerData());

                if (pwPriceEntity != null) {
                    x.setPowerPrice(pwPriceEntity.getBasePrice());
                }
                return x;
            });
        }
    }


    /**
     * 获取开始时间 和结束时间
     * @param startDateStr
     * @param endDateStr
     */
    private void setDate (int dateType,String startDateStr ,String endDateStr,Date startDate,Date endDate) {
        if (dateType == 0){
            startDate = DateUtils.stringToDate(startDateStr,DateUtils.DATE_PATTERN);
            endDate = DateUtils.stringToDate(endDateStr,DateUtils.DATE_PATTERN);
        } else {
            getDateByCondition(dateType,startDate,endDate);
        }
    }

    /**
     * 获取开始时间 和结束时间
     * @param startDateStr
     * @param endDateStr
     */
    private void setSimpleDate (String startDateStr ,String endDateStr,Date startDate,Date endDate) {
            startDate = DateUtils.stringToDate(startDateStr,DateUtils.DATE_PATTERN);
            endDate = DateUtils.stringToDate(endDateStr,DateUtils.DATE_PATTERN);
    }
    /**
     * 根据条件返回 开始时间和结束时间
     * @param startDate
     * @param endDate
     */
    private void getDateByCondition (int dateType,Date startDate ,Date endDate) {
        if (dateType == 0) {
            return ;
        }
        switch (dateType) {
            case 1:
                startDate = DateUtils.getStartTimeofCurrentDay();
                endDate = DateUtils.getEndTimeofCurrentDay();
                break;
            case 2:
                startDate = DateUtils.getFirstDayofWeek();
                endDate = DateUtils.getEndDayOfWeek();
                break;
            case 3:
                startDate = DateUtils.getFirstDayOfMonth();
                endDate = DateUtils.getEndDayOfMonth();
                break;
            case 4:
                startDate = DateUtils.getFirstDayOfYear();
                endDate = DateUtils.getEndDayOfYear();

                break;
            default:
                break;
        }
    }

    private String[] getChartData(int dateType, String[] dateArray, int collectType, String[] equidArray, List<DataEntity> dataX) {
        Date startDate;
        Date endDate;
        switch (dateType) {
            case 1:
                dateArray = DateUtils.getDayHours();
                startDate = DateUtils.getStartTimeofCurrentDay();
                endDate = DateUtils.getEndTimeofCurrentDay();
                for (String equipId : equidArray) {
                    DataEntity entity = getDataEntity(collectType,equipId, collecstatisService.comparisonChartByDay(collectType, equipId, startDate, endDate));
                    dataX.add(entity);
                }

                break;
            case 2:
                dateArray = DateUtils.getDayOfWeek();
                startDate = DateUtils.getFirstDayofWeek();
                endDate = DateUtils.getEndDayOfWeek();

                for (String equipId : equidArray) {
                    DataEntity entity = getDataEntity(collectType,equipId, collecstatisService.comparisonChartByWeek(collectType, equipId, startDate, endDate));
                    dataX.add(entity);
                }
                break;
            case 3:

                dateArray = DateUtils.getDayOfMonth();
                startDate = DateUtils.getFirstDayOfMonth();
                endDate = DateUtils.getEndDayOfMonth();
                for (String equipId : equidArray) {
                    DataEntity entity = getDataEntity(collectType,equipId, collecstatisService.comparisonChartByMonth(collectType, equipId, startDate, endDate));
                    dataX.add(entity);
                }
                break;
            case 4:

                dateArray = DateUtils.getMonthOfYear();
                startDate = DateUtils.getFirstDayOfYear();
                endDate = DateUtils.getEndDayOfYear();
                for (String equipId : equidArray) {
                    DataEntity entity = getDataEntity(collectType,equipId, collecstatisService.comparisonChartByYear(collectType, equipId, startDate, endDate));
                    dataX.add(entity);
                }
                break;
            default:
                break;
        }
        return dateArray;
    }


    private void getEntities(List<CompareDataEntity> entities) {
        if (CollectionUtils.isNotEmpty(entities)) {
            entities.stream().map(x -> {
                EquipmentEntity equipmentEntity = equipmentService.selectById(x.getEquipId());
                if (equipmentEntity != null) {
                    x.setName(equipmentEntity.getName());
                    x.setUnitName(equipmentEntity.getCollecUnit());
                }
                return x;
            });
        }
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

    /**
     *
     * @param collecstatisEntitys
     * @return
     */
    private DataEntity getDataEntityOfAir(List<CollecstatisEntity> collecstatisEntitys) {
        Integer[] values = null;
        if (CollectionUtils.isNotEmpty(collecstatisEntitys)) {
            values = collecstatisEntitys.stream().map(x->x.getCollectData()).collect(Collectors.toList()).toArray(new  Integer[collecstatisEntitys.size()]);
        }
//        EquipmentEntity equipmentEntity = equipmentService.selectById(equipId);
//        DataEntity entity = new DataEntity();
//        entity.setId(equipId);
//        entity.setName(equipmentEntity.getName());
//        entity.setColor(equipmentEntity.getColor());
//        entity.setData(values);
//        return entity;
        return null;
    }

    private DataEntity getDataEntity(int collectType,String equipId, List<CollecstatisEntity> collecstatisEntitys) {
        Integer[] values = null;
        if (CollectionUtils.isNotEmpty(collecstatisEntitys)) {
            values = collecstatisEntitys.stream().map(x->x.getCollectData()).collect(Collectors.toList()).toArray(new  Integer[collecstatisEntitys.size()]);
        }
        EquipmentEntity equipmentEntity = equipmentService.selectById(equipId);
        EquipColorEntity equipColorEntity = equipColorService.queryByCollectType(equipId,collectType);

        DataEntity entity = new DataEntity();
        entity.setId(equipId);
        entity.setName(equipmentEntity.getName());
        if (equipColorEntity != null) {
            entity.setColor(equipColorEntity.getColor());
        }
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



    private List<AirDataEntity> getDateListMap(List<CompareDataEntity> entitys) {
        Map<Date, List<CompareDataEntity>> map =  entitys.parallelStream().collect(Collectors.groupingBy(CompareDataEntity::getDate));
        List<AirDataEntity> dataRet = new ArrayList<>();
        if (map != null) {
            map.forEach((x,y)->{
                AirDataEntity entity = new AirDataEntity();
                entity.setDate(x);
                entity.setData(y);
                dataRet.add(entity);
            });
        }
        return dataRet;
    }


    private List<CompareDataEntity> getCompareDataEntities(List<CompareDataEntity> entitys) {
        if (CollectionUtils.isNotEmpty(entitys)) {
            entitys = entitys.stream().map(x -> {
                EquipmentEntity equipmentEntity = equipmentService.selectById(x.getEquipId());
                if (equipmentEntity != null) {
                    x.setName(equipmentEntity.getName());
                }
                return x;
            }).collect(Collectors.toList());
        }
        return entitys;
    }


}
