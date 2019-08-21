package io.nakong.modules.collect.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2019-05-29 21:58:32
 */
@TableName("t_tips")
public class TipsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String equipType;
	/**
	 * 
	 */
	private String tipsMsg;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private String startTime;
	/**
	 * 
	 */
	private String endTime;
	/**
	 * 
	 */
	private Date insertTime;


	private  Integer  miniValue;

	private Integer  maxValue;

	private Integer  period;

	private Integer periodType;

	@TableField(exist = false)
	private String periodTypeName;

	public String getPeriodTypeName() {
		return periodTypeName;
	}

	public void setPeriodTypeName(String periodTypeName) {
		this.periodTypeName = periodTypeName;
	}

	public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Integer periodType) {
        this.periodType = periodType;
    }

    public Integer getMiniValue() {
        return miniValue;
    }

    public void setMiniValue(Integer miniValue) {
        this.miniValue = miniValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    /**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}
	/**
	 * 获取：
	 */
	public String getEquipType() {
		return equipType;
	}
	/**
	 * 设置：
	 */
	public void setTipsMsg(String tipsMsg) {
		this.tipsMsg = tipsMsg;
	}
	/**
	 * 获取：
	 */
	public String getTipsMsg() {
		return tipsMsg;
	}
	/**
	 * 设置：
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 设置：
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：
	 */
	public String getEndTime() {
		return endTime;
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
