package io.nakong.modules.collect.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2019-05-29 21:58:32
 */
@TableName("t_pw_price")
public class PwPriceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private BigDecimal basePrice;
	/**
	 * 
	 */
	private BigDecimal upRate;
	/**
	 * 
	 */
	private BigDecimal totalUpRate;
	/**
	 * 
	 */
	private Date insertTime;
	/**
	 * 
	 */
	private BigDecimal costPrice;

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
	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getBasePrice() {
		return basePrice;
	}
	/**
	 * 设置：
	 */
	public void setUpRate(BigDecimal upRate) {
		this.upRate = upRate;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getUpRate() {
		return upRate;
	}
	/**
	 * 设置：
	 */
	public void setTotalUpRate(BigDecimal totalUpRate) {
		this.totalUpRate = totalUpRate;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getTotalUpRate() {
		return totalUpRate;
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
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getCostPrice() {
		return costPrice;
	}
}
