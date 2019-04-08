package io.nakong.modules.collect.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-06-28 20:20:43
 */
@TableName("tb_equipment_type")
public class EquipmentTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 设备类型编号
	 */
	private Integer enquType;
	/**
	 * 设备名称
	 */
	private String enquName;
	/**
	 * 
	 */
	private String insertUser;
	/**
	 * 
	 */
	private Date insertTime;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：设备类型编号
	 */
	public void setEnquType(Integer enquType) {
		this.enquType = enquType;
	}
	/**
	 * 获取：设备类型编号
	 */
	public Integer getEnquType() {
		return enquType;
	}
	/**
	 * 设置：设备名称
	 */
	public void setEnquName(String enquName) {
		this.enquName = enquName;
	}
	/**
	 * 获取：设备名称
	 */
	public String getEnquName() {
		return enquName;
	}
	/**
	 * 设置：
	 */
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	/**
	 * 获取：
	 */
	public String getInsertUser() {
		return insertUser;
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
