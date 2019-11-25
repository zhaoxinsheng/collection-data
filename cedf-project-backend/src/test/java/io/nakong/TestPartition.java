package io.nakong;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import java.util.List;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
public class TestPartition {

    public static void main(String [] args) {
        String sql = "" +
                "ALTER TABLE " ;
         String sql2 =  "  PARTITION BY RANGE (Month(insert_time)) ( \n" +
                "";
        String month = "t_press     ,"
       + "t_pipe      ,"
                + "t_pipe_lj   ,"
                +"t_pipe_ss   ,"
                +"t_power     ,"
                +"t_press     ,"
                +"t_press_ld  ,"
                +"t_temp      ";

        List<String> months = Splitter.on(",").splitToList(month);
        StringBuilder sb = new StringBuilder();

        for (String m : months) {
            sb.append(sql);
            sb.append(m + "\n");
            sb.append(sql2);
            for(int index = 19 ;index <= 20;index ++ ){

                for(int col = 1 ;col <= 11 ;col++){
                    String year = "20" + index ;
                    year =   year + lpadString(col + "");
                    String value = year  + "";
                    sb.append("PARTITION " +  "p"+ year + " VALUES LESS THAN (to_days('" + value + "')), \n");
                }
            }

            for(int index = 20 ;index <= 20;index ++ ){

                for(int col = 1 ;col <= 11 ;col++){
                    String year = "20" + index ;
                    year =   year + lpadString(col + "");
                    String value = year  + "";
                    sb.append("PARTITION " +  "p"+ year + " VALUES LESS THAN (to_days('" + value + "')), \n");
                }
            }
            sb.append("PARTITION "+  "p202012" +" VALUES LESS THAN (MAXVALUE)) ;");
            System.out.println(sb.toString());
            sb = new StringBuilder();
        }

    }

    public static String lpadString(String value) {
       return  Strings.padStart(value, 2, '0');
    }
}
