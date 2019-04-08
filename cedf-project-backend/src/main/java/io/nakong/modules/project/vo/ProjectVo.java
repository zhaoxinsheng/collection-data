package io.nakong.modules.project.vo;/*
										*   阔地教育科技有限公司版权所有（codyy.com/codyy.cn）
										*   Copyright (c) 2017, Codyy and/or its affiliates. All rights              
										*    reserved.
										*/

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import io.nakong.modules.project.entity.ProjectEntity;

/**
 * @author maozhengwen
 * @version （注明版本号）
 * @Description: 值对象(这里用一句话描述这个类的作用)
 * @date
 */
public class ProjectVo extends ProjectEntity {

	private String projectTypeName;

	/**
	 * 捐赠单位对象，方便新增时接收
	 */
	private DonatorVo donatorEntity = new DonatorVo();

	/**
	 * 捐赠单位对象，方便新增时接收
	 */
	private List<ReceiverVo> receiverEntity = new ArrayList<>();

	public DonatorVo getDonatorEntity() {
		return donatorEntity;
	}

	public void setDonatorEntity(DonatorVo donatorEntity) {
		this.donatorEntity = donatorEntity;
	}

	public List<ReceiverVo> getReceiverEntity() {
		return receiverEntity;
	}

	public void setReceiverEntity(List<ReceiverVo> receiverEntity) {
		this.receiverEntity = receiverEntity;
	}

	
	// used for excel import
	public void setReceiverCompany(String val) {
		checkAndFillReceiverEntity();
		receiverEntity.get(0).setReceiveCompany(val);
	}

	public void setReceiverAddressDetail(String val) {
		if (StringUtils.isEmpty(val)) {
			return;
		}
		String[] vals = val.split(",");
		checkAndFillReceiverEntity(vals.length);
		for (int i = 0; i < vals.length; i++) {
			receiverEntity.get(i).setAddressDetail(vals[i]);
		}
	}

	public void setReceiverRelatePerson(String val) {
		if (StringUtils.isEmpty(val)) {
			return;
		}
		String[] vals = val.split(",");
		checkAndFillReceiverEntity(vals.length);
		for (int i = 0; i < vals.length; i++) {
			receiverEntity.get(i).setRelatePerson(vals[i]);
		}
		
	}

	public void setReceiverReceiveTelephone(String val) {
		if (StringUtils.isEmpty(val)) {
			return;
		}
		String[] vals = val.split(",");
		checkAndFillReceiverEntity(vals.length);
		for (int i = 0; i < vals.length; i++) {
			receiverEntity.get(i).setReceiveTelephone(vals[i]);
		}
	}
	
	private void checkAndFillReceiverEntity() {
		checkAndFillReceiverEntity(1);
	}
	
	private void checkAndFillReceiverEntity(int num) {
		if (receiverEntity == null) {
			receiverEntity = new ArrayList<>();
		}
		if (receiverEntity.size() < num) {
			int numToAdd = num - receiverEntity.size();
			for (int i = 0; i < numToAdd; i++) {
				receiverEntity.add(new ReceiverVo());
			}
		}
	}

	public String getProjectTypeName() {
		return projectTypeName;
	}

	public void setProjectTypeName(String projectTypeName) {
		this.projectTypeName = projectTypeName;
	}
}
