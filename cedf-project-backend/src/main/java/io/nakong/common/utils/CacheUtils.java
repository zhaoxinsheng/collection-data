package io.nakong.common.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang.StringUtils;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
public class CacheUtils {

    static Cache<String,Integer> paramCache = CacheBuilder.newBuilder().maximumSize(2000).build();

    /**
     * 设置缓存值
     * @param key
     * @param value
     */
    public static void setValue(String key ,Integer value) {
       if(paramCache.getIfPresent(key) != null && paramCache.getIfPresent(key) > 0) {
           paramCache.invalidate(key);
           paramCache.put(key,value);
       } else {
           paramCache.put(key,value);
       }
    }

    /**
     * 获取到cache 中的值
     * @return
     */
    public static Integer getValue(String key) {
        return paramCache.getIfPresent(key);
    }
}
