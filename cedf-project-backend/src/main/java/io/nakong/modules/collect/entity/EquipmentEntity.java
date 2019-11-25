package io.nakong.modules.collect.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2019-05-29 21:58:32
 */
@TableName("t_equipment")
public class EquipmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private int id;
	/**
	 * 
	 */
	private String model;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String vendor;
	/**
	 * 
	 */
	private String type;
	/**
	 * 
	 */
	private Integer no;
	/**
	 * 
	 */
	private String productDate;
	/**
	 * 
	 */
	private String collecUnit;
	/**collecTypeName
	 * 
	 */
	private Integer collecType;

	private Integer category;

	@TableField(exist = false)
	private List<EquipColorEntity> detail;

	public List<EquipColorEntity> getDetail() {
		return detail;
	}

	public void setDetail(List<EquipColorEntity> detail) {
		this.detail = detail;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getCollecTypeName() {
		return collecTypeName;
	}

	public void setCollecTypeName(String collecTypeName) {
		this.collecTypeName = collecTypeName;
	}

	/**
	 *
	 */
	@TableField(exist = false)
	private  String collecTypeName;
	/**
	 * 
	 */
	private Date insertDate;
	/**
	 * 
	 */
	private String color;
	/**
	 * 
	 */
	private String varname;

	/**
	 * 设置：
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public int getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * 获取：
	 */
	public String getModel() {
		return model;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	/**
	 * 获取：
	 */
	public String getVendor() {
		return vendor;
	}
	/**
	 * 设置：
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setNo(Integer no) {
		this.no = no;
	}
	/**
	 * 获取：
	 */
	public Integer getNo() {
		return no;
	}
	/**
	 * 设置：
	 */
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	/**
	 * 获取：
	 */
	public String getProductDate() {
		return productDate;
	}
	/**
	 * 设置：
	 */
	public void setCollecUnit(String collecUnit) {
		this.collecUnit = collecUnit;
	}
	/**
	 * 获取：
	 */
	public String getCollecUnit() {
		return collecUnit;
	}
	/**
	 * 设置：
	 */
	public void setCollecType(Integer collecType) {
		this.collecType = collecType;
	}
	/**
	 * 获取：
	 */
	public Integer getCollecType() {
		return collecType;
	}
	/**
	 * 设置：
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	/**
	 * 获取：
	 */
	public Date getInsertDate() {
		return insertDate;
	}
	/**
	 * 设置：
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * 获取：
	 */
	public String getColor() {
		return color;
	}
	/**
	 * 设置：
	 */
	public void setVarname(String varname) {
		this.varname = varname;
	}
	/**
	 * 获取：
	 */
	public String getVarname() {
		return varname;
	}
}
