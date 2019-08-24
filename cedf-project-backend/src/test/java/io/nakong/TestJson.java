package io.nakong;

import com.alibaba.fastjson.JSON;
import io.nakong.common.utils.R;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version 1: AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
public class TestJson {

     public static void main (String [] args) {

         ArrayList list  = new ArrayList();

//         JSON.JSON
         for (int i = 0 ;i< 4 ;i++) {
            Map map = new HashMap<>();
            map.put("id",1);
            map.put("name","测试");

            list.add(map);
         }

         System.out.println(JSON.toJSONString( R.ok().put("data",list)));
     }
}
