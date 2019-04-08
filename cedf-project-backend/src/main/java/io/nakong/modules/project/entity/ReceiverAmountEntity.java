package io.nakong.modules.project.entity;

import java.math.BigDecimal;
import java.util.Date;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author robot
 */
@TableName("tb_receiver_amount")
public class ReceiverAmountEntity {
	@TableId
	private Integer id;
	private Integer projectId;// 项目id
	private String contract_no; //协议号
	private String receiverId;// 接收捐赠者id
	
	private String amountType;// 接收捐款方式 0 现金 1 物资
	private BigDecimal cashAmount;// 支出金额
	private String relatePerson;// 单位联系人
	private String receiveTelephone;// 接收捐赠单位电话
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date receiveDate;// 接收日期
	private String secondUnit;//下辖接受捐赠单位名称
	private Long insertUser;
	private Date insertTime;
	
	

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public Long getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(Long insertUser) {
		this.insertUser = insertUser;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getRelatePerson() {
		return relatePerson;
	}

	public void setRelatePerson(String relatePerson) {
		this.relatePerson = relatePerson;
	}

	public String getReceiveTelephone() {
		return receiveTelephone;
	}

	public void setReceiveTelephone(String receiveTelephone) {
		this.receiveTelephone = receiveTelephone;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getSecondUnit() {
		return secondUnit;
	}

	public void setSecondUnit(String secondUnit) {
		this.secondUnit = secondUnit;
	}
}
