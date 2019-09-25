package io.nakong.modules.collect.entity;

import java.util.Date;
import java.util.List;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
public class AirDataEntity  implements  java.io.Serializable{

   private Date date;

    private String dateStr;

   private List<CompareDataEntity> data;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CompareDataEntity> getData() {
        return data;
    }

    public void setData(List<CompareDataEntity> data) {
        this.data = data;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
}
