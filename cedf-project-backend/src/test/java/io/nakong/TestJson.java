package io.nakong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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


         JSONArray array =  new JSONArray();
         JSONObject singleObject = new JSONObject();
         singleObject.put("id","0");
         singleObject.put("name","总电量");
         array.add(singleObject);




         JSONArray array2 = new JSONArray();

         JSONObject singleObject1 = new JSONObject();
         singleObject1.put("id","1");
         singleObject1.put("name","总电量1");

         JSONObject singleObject2 = new JSONObject();
         singleObject2.put("id","2");
         singleObject2.put("name","总电量2");

//         array2.add(singleObject1);
//         array2.add(0,singleObject2);

         array2.addAll( array);

         array2.add(singleObject1);

         for(int k =0;k< array2.size();k++ ){
             JSONObject o =  array2.getJSONObject(k);
             System.out.println(o.toJSONString());
         }

     }
}
