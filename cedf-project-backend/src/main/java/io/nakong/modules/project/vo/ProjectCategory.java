package io.nakong.modules.project.vo;

import java.math.BigDecimal;

public class ProjectCategory {
		
		/**
		 * 项目分类
		 */
		private String projetType ;
		
		/**
		 * 协议个数
		 */
		private Integer projectNum;
		
		
		/**
		 * 协议捐款金额
		 */
		private BigDecimal projectAmount;
		
		/**
		 * 到账金额
		 */
		private BigDecimal donateAmount;
		
		
		/**
		 * 到账现金金额
		 */
		private BigDecimal donateCashAmount;
		
		
		/**
		 * 到账物资金额
		 */
		private BigDecimal donateMaterialAmount;
		
		/**
		 * 支出金额
		 */
		private BigDecimal receiveAmount;
		
		
		/**
		 * 支出现金金额
		 */
		private BigDecimal receiveCashAmount;
		
		/**
		 * 支出物资金额
		 */
		private BigDecimal receiveMaterialAmount;
		
		/**
		 * 余额
		 */
		private BigDecimal balanceAmount;
		
		/**
		 * 项目名称
		 */
		private String projectName;
		
		/**
		 * 
		 * 项目编号
		 */
		private String projectNo;
		
		/**
		 * 支出日期
		 */
		private String  receiveDate;
		
		/**
		 * 接收到捐款日期
		 */
		private String donateDate;
		
	 
		
		public String getReceiveDate() {
			return receiveDate;
		}

		public void setReceiveDate(String receiveDate) {
			this.receiveDate = receiveDate;
		}

		public String getDonateDate() {
			return donateDate;
		}

		public void setDonateDate(String donateDate) {
			this.donateDate = donateDate;
		}

		public BigDecimal getDonateCashAmount() {
			return donateCashAmount;
		}

		public void setDonateCashAmount(BigDecimal donateCashAmount) {
			this.donateCashAmount = donateCashAmount;
		}

		public BigDecimal getDonateMaterialAmount() {
			return donateMaterialAmount;
		}

		public void setDonateMaterialAmount(BigDecimal donateMaterialAmount) {
			this.donateMaterialAmount = donateMaterialAmount;
		}

		public BigDecimal getReceiveCashAmount() {
			return receiveCashAmount;
		}

		public void setReceiveCashAmount(BigDecimal receiveCashAmount) {
			this.receiveCashAmount = receiveCashAmount;
		}

		public BigDecimal getReceiveMaterialAmount() {
			return receiveMaterialAmount;
		}

		public void setReceiveMaterialAmount(BigDecimal receiveMaterialAmount) {
			this.receiveMaterialAmount = receiveMaterialAmount;
		}

		public String getProjectName() {
			return projectName;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

		public String getProjectNo() {
			return projectNo;
		}

		public void setProjectNo(String projectNo) {
			this.projectNo = projectNo;
		}

		public String getProjetType() {
			return projetType;
		}
		
		public void setProjetType(String projetType) {
			this.projetType = projetType;
		}

		public Integer getProjectNum() {
			return projectNum;
		}

		public void setProjectNum(Integer projectNum) {
			this.projectNum = projectNum;
		}

		public BigDecimal getProjectAmount() {
			return projectAmount;
		}

		public void setProjectAmount(BigDecimal projectAmount) {
			this.projectAmount = projectAmount;
		}

		public BigDecimal getDonateAmount() {
			return donateAmount;
		}

		public void setDonateAmount(BigDecimal donateAmount) {
			this.donateAmount = donateAmount;
		}

		public BigDecimal getReceiveAmount() {
			return receiveAmount;
		}

		public void setReceiveAmount(BigDecimal receiveAmount) {
			this.receiveAmount = receiveAmount;
		}

		public BigDecimal getBalanceAmount() {
			return balanceAmount;
		}

		public void setBalanceAmount(BigDecimal balanceAmount) {
			this.balanceAmount = balanceAmount;
		}
}
