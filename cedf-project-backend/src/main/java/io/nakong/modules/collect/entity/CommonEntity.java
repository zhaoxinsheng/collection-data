package io.nakong.modules.collect.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tom
 */
public class CommonEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * x 坐标值
     */
    private List<Integer> xDataList = new ArrayList<>();
    /**
     *  统计数据
     */
    private List<Float> dataList = new ArrayList<>();

    /**
     * 平均值
     */
    private Float avgData;

    /**
     * 总的值
     */
    private Float totalData;

    public Float getTotalData() {
        return totalData;
    }

    public void setTotalData(Float totalData) {
        this.totalData = totalData;
    }

    public List<Integer> getxDataList() {
        return xDataList;
    }

    public void setxDataList(List<Integer> xDataList) {
        this.xDataList = xDataList;
    }

    public List<Float> getDataList() {
        return dataList;
    }

    public void setDataList(List<Float> dataList) {
        this.dataList = dataList;
    }

    public Float getAvgData() {
        return avgData;
    }

    public void setAvgData(Float avgData) {
        this.avgData = avgData;
    }
}
