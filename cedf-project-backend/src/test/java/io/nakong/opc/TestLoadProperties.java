package io.nakong.opc;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
public class TestLoadProperties {

    public static void main(String[] args) throws IOException {
//        Properties p  = PropertiesLoaderUtils.loadAllProperties("server.properties");
//        System.out.println(p.stringPropertyNames());

         TestItems test = new  TestItems();
          InputStream stream = test.getClass().getResourceAsStream("server.properties");
        Properties p = new Properties();
        p.load(stream);
        System.out.println(p.stringPropertyNames());

    }
}
