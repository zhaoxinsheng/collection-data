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
 * @date 2019-08-18 21:50:18
 */
@TableName("t_collect_statis")
public class CollecstatisEntity implements Serializable {
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

	private Integer data;
	/**
	 * 
	 */
	private Integer collectData;
	/**
	 * 
	 */
	private Integer collectType;
	/**
	 * 
	 */
	private Integer equipId;
	/**
	 * 
	 */
	private Integer hour;
	/**
	 * 
	 */
	private Integer minu;
	/**
	 * 
	 */
	private Integer second;
	/**
	 * 
	 */
	private Integer month;
	/**
	 * 
	 */
	private Integer year;
	/**
	 * 
	 */
	private Integer weekMonth;
	/**
	 * 
	 */
	private String dayYear;
	/**
	 * 
	 */
	private Date insertTime;
	/**
	 * 
	 */
	private Date updateTime;

	private String name;

	private String dateStr;

	private String color;


	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

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
	 * 设置：
	 */
	public void setCollectData(Integer collectData) {
		this.collectData = collectData;
	}
	/**
	 * 获取：
	 */
	public Integer getCollectData() {
		return collectData;
	}
	/**
	 * 设置：
	 */
	public void setCollectType(Integer collectType) {
		this.collectType = collectType;
	}
	/**
	 * 获取：
	 */
	public Integer getCollectType() {
		return collectType;
	}
	/**
	 * 设置：
	 */
	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}
	/**
	 * 获取：
	 */
	public Integer getEquipId() {
		return equipId;
	}
	/**
	 * 设置：
	 */
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	/**
	 * 获取：
	 */
	public Integer getHour() {
		return hour;
	}
	/**
	 * 设置：
	 */
	public void setMinu(Integer minu) {
		this.minu = minu;
	}
	/**
	 * 获取：
	 */
	public Integer getMinu() {
		return minu;
	}
	/**
	 * 设置：
	 */
	public void setSecond(Integer second) {
		this.second = second;
	}
	/**
	 * 获取：
	 */
	public Integer getSecond() {
		return second;
	}
	/**
	 * 设置：
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}
	/**
	 * 获取：
	 */
	public Integer getMonth() {
		return month;
	}
	/**
	 * 设置：
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	/**
	 * 获取：
	 */
	public Integer getYear() {
		return year;
	}
	/**
	 * 设置：
	 */
	public void setWeekMonth(Integer weekMonth) {
		this.weekMonth = weekMonth;
	}
	/**
	 * 获取：
	 */
	public Integer getWeekMonth() {
		return weekMonth;
	}
	/**
	 * 设置：
	 */
	public void setDayYear(String dayYear) {
		this.dayYear = dayYear;
	}
	/**
	 * 获取：
	 */
	public String getDayYear() {
		return dayYear;
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
