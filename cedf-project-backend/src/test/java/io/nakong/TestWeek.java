package io.nakong;

import io.nakong.common.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
public class TestWeek {

    public static void main(String[] args) {


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//        System.out.println(format.format(DateUtils.getLastMonthBeginDate()));
//
//
//        System.out.println(format.format(DateUtils.getLastMonthEndDay()));
//
        System.out.println(format.format(DateUtils.getStartDateOfQuarter()));

        System.out.println(format.format(DateUtils.getEndDateOfQuarter()));
    }
}
//        System.out.println(format.format(DateUtils.getEndDayOfLastYear()));
//        System.out.println(format.format(DateUtils.getFirstDayOfLastYear()));


//
//        System.out.println(format.format(DateUtils.getEndDayOfYear()));
//
//        System.out.println(format.format(DateUtils.getFirstDayOfYear()));
//


//        System.out.println(format.format(DateUtils.getFirstDayofWeek()));
//        System.out.println(format.format(DateUtils.getEndDayOfWeek()));
//        System.out.println(format.format(DateUtils.getFirstDayOfMonth()));
//        System.out.println(format.format(DateUtils.getEndDayOfMonth()));


//       Arrays.stream(DateUtils.getMonthOfYear()).forEach( (x)->System.out.println(x));
//        Arrays.stream(DateUtils.getDayHours()).forEach( (x)->System.out.println(x));
//        Arrays.stream(DateUtils.getDayOfWeek()).forEach( (x)->System.out.println(x));
//        Arrays.stream(DateUtils.getDayOfMonth()).forEach( (x)->System.out.println(x));
//    }