package io.nakong.modules.collect.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@cowain.com.cn
 * @date 2019-08-24 14:34:43
 */
@TableName("t_collect_param")
public class CollecparamEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    @TableField(exist = false)
	private String t;

    public String getT() {
        return t;
    }
	public void setT(String t){
        this.t = t;
	}
		/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 采集类型 
	 */
	private Integer collectType;
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
	private Date updateTime;

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
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
