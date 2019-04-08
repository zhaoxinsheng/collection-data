package io.nakong.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-10-17 18:33:34
 */
@Validated
@TableName("tb_project_normal")
public class ProjectNormalEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 捐款人
	 */
	@NotNull(message = "捐款人不能为空")
	private String donatorName;
	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 捐赠金额
	 */
	@NotNull(message = "到账金额不能为空")
	private BigDecimal cashAmount;
	/**
	 * 到账日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "到账日期不能为空")
	private Date donateDate;
	/**
	 * 
	 */
	private BigDecimal withAmount;
	/**
	 * 支取日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date withDate;
	/**
	 * 插入日期
	 */
	private Date insertTime;
	/**
	 * 插入人
	 */
	private Long insertUser;

	/**
	 * 设置：捐款人
	 */
	public void setDonatorName(String donatorName) {
		this.donatorName = donatorName;
	}
	/**
	 * 获取：捐款人
	 */
	public String getDonatorName() {
		return donatorName;
	}
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
	 * 设置：捐赠金额
	 */
	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}
	/**
	 * 获取：捐赠金额
	 */
	public BigDecimal getCashAmount() {
		return cashAmount;
	}
	/**
	 * 设置：到账日期
	 */
	public void setDonateDate(Date donateDate) {
		this.donateDate = donateDate;
	}
	/**
	 * 获取：到账日期
	 */
	public Date getDonateDate() {
		return donateDate;
	}
	/**
	 * 设置：
	 */
	public void setWithAmount(BigDecimal withAmount) {
		this.withAmount = withAmount;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getWithAmount() {
		return withAmount;
	}
	/**
	 * 设置：支取日期
	 */
	public void setWithDate(Date withDate) {
		this.withDate = withDate;
	}
	/**
	 * 获取：支取日期
	 */
	public Date getWithDate() {
		return withDate;
	}
	/**
	 * 设置：插入日期
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * 获取：插入日期
	 */
	public Date getInsertTime() {
		return insertTime;
	}
	/**
	 * 设置：插入人
	 */
	public void setInsertUser(Long insertUser) {
		this.insertUser = insertUser;
	}
	/**
	 * 获取：插入人
	 */
	public Long getInsertUser() {
		return insertUser;
	}
}
