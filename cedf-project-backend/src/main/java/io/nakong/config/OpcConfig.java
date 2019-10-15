package io.nakong.config;


import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
@Component
@ConfigurationProperties(prefix = "io.nakong")
@PropertySource(value={"classpath:server.properties"}, encoding = "utf-8")
public class OpcConfig {

    protected Logger logger = LoggerFactory.getLogger(OpcConfig.class);

    private String host;

    private String user;

    private String password;

    private String clsid ;

    private String items;

    public String[] getItemDetail() {
        if (StringUtils.isNotBlank(items)) {
            return items.split(",");
        }
        return null;
    }
    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public  String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClsid() {
        return clsid;
    }

    public void setClsid(String clsid) {
        this.clsid = clsid;
    }

    public void printParam() {
        logger.info("host=%s,user=%s,password=%s,clsid=%s",host,user,password,clsid);
    }
}
