package io.nakong.modules.project.entity;

import java.io.Serializable;
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
@TableName("tb_donator")
public class DonatorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 捐赠单位名称
	 */
	private String companyName;
	/**
	 * 捐赠单位地址
	 */
	private String areaCode;
	/**
	 * 捐赠单位详细地址
	 */
	private String addressDetail;
	/**
	 * 捐赠单位法人
	 */
	private String legalPerson;
	/**
	 * 捐赠单位联系人
	 */
	private String relatePerson;
	/**
	 * 捐赠单位邮编
	 */
	private String postalCode;
	/**
	 * 捐赠单位电话
	 */
	private String companyTelephone;
	/**
	 * 插入时间
	 */
	private Date insertTime;
	/**
	 * 操作人
	 */
	private String insertUser;
	/**
	 * 更新时间
	 */
	private Date updateTime;

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
	 * 设置：捐赠单位名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 获取：捐赠单位名称
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置：捐赠单位地址
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * 获取：捐赠单位地址
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * 设置：捐赠单位详细地址
	 */
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	/**
	 * 获取：捐赠单位详细地址
	 */
	public String getAddressDetail() {
		return addressDetail;
	}
	/**
	 * 设置：捐赠单位法人
	 */
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	/**
	 * 获取：捐赠单位法人
	 */
	public String getLegalPerson() {
		return legalPerson;
	}
	/**
	 * 设置：捐赠单位联系人
	 */
	public void setRelatePerson(String relatePerson) {
		this.relatePerson = relatePerson;
	}
	/**
	 * 获取：捐赠单位联系人
	 */
	public String getRelatePerson() {
		return relatePerson;
	}
	/**
	 * 设置：捐赠单位邮编
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * 获取：捐赠单位邮编
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * 设置：捐赠单位电话
	 */
	public void setCompanyTelephone(String companyTelephone) {
		this.companyTelephone = companyTelephone;
	}
	/**
	 * 获取：捐赠单位电话
	 */
	public String getCompanyTelephone() {
		return companyTelephone;
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
