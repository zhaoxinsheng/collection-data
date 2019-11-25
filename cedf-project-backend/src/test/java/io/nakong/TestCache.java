package io.nakong;

import io.nakong.common.utils.CacheUtils;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
public class TestCache {

    public static void main(String[] args) {
        CacheUtils.setValue("BY_KYZ.G1.瞬间流量FT1",1);

        System.out.println(CacheUtils.getValue("BY_KYZ.G1.瞬间流量FT1"));
    }
}
