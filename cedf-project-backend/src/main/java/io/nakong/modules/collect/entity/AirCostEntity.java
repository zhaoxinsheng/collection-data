package io.nakong.modules.collect.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@cowain.com.cn
 * @date 2019-08-24 14:44:22
 */
@TableName("t_air_cost")
public class AirCostEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    @TableField(exist = false)
	private String t;

    public String getT() {
        return t;
    }
	public void setT(String t){
        this.t = t;
	}
		/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 单位压缩空气能耗
	 */
	private Integer unitCost;
	/**
	 * 实际流量
	 */
	private Integer actPipe;
	/**
	 * 含尘量
	 */
	private Integer inDust;
	/**
	 * 含油量
	 */
	private Integer inOil;
	/**
	 * 排气温度
	 */
	private Integer outTemp;
	/**
	 * 进气温度
	 */
	private Integer inTemp;
	/**
	 * 压力露点
	 */
	private Integer ldTemp;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：单位压缩空气能耗
	 */
	public void setUnitCost(Integer unitCost) {
		this.unitCost = unitCost;
	}
	/**
	 * 获取：单位压缩空气能耗
	 */
	public Integer getUnitCost() {
		return unitCost;
	}
	/**
	 * 设置：实际流量
	 */
	public void setActPipe(Integer actPipe) {
		this.actPipe = actPipe;
	}
	/**
	 * 获取：实际流量
	 */
	public Integer getActPipe() {
		return actPipe;
	}
	/**
	 * 设置：含尘量
	 */
	public void setInDust(Integer inDust) {
		this.inDust = inDust;
	}
	/**
	 * 获取：含尘量
	 */
	public Integer getInDust() {
		return inDust;
	}
	/**
	 * 设置：含油量
	 */
	public void setInOil(Integer inOil) {
		this.inOil = inOil;
	}
	/**
	 * 获取：含油量
	 */
	public Integer getInOil() {
		return inOil;
	}
	/**
	 * 设置：排气温度
	 */
	public void setOutTemp(Integer outTemp) {
		this.outTemp = outTemp;
	}
	/**
	 * 获取：排气温度
	 */
	public Integer getOutTemp() {
		return outTemp;
	}
	/**
	 * 设置：进气温度
	 */
	public void setInTemp(Integer inTemp) {
		this.inTemp = inTemp;
	}
	/**
	 * 获取：进气温度
	 */
	public Integer getInTemp() {
		return inTemp;
	}
	/**
	 * 设置：压力露点
	 */
	public void setLdTemp(Integer ldTemp) {
		this.ldTemp = ldTemp;
	}
	/**
	 * 获取：压力露点
	 */
	public Integer getLdTemp() {
		return ldTemp;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
