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
@TableName("tb_duty_detail")
public class DutyDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer dutyId;
	/**
	 * 
	 */
	private Date loginDate;
	/**
	 * 
	 */
	private Date endDate;
	/**
	 * 
	 */
	private Integer dutyStatus;
	/**
	 * 
	 */
	private Date insertTime;
	/**
	 * 
	 */
	private String insertUser;

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
	 * 设置：
	 */
	public void setDutyId(Integer dutyId) {
		this.dutyId = dutyId;
	}
	/**
	 * 获取：
	 */
	public Integer getDutyId() {
		return dutyId;
	}
	/**
	 * 设置：
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	/**
	 * 获取：
	 */
	public Date getLoginDate() {
		return loginDate;
	}
	/**
	 * 设置：
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置：
	 */
	public void setDutyStatus(Integer dutyStatus) {
		this.dutyStatus = dutyStatus;
	}
	/**
	 * 获取：
	 */
	public Integer getDutyStatus() {
		return dutyStatus;
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
}
