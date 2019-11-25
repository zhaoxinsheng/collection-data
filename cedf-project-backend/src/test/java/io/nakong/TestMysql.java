package io.nakong;

import com.google.common.base.Strings;

import java.util.Calendar;
import java.util.Date;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
public class TestMysql {

    public static void main(String [] args) {
        // 2019-09-08
        Calendar cl = Calendar.getInstance();
        cl.set(2019,9,8);



//        System.out.println("year = "+ year  + ",month = " + month + ",day = " + day);
//        cl.add(Calendar.DATE, 7);
//        String week = cl.get(Calendar.YEAR) + "" + cl.get(Calendar.WEEK_OF_YEAR);
//        // System.out.print(week);
//        week = lpadString(week);
//        String table = "t_pipe_lj                       ,"  +
//                "t_pipe_ss                       ,"  +
//                "t_power                         ,"  +
//                "t_press                         ,"  +
//                "t_press_ld                      ,"  +
//                "t_temp                          ,"  +
//                "t_collect_statis                ,"  +
//                "t_collect_statis_day            ,"  +
//                "t_collect_statis_month          ,"  +
//                "t_collect_statis_week           ,"  +
//                "t_collect_statis_year           ,"  ;

        String table = "t_pipe";

        String[] tables = table.split(",");
        for (String t : tables ) {
            StringBuilder sb = new StringBuilder();
            sb.append("ALTER TABLE "+ t.trim() +" PARTITION BY RANGE (TO_DAYS(insert_time)) (  \n");
            for (int yearweek = 201936; yearweek <= 203099; yearweek = yearweek + 7) {
                cl.add(Calendar.DATE, 7);
                int year = cl.get(Calendar.YEAR);
                String month = lpadString(cl.get(Calendar.MONTH) + "");
                String day = lpadString(cl.get(Calendar.DATE) +"");
                String prefix = year + "" + month + day;
                String week = cl.get(Calendar.YEAR) + "-" + lpadString(String.valueOf(cl.get(Calendar.MONTH))) +
                        "-"+ lpadString(String.valueOf(cl.get(Calendar.DATE)));
                // System.out.print(week);
                //
                if (prefix.equals("20200105") || prefix.equals("20210012") || prefix.equals("20201019")
                        || prefix.equals("20210026")  || prefix.equals("20220431") ||
                        prefix.equals("20200231 ") || prefix.equals("20191131") || prefix.equals("2020007")
                        || prefix.equals("20200014")
                        || prefix.equals("20200021") || prefix.equals("20200028")
                        || prefix.equals("20200204 ") || prefix.equals("20220011")
                        || prefix.equals("20220018") || prefix.equals("20220025")
                        || prefix.equals("20220229") || prefix.equals("20200231")
                        || prefix.equals("20220004") || prefix.equals("20210230")
                        || prefix.equals("20210005") || prefix.equals("20210019") ||   prefix.equals("20200007")
                ) {
                    continue;
                }
                sb.append(" PARTITION p_"+prefix + "   VALUES LESS THAN (TO_DAYS('"+week+"')), \n");
            }
            sb.append("  PARTITION p_max VALUES LESS THAN MAXVALUE );");
            System.out.println(sb.toString());
            cl.set(2019,9,8);
        }

    }

    public static String lpadString(String value) {
        return  Strings.padStart(value, 2, '0');
    }
}


