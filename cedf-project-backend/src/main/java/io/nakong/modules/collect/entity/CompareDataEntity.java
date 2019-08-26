package io.nakong.modules.collect.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
public class CompareDataEntity implements  java.io.Serializable{
    private String equipId;
    private String name;
    private Integer data;
    private Date date;
    private Integer powerData;

    private BigDecimal powerPrice;
    // 气电比
    private Integer rate;

    private String unitName;


    public BigDecimal getPowerPrice() {
        return powerPrice;
    }

    public void setPowerPrice(BigDecimal powerPrice) {
        this.powerPrice = powerPrice;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getPowerData() {
        return powerData;
    }

    public void setPowerData(Integer powerData) {
        this.powerData = powerData;
    }

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}