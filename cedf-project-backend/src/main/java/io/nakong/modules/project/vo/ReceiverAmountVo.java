package io.nakong.modules.project.vo;

import io.nakong.modules.project.entity.ReceiverAmountEntity;

/**
 *
 * @author robot
 */
public class ReceiverAmountVo extends ReceiverAmountEntity {
	private String projectNo;// 协议号
	private String projectName;// 项目名称
	private String receiveCompany;// 接收捐赠的单位

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getReceiveCompany() {
		return receiveCompany;
	}

	public void setReceiveCompany(String receiveCompany) {
		this.receiveCompany = receiveCompany;
	}
}
