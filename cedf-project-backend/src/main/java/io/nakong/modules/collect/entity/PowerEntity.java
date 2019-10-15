package io.nakong.modules.collect.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2019-05-29 21:58:32
 */
@TableName("t_power")
public class PowerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String equipId;
	/**
	 * 
	 */
	private String collecValue;

	@TableField(exist = false)
	private String name;

	@TableField(exist = false)
	private String unitName;
	/**
	 * 
	 */
	private Date collecTime;
	/**
	 * 
	 */
	private Date collecTimeStamp;
	/**
	 * 
	 */
	private Date insertTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

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
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	/**
	 * 获取：
	 */
	public String getEquipId() {
		return equipId;
	}
	/**
	 * 设置：
	 */
	public void setCollecValue(String collecValue) {
		this.collecValue = collecValue;
	}
	/**
	 * 获取：
	 */
	public String getCollecValue() {
		return collecValue;
	}
	/**
	 * 设置：
	 */
	public void setCollecTime(Date collecTime) {
		this.collecTime = collecTime;
	}
	/**
	 * 获取：
	 */
	public Date getCollecTime() {
		return collecTime;
	}
	/**
	 * 设置：
	 */
	public void setCollecTimeStamp(Date collecTimeStamp) {
		this.collecTimeStamp = collecTimeStamp;
	}
	/**
	 * 获取：
	 */
	public Date getCollecTimeStamp() {
		return collecTimeStamp;
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
