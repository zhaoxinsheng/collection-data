package io.nakong.modules.collect.controller;

/**
 * @author tom
 */

import io.nakong.common.utils.DateUtils;
import io.nakong.common.utils.R;
import io.nakong.modules.collect.entity.CommonEntity;
import io.nakong.modules.collect.service.LineService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("collect/line")
public class LineController {

    @Autowired
    LineService lineService;
    /**
     *  压力曲线 列表
     */
    @RequestMapping("/press/{type}/{date}")
    public R pressList(@PathVariable("type") Integer type,@PathVariable("date") String date){
        // type =1 按天 统计 type = 2 按月统计 type = 3 按照年统计
        String dateStr = getDateByType(type,date);
        CommonEntity commonEntity = null;
        if (type == 1 ) {
            commonEntity =  lineService.getPressDayList(dateStr);
        } else if (type == 2) {
            commonEntity =  lineService.getPressMonthList(dateStr);
        } else if (type == 3 ) {
            commonEntity =  lineService.getPressYearList(dateStr);
        }
        return R.ok().put("data", commonEntity);
    }


    /**
     *  流量曲线 列表
     */
    @RequestMapping("/pipe/{type}/{date}")
    public R pipeList(@PathVariable("type") Integer type,@PathVariable("date") String date){
        // type =1 按天 统计 type = 2 按月统计 type = 3 按照年统计
        String dateStr = getDateByType(type,date);
        CommonEntity commonEntity = null;
        if (type == 1 ) {
            commonEntity =  lineService.getPipeDayList(dateStr);
        } else if (type == 2) {
            commonEntity =  lineService.getPipeMonthList(dateStr);
        } else if (type == 3 ) {
            commonEntity =  lineService.getPipeYearList(dateStr);
        }
        return R.ok().put("data", commonEntity);
    }


    /**
     *  电量曲线 列表
     */
    @RequestMapping("/power/{type}/{date}")
    public R powerList(@PathVariable("type") Integer type,@PathVariable("date") String date){
        // type =1 按天 统计 type = 2 按月统计 type = 3 按照年统计
        String dateStr = getDateByType(type,date);
        CommonEntity commonEntity = null;
        if (type == 1 ) {
            commonEntity =  lineService.getPowerDayList(dateStr);
        } else if (type == 2) {
            commonEntity =  lineService.getPowerMonthList(dateStr);
        } else if (type == 3 ) {
            commonEntity =  lineService.getPowerYearList(dateStr);
        }
        return R.ok().put("data", commonEntity);
    }




    /**
     * 温度曲线
     * @param type
     * @return
     */
    @RequestMapping("/temp/{type}")
    public R tempList(@PathVariable("type") Integer type,@PathVariable("date") String date){
        // type =1 按天 统计 type = 2 按月统计 type = 3 按照年统计
        CommonEntity commonEntity = null;
        String dateStr = getDateByType(type,date);
        if (type == 1 ) {
            commonEntity =  lineService.getTempDayList(dateStr);
        } else if (type == 2) {
            commonEntity =  lineService.getTempMonthList(dateStr);
        } else if (type == 3 ) {
            commonEntity =  lineService.getTempYearList(dateStr);
        }
        return R.ok().put("data", commonEntity);
    }

    /**
     * 压力露点曲线
     * @param type
     * @return
     */
    @RequestMapping("/templd/{type}")
    public R tempLdList(@PathVariable("type") Integer type,@PathVariable("date") String date){
        // type =1 按天 统计 type = 2 按月统计 type = 3 按照年统计
        CommonEntity commonEntity = null;
        String dateStr = getDateByType(type,date);
        if (type == 1 ) {
            commonEntity =  lineService.getTempLdDayList(dateStr);
        } else if (type == 2) {
            commonEntity =  lineService.getTempLdMonthList(dateStr);
        } else if (type == 3 ) {
            commonEntity =  lineService.getTempLdYearList(dateStr);
        }
        return R.ok().put("data", commonEntity);
    }

    public String getDateByType(Integer type,String date){
         if (StringUtils.isEmpty(date) || "undefined".equals(date)){
             // 今天
             if (type == 1 ) {
                 return DateUtils.format(new Date(),"yyyy-MM-dd");
             } else if (type == 2 ) {
                 return DateUtils.format(new Date(),"yyyy-MM");
             } else if (type == 3 ) {
                return  DateUtils.format(new Date(),"yyyy");
             }
         }
         return date;
    }
}
