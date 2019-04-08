package io.nakong.modules.project.entity;

import java.io.Serializable;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-22 14:17:37
 */
@TableName("tb_project_type")
public class ProjectTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 项目类型表
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String name;

	/**
	 * 设置：项目类型表
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：项目类型表
	 */
	public Integer getId() {
		return id;
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
}
