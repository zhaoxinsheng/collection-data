package io.nakong.modules.collect.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.github.pagehelper.PageInfo;
import io.nakong.common.excel.ExcelUtils;
import io.nakong.common.utils.DateUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.collect.entity.*;
import io.nakong.modules.collect.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
        List<String> dateArray = null;
        List<String> equidArray= Arrays.asList(equipIds.split(","));
        Map<Integer,List<CollecstatisEntity>> dataX = new HashMap<>();
        String unitName = getUnitName(collectType);
        Map<String,Object> retMap = null;
          //  getChartData(int dateType,  int collectType, List<String> equidArray, Map<Integer,List<CollecstatisEntity>> dataList) {
           retMap = getChartData(dateType,  collectType, equidArray, dataX);
           if (retMap != null && retMap.size() > 0) {
               dateArray = ( List<String>)retMap.get("date");
               dataX = (Map<Integer,List<CollecstatisEntity>> )retMap.get("data");
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
        // compareType  1 去年 2 上个月 3 上个季度
        String unitName = getUnitName(collectType);
        CompareDataEntity entity = null,entityLastYear = null;
        switch (compareType) {
            case 1:
                 entity = collecstatisService.getCurYearCollectData(collectType);
                 entityLastYear = collecstatisService.getLastYearCollectData(collectType);
                 if(entity != null){
                     entity.setName("今年");
                 }
                 if(entityLastYear != null) {
                     entityLastYear.setName("去年");
                 }
               break;
            case 2:
                 entity = collecstatisService.getCurMonthCollectData(collectType);
                 entityLastYear = collecstatisService.getLastMonthCollectData(collectType);
                if(entity != null){
                    entity.setName("本月");
                }
                if(entityLastYear != null) {
                    entityLastYear.setName("上个月");
                }
                break;
            case 3:
                 entity = collecstatisService.getCurQuarCollectData(collectType);
                 entityLastYear = collecstatisService.getLastQuarCollectData(collectType);
                if(entity != null){
                    entity.setName("本季度");
                }
                if(entityLastYear != null) {
                    entityLastYear.setName("上个季度");
                }
                break;
            default:
                    break;
        }

        return R.ok().put("currentData", entity).put("lastData",entityLastYear).put("unitName",unitName);
    }

    private void setName(Integer compareType, CompareDataEntity entity) {
        if ( entity != null ) {
            switch (compareType) {
                case 1:
                    entity.setName("");
                    break;
                case 2:
                    entity.setName("");
                    break;
                case 3:
                    entity.setName("");
                    break;
            }
        }
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
        //return R.ok().put("data", getCompareDataEntities(entities)).put("unitName",unitName);
        return R.ok().put("data", entities).put("unitName",unitName);
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
        return R.ok().put("data", entities).put("unitName",unitName);
    }


    /**
     *   累计流量报表
     *
     */
    @RequestMapping("/comparisonmutichart/airData/{dateType}/{startDate}/{endDate}")
    public R sumAirData(@PathVariable("dateType") int dateType,@PathVariable("startDate") String startDateStr,@PathVariable("endDate") String endDateStr){
        String unitName = getUnitName(3);

        Date startDate = null;
        Date endDate = null;
        if (dateType == 0){
              startDate = DateUtils.stringToDate(startDateStr,DateUtils.DATE_PATTERN);
              endDate = DateUtils.stringToDate(endDateStr,DateUtils.DATE_PATTERN);
        } else {
            Map<String ,Date> dateMap = getDateByCondition(dateType);
            startDate = dateMap.get("startDate");
            endDate = dateMap.get("endDate");
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
        Map<String,Date> dateMap = setDate (dateType,startDateStr ,endDateStr);

        List<CompareDataEntity>   entities = collecstatisService.sumPowerData(dateMap.get("startDate"),dateMap.get("endDate"));
        List<AirDataEntity> dataRet = new ArrayList<>();
        if (entities != null) {
            dataRet = getDateListMap(entities);
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


        Map<String,Date> dateMap =  setDate (dateType,startDateStr ,endDateStr);

        List<CompareDataEntity>   entitys = null;//collecstatisService.sumTempData(startDate,endDate);
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
        Map<String,Date> dateMap = setDate (dateType,startDateStr ,endDateStr );

        List<CompareDataEntity>   entitys = collecstatisService.alarmData(dateMap.get("startDate"),dateMap.get("endDate"));
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

        Map<String,Date> dateMap = setDate(dateType,startDateStr ,endDateStr);

        List<CompareDataEntity>   entitys = collecstatisService.airPowerRate(dateMap.get("startDate"),dateMap.get("endDate"));
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
    @RequestMapping("/comparisonmutichart/pressData")
    public R pressData(@RequestParam Map<String, Object> params){
        io.nakong.common.page.Page<CompareDataEntity>  pageParam = getDateMap(params);
//        String unitName = getUnitName(1);

        List<CompareDataEntity>   entitys = collecstatisService.pressDataPageList(pageParam);
        entitys = getEntities(entitys);
        PageUtils pageUtils  = new PageUtils(entitys,pageParam.getTotal(),getLimit(params),getCurrentPage(params));
        return R.ok().put("page", pageUtils);
    }
    /**
     *   流量报表
     *
     */
    @RequestMapping("/comparisonmutichart/pipeData")
    public R sumPipeData(@RequestParam Map<String, Object> params){

        io.nakong.common.page.Page<CompareDataEntity>  pageParam = getDateMap(params);

        List<CompareDataEntity> entitys =  collecstatisService.sumPipePageList(pageParam);
        pageParam.setData(entitys);

//        if (CollectionUtils.isNotEmpty(entitys)) {
//            for(CompareDataEntity compareDataEntity : entitys){
//                compareDataEntity.setUnitName("m3");
//                // 查询变量名称
//                compareDataEntity.setName("累积流量FT7");
//            }
//        }
        getEntities(entitys);
        PageUtils pageUtils  = new PageUtils(entitys,pageParam.getTotal(),getLimit(params),getCurrentPage(params));
        return R.ok().put("page", pageUtils);
    }

    /**
     *   耗电量数据
     *
     */
    @RequestMapping("/comparisonmutichart/powerDetailData")
    public R powerData(@RequestParam Map<String, Object> params){

        io.nakong.common.page.Page<CompareDataEntity>  pageParam = getDateMap(params);
        String unitName = getUnitName(1);

        List<CompareDataEntity>   entities = collecstatisService.powerDataPageList(pageParam);
        getEntities(entities);
        PageUtils pageUtils  = new PageUtils(entities,pageParam.getTotal(),getLimit(params),getCurrentPage(params));
        return R.ok().put("page", pageUtils);

    }

    /**
     *   压力露点数据
     *
     */
    @RequestMapping("/comparisonmutichart/ldData")
    public R ldData(@RequestParam Map<String, Object> params){
        io.nakong.common.page.Page<CompareDataEntity>  pageParam = getDateMap(params);
        List<CompareDataEntity>   entities = collecstatisService.ldDataPageList(pageParam);
        getEntities(entities);
        PageUtils pageUtils  = new PageUtils(entities,pageParam.getTotal(), getLimit(params),getCurrentPage(params));
        return R.ok().put("page", pageUtils);
    }


    /**
     *   温度数据
     *
     */
    @RequestMapping("/comparisonmutichart/tempDetailData")
    public R tempData(@RequestParam Map<String, Object> params){
        io.nakong.common.page.Page<CompareDataEntity>  pageParam = getDateMap(params);
        List<CompareDataEntity>   entities = collecstatisService.tempDataPageList(pageParam);
        getEntities(entities);
        PageUtils pageUtils  = new PageUtils(entities,pageParam.getTotal(),getLimit(params),getCurrentPage(params));
        return R.ok().put("page", pageUtils);
    }


    /**
     *   气电比数据
     *
     */
    @RequestMapping("/comparisonmutichart/powerRateData")
    public R powerRateData(@RequestParam Map<String, Object> params){

        io.nakong.common.page.Page<CompareDataEntity>  pageParam = getDateMap(params);
        pageParam.getMap().put("id",String.valueOf(params.get("deviceId")));
        List<CompareDataEntity>   entities = null;
        if ("0".equals(String.valueOf(params.get("deviceId")))) {
           // 查询所有数据的和
            entities = collecstatisService.powerAllRatePageList(pageParam);
            if(CollectionUtils.isNotEmpty(entities)) {
                for (CompareDataEntity entity : entities) {
                    entity.setName("总电量");
                }
            }
        } else {
            entities = collecstatisService.powerRatePageList(pageParam);
        }
        PageUtils pageUtils  = new PageUtils(entities,pageParam.getTotal(),getLimit(params),getCurrentPage(params));
        return R.ok().put("page", pageUtils);

    }

    private int getLimit(Map params) {
        return  params.get("limit") == null ? 0 :  Integer.parseInt((String)params.get("limit"));

    }

    private int getCurrentPage(Map params) {
        return   params.get("page") == null ? 0 : Integer.parseInt((String)params.get("page"));
    }

    private io.nakong.common.page.Page<CompareDataEntity> getDateMap(Map params){
        int currentPage =  params.get("page") == null ? 0 : Integer.parseInt((String)params.get("page"));
        int limit =  params.get("limit") == null ? 0 :  Integer.parseInt(String.valueOf(params.get("limit")));
        String startDateStr = String.valueOf(params.get("startDate"));
        String endDateStr = String.valueOf(params.get("endDate"));
        String deviceId = String.valueOf(params.get("deviceId"));

        Map<String,Date> dateMap = setSimpleDate(startDateStr,endDateStr);
        Map<String, Serializable> map = new HashMap<>();
        map.put("startDate",dateMap.get("startDate"));
        map.put("endDate",dateMap.get("endDate"));
        map.put("id",deviceId);
        io.nakong.common.page.Page<CompareDataEntity> pageParam = new io.nakong.common.page.Page<CompareDataEntity>();
        pageParam.setPaging(true);
        pageParam.setMap(map);
        pageParam.setStart((currentPage - 1 ) * limit);
        pageParam.setEnd(currentPage * limit - 1);

        return pageParam;
    }

    /**
     * 导出 excel 使用的查询组装方法
     * @param
     * @return
     */

    private io.nakong.common.page.Page<CompareDataEntity> getParamMap(HttpServletRequest request){
        int currentPage =  request.getParameter("page") == null ? 0 : Integer.parseInt(String.valueOf(request.getParameter("page")));
//        int limit =  request.getParameter("limit") == null ? 0 :  Integer.parseInt(String.valueOf(request.getParameter("limit")));
        int limit = 60000;
        String startDateStr = String.valueOf(request.getParameter("startDate"));
        String endDateStr = String.valueOf(request.getParameter("endDate") );
        Map<String,Date> dateMap = setSimpleDate(startDateStr,endDateStr);
        Map<String, Serializable> map = new HashMap<>();
        map.put("startDate",dateMap.get("startDate"));
        map.put("endDate",dateMap.get("endDate"));
        io.nakong.common.page.Page<CompareDataEntity> pageParam = new io.nakong.common.page.Page<CompareDataEntity>();
        pageParam.setPaging(true);
        pageParam.setMap(map);
        pageParam.setStart((currentPage - 1 ) * limit);
        pageParam.setEnd(currentPage * limit - 1);

        return pageParam;
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
        Map<String,Date> dateMap = setDate (dateType,startDateStr ,endDateStr);

        String unitName = getUnitName(collectType);
        if (dateType != 0) {
//            dateArray = getChartData(dateType, dateArray, collectType, equidArray, dataX);
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

//        Date startDate = new Date();
//        Date endDate = new Date();
        Map<String,Date> dateMap = setDate (dateType,startDateStr ,endDateStr);

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
    private Map<String,Date> setDate (int dateType,String startDateStr ,String endDateStr) {

        if (dateType == 0){
              Date startDate = DateUtils.stringToDate(startDateStr,DateUtils.DATE_PATTERN);
              Date endDate = DateUtils.stringToDate(endDateStr,DateUtils.DATE_PATTERN);
              return wrapMap(startDate,endDate);
        } else {
            return getDateByCondition(dateType);
        }
    }

    private Map<String,Date>  wrapMap(java.util.Date startDate, java.util.Date endDate ) {
        Map<java.lang.String, java.util.Date> dateMap = new HashMap<>();
        dateMap.put("startDate",startDate);
        dateMap.put("endDate",endDate);
        return dateMap;
    }

    /**
     * 获取开始时间 和结束时间
     * @param startDateStr
     * @param endDateStr
     */
    private Map<String,Date> setSimpleDate (String startDateStr ,String endDateStr ) {
          Date  startDate = DateUtils.stringToDate(startDateStr,DateUtils.DATE_PATTERN);
          Date  endDate = DateUtils.stringToDate(endDateStr,DateUtils.DATE_PATTERN);
          return wrapMap(startDate,endDate);

    }
    /**
     * 根据条件返回 开始时间和结束时间
     */
    private Map<String ,Date> getDateByCondition (int dateType) {
        if (dateType == 0) {
            return null;
        }
        Date newStartDate = new Date();
        Date newEndDate = new Date();

        switch (dateType) {
            case 1:
                newStartDate = DateUtils.getStartTimeofCurrentDay();
                newEndDate = DateUtils.getEndTimeofCurrentDay();
                break;
            case 2:
                newStartDate = DateUtils.getFirstDayofWeek();
                newEndDate = DateUtils.getEndDayOfWeek();
                break;
            case 3:
                newStartDate = DateUtils.getFirstDayOfMonth();
                newEndDate = DateUtils.getEndDayOfMonth();
                break;
            case 4:
                newStartDate = DateUtils.getFirstDayOfYear();
                newEndDate = DateUtils.getEndDayOfYear();

                break;
            default:
                break;
        }

        return wrapMap(newStartDate,newEndDate);
    }

    private Map<String,Object>  getChartData(int dateType,  int collectType, List<String> equidArray, Map<Integer,List<CollecstatisEntity>> dataList) {
        Date startDate;
        Date endDate;
        List<String> dateList = null;
        Map<String,Object> retMap = new HashMap<>();

        switch (dateType) {
            // 当天统计
            case 1:
                // dateArray = DateUtils.getDayHours();
                startDate = DateUtils.getStartTimeofCurrentDay();
                endDate = DateUtils.getEndTimeofCurrentDay();
                List<CollecstatisEntity>   retDayList = collecstatisService.comparisonChartByDay(collectType, equidArray, startDate, endDate);
                if (CollectionUtils.isNotEmpty(retDayList)) {
                    dataList = retDayList.stream().collect(Collectors.groupingBy(CollecstatisEntity::getEquipId));
                    dateList = retDayList.stream().map(x->x.getDateStr()).distinct().collect(Collectors.toList());
                }
                retMap.put("data",dataList);
                retMap.put("date",dateList);
                break;
            case 2:
                //本周 统计
               // dateArray = DateUtils.getDayOfWeek();
                startDate = DateUtils.getFirstDayofWeek();
                endDate = DateUtils.getEndDayOfWeek();

                List<CollecstatisEntity>   retWeekList = collecstatisService.comparisonChartByWeek(collectType, equidArray, startDate, endDate);
                if (CollectionUtils.isNotEmpty(retWeekList)) {
                    dataList = retWeekList.stream().collect(Collectors.groupingBy(CollecstatisEntity::getEquipId));
                    dateList = retWeekList.stream().map(x->x.getDateStr()).distinct().collect(Collectors.toList());
                }
                retMap.put("data",dataList);
                retMap.put("date",dateList);

//                for (String equipId : equidArray) {
//                    DataEntity entity = getDataEntity(collectType,equipId, collecstatisService.comparisonChartByWeek(collectType, equipId, startDate, endDate));
//                    dataX.add(entity);
//                }
                break;
            case 3:
                //本月 统计
//                dateArray = DateUtils.getDayOfMonth();
                startDate = DateUtils.getFirstDayOfMonth();
                endDate = DateUtils.getEndDayOfMonth();
//                for (String equipId : equidArray) {
////                    DataEntity entity = getDataEntity(collectType,equipId, collecstatisService.comparisonChartByMonth(collectType, equipId, startDate, endDate));
////                    dataX.add(entity);
////                }

                List<CollecstatisEntity>   retMonthList = collecstatisService.comparisonChartByMonth(collectType, equidArray, startDate, endDate);
                if (CollectionUtils.isNotEmpty(retMonthList)) {
                    dataList = retMonthList.stream().collect(Collectors.groupingBy(CollecstatisEntity::getEquipId));
                    dateList = retMonthList.stream().map(x->x.getDateStr()).distinct().collect(Collectors.toList());
                }
                retMap.put("data",dataList);
                retMap.put("date",dateList);
                break;
            case 4:
                //本年 统计
//                dateArray = DateUtils.getMonthOfYear();
                startDate = DateUtils.getFirstDayOfYear();
                endDate = DateUtils.getEndDayOfYear();
                List<CollecstatisEntity>   retYearList = collecstatisService.comparisonChartByYear(collectType, equidArray, startDate, endDate);
                if (CollectionUtils.isNotEmpty(retYearList)) {
                    dataList = retYearList.stream().collect(Collectors.groupingBy(CollecstatisEntity::getEquipId));
                    dateList = retYearList.stream().map(x->x.getDateStr()).distinct().collect(Collectors.toList());
                }
//                for (String equipId : equidArray) {
//                    DataEntity entity = getDataEntity(collectType,equipId, collecstatisService.comparisonChartByYear(collectType, equipId, startDate, endDate));
//                    dataX.add(entity);
//                }
                retMap.put("data",dataList);
                retMap.put("date",dateList);
                break;
            default:
                break;
        }
        return retMap;
    }


    private List<CompareDataEntity>  getEntities(List<CompareDataEntity> entities) {
        List<CompareDataEntity>  entities2 = entities;
        if (CollectionUtils.isNotEmpty(entities)) {
            entities2 = entities.stream().map(x -> {
                EquipColorEntity equipColorEntity = equipColorService.selectById(x.getEquipId());
                if (equipColorEntity != null) {
                    x.setName(equipColorEntity.getVarname());
                    x.setUnitName(equipColorEntity.getUnit());
                }
                return x;
            }).collect(Collectors.toList());
        }
        return entities2;
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


    @RequestMapping("/export")
    public void orderExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("content-Type", "application/vnd.ms-excel");
        String excelFileName = "export-"+ DateUtils.format(new Date()) +".xlsx";
        response.setHeader("Content-Disposition", "attachment;filename=" + excelFileName);
        int type = request.getParameter("type") == null ? 0 : Integer.parseInt(request.getParameter("type"));
        // excel 一个sheet 最大导出行60000行

        io.nakong.common.page.Page<CompareDataEntity>  pageParam =  getParamMap(request);
        List<CompareDataEntity>   entities = null;
        switch (type){
            case 1:
                // 压力数据
               entities = collecstatisService.pressDataPageList(pageParam);
                break;
            case 2:
                // 流量数据
               entities = collecstatisService.sumPipePageList(pageParam);
                break;
            case 3:
                // 耗电量数据
                entities = collecstatisService.powerDataPageList(pageParam);
                break;
            case 4:
                // 压力露点数据
                entities = collecstatisService.ldDataPageList(pageParam);
                break;
            case 5:
                // 温度数据
                entities = collecstatisService.tempDataPageList(pageParam);
                break;
            default:
                break;
        }
        getEntities(entities);
        ExcelUtils.writeExcel(response, entities, CompareDataEntity.class);
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
                unitName = "Bar";
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
        Map<String, List<CompareDataEntity>> map =  entitys.parallelStream().collect(Collectors.groupingBy(CompareDataEntity::getDateStr));
        List<AirDataEntity> dataRet = new ArrayList<>();
        if (map != null) {
            map.forEach((x,y)->{
                AirDataEntity entity = new AirDataEntity();
                entity.setDateStr(x);
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
