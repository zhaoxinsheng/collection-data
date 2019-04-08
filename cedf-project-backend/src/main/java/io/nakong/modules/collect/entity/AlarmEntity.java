package io.nakong.modules.collect.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("tb_alarm")
public class AlarmEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer alarmType;
	/**
	 * 
	 */
	private String alarmName;
	/**
	 * 
	 */
	private BigDecimal maxLimit;
	/**
	 * 
	 */
	private BigDecimal minLimit;
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
	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}
	/**
	 * 获取：
	 */
	public Integer getAlarmType() {
		return alarmType;
	}
	/**
	 * 设置：
	 */
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}
	/**
	 * 获取：
	 */
	public String getAlarmName() {
		return alarmName;
	}
	/**
	 * 设置：
	 */
	public void setMaxLimit(BigDecimal maxLimit) {
		this.maxLimit = maxLimit;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getMaxLimit() {
		return maxLimit;
	}
	/**
	 * 设置：
	 */
	public void setMinLimit(BigDecimal minLimit) {
		this.minLimit = minLimit;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getMinLimit() {
		return minLimit;
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
