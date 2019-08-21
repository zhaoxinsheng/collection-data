package io.nakong.modules.collect.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2019-06-26 22:31:38
 */
@TableName("t_collec_perid")
public class CollecPeridEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String equipmentId;
	/**
	 * 1 电力 2 温度 3 压力 
	 */
	private Integer peridType;
	/**
	 * 采集频率
	 */
	private Integer peridValue;
	/**
	 * 
	 */
	private Date insertTime;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	/**
	 * 获取：
	 */
	public String getEquipmentId() {
		return equipmentId;
	}
	/**
	 * 设置：1 电力 2 温度 3 压力 
	 */
	public void setPeridType(Integer peridType) {
		this.peridType = peridType;
	}
	/**
	 * 获取：1 电力 2 温度 3 压力 
	 */
	public Integer getPeridType() {
		return peridType;
	}
	/**
	 * 设置：采集频率
	 */
	public void setPeridValue(Integer peridValue) {
		this.peridValue = peridValue;
	}
	/**
	 * 获取：采集频率
	 */
	public Integer getPeridValue() {
		return peridValue;
	}
	/**
	 * 设置：
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * 获取：
	 */
	public Date getInsertTime() {
		return insertTime;
	}
}
