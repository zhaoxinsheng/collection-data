package io.nakong.modules.project.vo;

import com.baomidou.mybatisplus.annotations.TableField;

import io.nakong.modules.project.entity.ProjectEntity;

/**
 * @author maozhengwen
 * @version （注明版本号）
 * @Description: 值对象(这里用一句话描述这个类的作用)
 * @date
 */
public class ProjectSummaryVo extends ProjectEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -69464583587857620L;

	/**
	 * 捐赠单位地址
	 */
	@TableField(exist = false)
	private String donatorAddress;

	/**
	 * 捐赠单位法人
	 */
	@TableField(exist = false)
	private String donatorLegalPerson;

	/**
	 * 捐赠单位邮编
	 */
	@TableField(exist = false)
	private String donatorPostalCode;

	/**
	 * 捐赠单位联系人
	 */
	@TableField(exist = false)
	private String donatorRelatePerson;

	/**
	 * 捐赠单位联系电话
	 */
	@TableField(exist = false)
	private String donatorCompanyTelephone;

	/**
	 * 接受捐赠单位地址，逗号拼接
	 */
	@TableField(exist = false)
	private String receiverAddressDetail;

	/**
	 * 接受捐赠单位联系人，逗号拼接
	 */
	@TableField(exist = false)
	private String receiverRelatePerson;

	/**
	 * 接受捐赠单位联系电话，逗号拼接
	 */
	@TableField(exist = false)
	private String receiverTelephone;

	/**
	 * 现金到账金额
	 */
	private Double inCashAmount;
	/**
	 * 物资到账金额
	 */
	private Double inMeterialAmount;
	/**
	 * 现金支出金额
	 */
	private Double outCashAmount;
	/**
	 * 物资支出金额
	 */
	private Double outMeterialAmount;

	/**
	 * 总支出
	 */
	private Double totalOutAmount;
	/**
	 * 余额
	 */
	private Double balance;
	
	
	private String dateTimeStr;
	
	 
	private String cashAmountStr;
	 
	private String materialAmountStr;
	
	private String inMeterialAmountStr;
	
	private String  inCashAmountStr;
	
	private String  totalOutAmountStr;
	
	private String  balanceStr;
	
	
	
	

	public String getDateTimeStr() {
		return dateTimeStr;
	}

	public void setDateTimeStr(String dateTimeStr) {
		this.dateTimeStr = dateTimeStr;
	}

	public String getCashAmountStr() {
		return cashAmountStr;
	}

	public void setCashAmountStr(String cashAmountStr) {
		this.cashAmountStr = cashAmountStr;
	}

	public String getMaterialAmountStr() {
		return materialAmountStr;
	}

	public void setMaterialAmountStr(String materialAmountStr) {
		this.materialAmountStr = materialAmountStr;
	}

	public String getInMeterialAmountStr() {
		return inMeterialAmountStr;
	}

	public void setInMeterialAmountStr(String inMeterialAmountStr) {
		this.inMeterialAmountStr = inMeterialAmountStr;
	}

	public String getInCashAmountStr() {
		return inCashAmountStr;
	}

	public void setInCashAmountStr(String inCashAmountStr) {
		this.inCashAmountStr = inCashAmountStr;
	}

	public String getTotalOutAmountStr() {
		return totalOutAmountStr;
	}

	public void setTotalOutAmountStr(String totalOutAmountStr) {
		this.totalOutAmountStr = totalOutAmountStr;
	}

	public String getBalanceStr() {
		return balanceStr;
	}

	public void setBalanceStr(String balanceStr) {
		this.balanceStr = balanceStr;
	}

	public String getDonatorAddress() {
		return donatorAddress;
	}

	public void setDonatorAddress(String donatorAddress) {
		this.donatorAddress = donatorAddress;
	}

	public String getDonatorLegalPerson() {
		return donatorLegalPerson;
	}

	public void setDonatorLegalPerson(String donatorLegalPerson) {
		this.donatorLegalPerson = donatorLegalPerson;
	}

	public String getDonatorPostalCode() {
		return donatorPostalCode;
	}

	public void setDonatorPostalCode(String donatorPostalCode) {
		this.donatorPostalCode = donatorPostalCode;
	}

	public String getDonatorRelatePerson() {
		return donatorRelatePerson;
	}

	public void setDonatorRelatePerson(String donatorRelatePerson) {
		this.donatorRelatePerson = donatorRelatePerson;
	}

	public String getDonatorCompanyTelephone() {
		return donatorCompanyTelephone;
	}

	public void setDonatorCompanyTelephone(String donatorCompanyTelephone) {
		this.donatorCompanyTelephone = donatorCompanyTelephone;
	}

	public String getReceiverAddressDetail() {
		return receiverAddressDetail;
	}

	public void setReceiverAddressDetail(String receiverAddressDetail) {
		this.receiverAddressDetail = receiverAddressDetail;
	}

	public String getReceiverRelatePerson() {
		return receiverRelatePerson;
	}

	public void setReceiverRelatePerson(String receiverRelatePerson) {
		this.receiverRelatePerson = receiverRelatePerson;
	}

	public String getReceiverTelephone() {
		return receiverTelephone;
	}

	public void setReceiverTelephone(String receiverTelephone) {
		this.receiverTelephone = receiverTelephone;
	}

	public Double getInCashAmount() {
		return inCashAmount;
	}

	public void setInCashAmount(Double inCashAmount) {
		this.inCashAmount = inCashAmount;
	}

	public Double getInMeterialAmount() {
		return inMeterialAmount;
	}

	public void setInMeterialAmount(Double inMeterialAmount) {
		this.inMeterialAmount = inMeterialAmount;
	}

	public Double getOutCashAmount() {
		return outCashAmount;
	}

	public void setOutCashAmount(Double outCashAmount) {
		this.outCashAmount = outCashAmount;
	}

	public Double getOutMeterialAmount() {
		return outMeterialAmount;
	}

	public void setOutMeterialAmount(Double outMeterialAmount) {
		this.outMeterialAmount = outMeterialAmount;
	}

	public Double getTotalOutAmount() {
		return outCashAmount + outMeterialAmount;
	}

	public void setTotalOutAmount(Double totalOutAmount) {
		this.totalOutAmount = totalOutAmount;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
}
