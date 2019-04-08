package io.nakong.modules.project.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:14
 */
@TableName("tb_receiver")
public class ReceiverEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 接收捐赠的单位
	 */
	private String receiveCompany;
	/**
	 * 单位区域编码
	 */
	private String areaCode;
	/**
	 * 详细地址
	 */
	private String addressDetail;
	/**
	 * 单位联系人
	 */
	private String relatePerson;
	/**
	 * 接收捐赠单位电话
	 */
	private String receiveTelephone;
	/**
	 * 项目id
	 */
	private Integer projectId;
	/**
	 * 操作人
	 */
	private String insertUser;
	/**
	 * 操作时间
	 */
	private Date insertTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 接收到的金额
	 */
	private BigDecimal receiveAmount;

	/**
	 * 省份ID
	 */
	private String provinceId;

	/**
	 * 城市ID
	 */
	private String cityId;

	/**
	 * 区县ID
	 */
	private String countyId;

	/**
	 * 下属单位名称
	 * */
	private String secondUnit;

	/**
	 * 省
	 * */
	@TableField(exist = false)
	private String provinceName;
	/**
	 * 市
	 * */
	@TableField(exist = false)
	private String cityName;
	/**
	 * 县
	 * */
	@TableField(exist = false)
	private String countyName;

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
	 * 设置：接收捐赠的单位
	 */
	public void setReceiveCompany(String receiveCompany) {
		this.receiveCompany = receiveCompany;
	}
	/**
	 * 获取：接收捐赠的单位
	 */
	public String getReceiveCompany() {
		return receiveCompany;
	}
	/**
	 * 设置：单位区域编码
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * 获取：单位区域编码
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * 设置：详细地址
	 */
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	/**
	 * 获取：详细地址
	 */
	public String getAddressDetail() {
		return addressDetail;
	}
	/**
	 * 设置：单位联系人
	 */
	public void setRelatePerson(String relatePerson) {
		this.relatePerson = relatePerson;
	}
	/**
	 * 获取：单位联系人
	 */
	public String getRelatePerson() {
		return relatePerson;
	}
	/**
	 * 设置：接收捐赠单位电话
	 */
	public void setReceiveTelephone(String receiveTelephone) {
		this.receiveTelephone = receiveTelephone;
	}
	/**
	 * 获取：接收捐赠单位电话
	 */
	public String getReceiveTelephone() {
		return receiveTelephone;
	}
	/**
	 * 设置：项目id
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：项目id
	 */
	public Integer getProjectId() {
		return projectId;
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
	 * 设置：操作时间
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * 获取：操作时间
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
	 * 设置：接收到的金额
	 */
	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}
	/**
	 * 获取：接收到的金额
	 */
	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCountyId() {
		return countyId;
	}

	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}

	public String getSecondUnit() {
		return secondUnit;
	}

	public void setSecondUnit(String secondUnit) {
		this.secondUnit = secondUnit;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
}
