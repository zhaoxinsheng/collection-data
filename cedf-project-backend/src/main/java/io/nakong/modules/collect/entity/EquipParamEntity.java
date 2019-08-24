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
 * @date 2019-08-24 14:22:53
 */
@TableName("t_equip_param")
public class EquipParamEntity implements Serializable {
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
	 * 流量
	 */
	private Integer pipe;
	/**
	 * 管损
	 */
	private Integer loss;
	/**
	 * 后处理设备
	 */
	private Integer equip;
	/**
	 * 电费正常模式
	 */
	private Integer pwNormal;
	/**
	 * 电费峰谷平
	 */
	private Integer pwHigh;
	/**
	 * 更新时间
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
	 * 设置：流量
	 */
	public void setPipe(Integer pipe) {
		this.pipe = pipe;
	}
	/**
	 * 获取：流量
	 */
	public Integer getPipe() {
		return pipe;
	}
	/**
	 * 设置：管损
	 */
	public void setLoss(Integer loss) {
		this.loss = loss;
	}
	/**
	 * 获取：管损
	 */
	public Integer getLoss() {
		return loss;
	}
	/**
	 * 设置：后处理设备
	 */
	public void setEquip(Integer equip) {
		this.equip = equip;
	}
	/**
	 * 获取：后处理设备
	 */
	public Integer getEquip() {
		return equip;
	}
	/**
	 * 设置：电费正常模式
	 */
	public void setPwNormal(Integer pwNormal) {
		this.pwNormal = pwNormal;
	}
	/**
	 * 获取：电费正常模式
	 */
	public Integer getPwNormal() {
		return pwNormal;
	}
	/**
	 * 设置：电费峰谷平
	 */
	public void setPwHigh(Integer pwHigh) {
		this.pwHigh = pwHigh;
	}
	/**
	 * 获取：电费峰谷平
	 */
	public Integer getPwHigh() {
		return pwHigh;
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
}
