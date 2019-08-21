package io.nakong.modules.project.vo;/*
 *   阔地教育科技有限公司版权所有（codyy.com/codyy.cn）
 *   Copyright (c) 2017, Codyy and/or its affiliates. All rights              
 *    reserved.
 */

import java.util.List;

import io.nakong.modules.project.entity.AreaEntity;
import io.nakong.modules.project.entity.ReceiverEntity;

/**
 * @author maozhengwen
 * @version （注明版本号）
 * @Description: (这里用一句话描述这个类的作用)
 * @date
 */
public class ReceiverVo extends ReceiverEntity {

    private List<AreaEntity> cityList;
    private List<AreaEntity> countyList;

    public List<AreaEntity> getCityList() {
        return cityList;
    }

    public void setCityList(List<AreaEntity> cityList) {
        this.cityList = cityList;
    }

    public List<AreaEntity> getCountyList() {
        return countyList;
    }

    public void setCountyList(List<AreaEntity> countyList) {
        this.countyList = countyList;
    }
}
