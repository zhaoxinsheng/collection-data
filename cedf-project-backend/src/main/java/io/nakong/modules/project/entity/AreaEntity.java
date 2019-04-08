package io.nakong.modules.project.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-24 16:43:00
 */
@TableName("tb_area")
public class AreaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String baseAreaId;
	/**
	 * 地区名
	 */
	private String areaName;
	/**
	 * 地区码（唯一）
	 */
	private String areaCode;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 父级地区id
	 */
	private String parentId;
	/**
	 * 地区级别：1，2...
	 */
	private Integer areaLevel;
	/**
	 * 
	 */
	private String areaPath;
	/**
	 * 插入时间
	 */
	private Date insertTime;

	/**
	 * 设置：
	 */
	public void setBaseAreaId(String baseAreaId) {
		this.baseAreaId = baseAreaId;
	}
	/**
	 * 获取：
	 */
	public String getBaseAreaId() {
		return baseAreaId;
	}
	/**
	 * 设置：地区名
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * 获取：地区名
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * 设置：地区码（唯一）
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * 获取：地区码（唯一）
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置：父级地区id
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父级地区id
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 设置：地区级别：1，2...
	 */
	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}
	/**
	 * 获取：地区级别：1，2...
	 */
	public Integer getAreaLevel() {
		return areaLevel;
	}
	/**
	 * 设置：
	 */
	public void setAreaPath(String areaPath) {
		this.areaPath = areaPath;
	}
	/**
	 * 获取：
	 */
	public String getAreaPath() {
		return areaPath;
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
}
