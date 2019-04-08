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
@TableName("tb_param_collect")
public class ParamCollectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 类型 1 
	 */
	private String collectTypeId;
	/**
	 * 采集数量
	 */
	private Integer collectNum;
	/**
	 * 采集步长
	 */
	private Integer collectStep;
	/**
	 * 
	 */
	private String insertUser;
	/**
	 * 
	 */
	private Date insertTime;
	/**
	 * 采集单位
	 */
	private String collectUnit;

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
	 * 设置：类型 1 
	 */
	public void setCollectTypeId(String collectTypeId) {
		this.collectTypeId = collectTypeId;
	}
	/**
	 * 获取：类型 1 
	 */
	public String getCollectTypeId() {
		return collectTypeId;
	}
	/**
	 * 设置：采集数量
	 */
	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}
	/**
	 * 获取：采集数量
	 */
	public Integer getCollectNum() {
		return collectNum;
	}
	/**
	 * 设置：采集步长
	 */
	public void setCollectStep(Integer collectStep) {
		this.collectStep = collectStep;
	}
	/**
	 * 获取：采集步长
	 */
	public Integer getCollectStep() {
		return collectStep;
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
	 * 设置：采集单位
	 */
	public void setCollectUnit(String collectUnit) {
		this.collectUnit = collectUnit;
	}
	/**
	 * 获取：采集单位
	 */
	public String getCollectUnit() {
		return collectUnit;
	}
}
