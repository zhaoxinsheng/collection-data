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
@TableName("tb_collect_type")
public class CollectTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 采集类型
	 */
	private Integer collectType;
	/**
	 * 
	 */
	private String collectUnit;
	/**
	 * 
	 */
	private String insertUser;
	/**
	 * 
	 */
	private Date insertTime;

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
	public void setCollectType(Integer collectType) {
		this.collectType = collectType;
	}
	/**
	 * 获取：采集类型
	 */
	public Integer getCollectType() {
		return collectType;
	}
	/**
	 * 设置：
	 */
	public void setCollectUnit(String collectUnit) {
		this.collectUnit = collectUnit;
	}
	/**
	 * 获取：
	 */
	public String getCollectUnit() {
		return collectUnit;
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
}
