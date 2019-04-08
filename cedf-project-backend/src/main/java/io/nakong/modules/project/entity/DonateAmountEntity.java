package io.nakong.modules.project.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:15
 */
@TableName("tb_donate_amount")
public class DonateAmountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 协议号
	 */
	// @NotNull(message = "协议号不能为空")
	private String contractNo;
	/**
	 * 项目名称
	 */
	private Integer projectId;
	/**
	 * 到账现金金额
	 */
	// @NotNull(message = "到账金额不能为空")
	private BigDecimal cashAmount;
	/**
	 * 到账日期
	 */
	 // @NotNull(message = "到账日期不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date donateDate;
	/**
	 * 收据邮寄日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date deliveryDate;
	/**
	 * 快递编号
	 */
	private String deliveryNo;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 操作人
	 */
	private String insertUser;
	/**
	 * 插入时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date insertTime;
	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateTime;
	/**
	 * 汇款单位
	 */
	private String donator;
	
	/**
	 * 捐赠单位id
	 */
	private int donateId;
	/**
	 * 到账物资金额
	 */
	private BigDecimal meterialAmount;

	// @NotNull(message = "到账类型不能为空")
	private int amountType;// 接收捐款方式 0 现金 1 物资
	
	@TableField(exist = false)
	private String donateType; // excel    现金   物资
	
	public String getDonateType() {
		return donateType;
	}
	public void setDonateType(String donateType) {
		this.donateType = donateType;
	}
	public int getDonateId() {
		return donateId;
	}
	public void setDonateId(int donateId) {
		this.donateId = donateId;
	}

	@TableField(exist = false)
	public String projectName;

	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
	 * 设置：协议号
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	/**
	 * 获取：协议号
	 */
	public String getContractNo() {
		return contractNo;
	}
	/**
	 * 设置：项目名称
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：项目名称
	 */
	public Integer getProjectId() {
		return projectId;
	}
	/**
	 * 设置：到账现金金额
	 */
	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}
	/**
	 * 获取：到账现金金额
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
	 * 设置：收据邮寄日期
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	/**
	 * 获取：收据邮寄日期
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	/**
	 * 设置：快递编号
	 */
	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	/**
	 * 获取：快递编号
	 */
	public String getDeliveryNo() {
		return deliveryNo;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：操作人
	 */
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	/**
	 * 获取：操作人
	 */
	public String getInsertUser() {
		return insertUser;
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
	/**
	 * 设置：汇款单位
	 */
	public void setDonator(String donator) {
		this.donator = donator;
	}
	/**
	 * 获取：汇款单位
	 */
	public String getDonator() {
		return donator;
	}
	/**
	 * 设置：到账物资金额
	 */
	public void setMeterialAmount(BigDecimal meterialAmount) {
		this.meterialAmount = meterialAmount;
	}
	/**
	 * 获取：到账物资金额
	 */
	public BigDecimal getMeterialAmount() {
		return meterialAmount;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getAmountType() {
		return amountType;
	}

	public void setAmountType(int amountType) {
		this.amountType = amountType;
	}
}
