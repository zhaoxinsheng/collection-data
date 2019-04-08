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
@TableName("tb_tips")
public class TipsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 采集类型
	 */
	private Integer collectTypeId;
	/**
	 * 提醒项说明信息
	 */
	private String tipDesc;
	/**
	 * 提醒频率
	 */
	private String tipQuartz;
	/**
	 * 
	 */
	private Date insertTime;
	/**
	 * 
	 */
	private String insertUser;

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
	 * 设置：采集类型
	 */
	public void setCollectTypeId(Integer collectTypeId) {
		this.collectTypeId = collectTypeId;
	}
	/**
	 * 获取：采集类型
	 */
	public Integer getCollectTypeId() {
		return collectTypeId;
	}
	/**
	 * 设置：提醒项说明信息
	 */
	public void setTipDesc(String tipDesc) {
		this.tipDesc = tipDesc;
	}
	/**
	 * 获取：提醒项说明信息
	 */
	public String getTipDesc() {
		return tipDesc;
	}
	/**
	 * 设置：提醒频率
	 */
	public void setTipQuartz(String tipQuartz) {
		this.tipQuartz = tipQuartz;
	}
	/**
	 * 获取：提醒频率
	 */
	public String getTipQuartz() {
		return tipQuartz;
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
