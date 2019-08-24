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
 * @date 2019-08-24 13:29:25
 */
@TableName("t_equip_color")
public class EquipColorEntity implements Serializable {
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
	 * 设备id
	 */
	private Integer equipId;
	/**
	 * 显示颜色
	 */
	private String color;
	/**
	 * collectType 1 压力 Pa  2 电量  kW·h 3 流量 m³ 4 温度 ℃
	 */
	private Integer collectType;
	/**
	 * 采集单位  collectType 1 压力 Pa  2 电量  kW·h 3 流量 m³ 4 温度 ℃
	 */
	private String unit;
	/**
	 * 插入时间
	 */
	private Date insertTime;

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
	 * 设置：设备id
	 */
	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}
	/**
	 * 获取：设备id
	 */
	public Integer getEquipId() {
		return equipId;
	}
	/**
	 * 设置：显示颜色
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * 获取：显示颜色
	 */
	public String getColor() {
		return color;
	}
	/**
	 * 设置：collectType 1 压力 Pa  2 电量  kW·h 3 流量 m³ 4 温度 ℃
	 */
	public void setCollectType(Integer collectType) {
		this.collectType = collectType;
	}
	/**
	 * 获取：collectType 1 压力 Pa  2 电量  kW·h 3 流量 m³ 4 温度 ℃
	 */
	public Integer getCollectType() {
		return collectType;
	}
	/**
	 * 设置：采集单位  collectType 1 压力 Pa  2 电量  kW·h 3 流量 m³ 4 温度 ℃
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 获取：采集单位  collectType 1 压力 Pa  2 电量  kW·h 3 流量 m³ 4 温度 ℃
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * 设置：插入时间
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * 获取：插入时间
	 */
	public Date getInsertTime() {
		return insertTime;
	}
}
