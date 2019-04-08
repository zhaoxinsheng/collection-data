package io.nakong.modules.project.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:14
 */
@TableName("tb_project")
public class ProjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 协议号
	 */
	private String projectNo;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 协议捐赠内容
	 */
	private String projectContent;
	/**
	 * 协议捐赠金额
	 */
	private BigDecimal cashAmount;
	/**
	 * 实物捐赠金额
	 */
	private BigDecimal materialAmount;
	/**
	 * 项目类型
	 */
	private Integer projectType;
	/**
	 * 协议时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateTime;
	/**
	 * 操作人
	 */
	private String insertUser;
	/**
	 * 插入时间
	 */
	private Date insertTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 捐赠人id
	 */
	private Integer donatorId;

	/**
	 * 助教号
	 */
	private String assistantNo;

	/**
	 * 项目类型
	 */
	@TableField(exist = false)
	private String typeName;

	/**
	 * 捐赠单位名称
	 */
	@TableField(exist = false)
	private String donatorName;
	/**
	 * 接受捐赠单位名称，逗号拼接
	 */
	@TableField(exist = false)
	private String receiverName;


	/**
	 * 起始日期
	 */
	@TableField(exist = false)
	private Date startDate;
	
	/**
	 * 捐赠单位id
	 */
	@TableField(exist = false)
	private int donateId;
	
	/**
	 * 接收捐赠单位id
	 */
	@TableField(exist = false)
	private int receiveId;

	public int getDonateId() {
		return donateId;
	}

	public void setDonateId(int donateId) {
		this.donateId = donateId;
	}

	public int getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(int receiveId) {
		this.receiveId = receiveId;
	}

	/**
	 * 结束日期
	 */
	@TableField(exist = false)
	private Date endDate;

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
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	/**
	 * 获取：协议号
	 */
	public String getProjectNo() {
		return projectNo;
	}

	/**
	 * 设置：项目名称
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * 获取：项目名称
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * 设置：协议捐赠内容
	 */
	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}

	/**
	 * 获取：协议捐赠内容
	 */
	public String getProjectContent() {
		return projectContent;
	}

	/**
	 * 设置：协议捐赠金额
	 */
	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	/**
	 * 获取：协议捐赠金额
	 */
	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	/**
	 * 设置：实物捐赠金额
	 */
	public void setMaterialAmount(BigDecimal materialAmount) {
		this.materialAmount = materialAmount;
	}

	/**
	 * 获取：实物捐赠金额
	 */
	public BigDecimal getMaterialAmount() {
		return materialAmount;
	}

	/**
	 * 设置：项目类型
	 */
	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	/**
	 * 获取：项目类型
	 */
	public Integer getProjectType() {
		return projectType;
	}

	/**
	 * 设置：协议时间
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * 获取：协议时间
	 */
	public Date getDateTime() {
		return dateTime;
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
	 * 设置：捐赠人id
	 */
	public void setDonatorId(Integer donatorId) {
		this.donatorId = donatorId;
	}

	/**
	 * 获取：捐赠人id
	 */
	public Integer getDonatorId() {
		return donatorId;
	}

	public String getDonatorName() {
		return donatorName;
	}

	public void setDonatorName(String donatorName) {
		this.donatorName = donatorName;
	}


	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAssistantNo() {
		return assistantNo;
	}

	public void setAssistantNo(String assistantNo) {
		this.assistantNo = assistantNo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
