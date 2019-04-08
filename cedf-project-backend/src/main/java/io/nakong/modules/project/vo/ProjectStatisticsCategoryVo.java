package io.nakong.modules.project.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 按照项目分类统计
 * @author tom
 *
 */
public class ProjectStatisticsCategoryVo {
	
	private BigDecimal totalProjectAmount;
	
	private BigDecimal totalDonateAmount;
	
	private BigDecimal totalReceiveAmount;
	
	private BigDecimal totalBalanceAmount;
	
	
	private BigDecimal totalDonateCashAmount;
	
	private BigDecimal totalDonateMaterialAmount;
	
	private BigDecimal totalReceiveCashAmount;
	
	private BigDecimal totalReceiveMaterialAmount;
	
	

	public BigDecimal getTotalDonateCashAmount() {
		return totalDonateCashAmount;
	}


	public void setTotalDonateCashAmount(BigDecimal totalDonateCashAmount) {
		this.totalDonateCashAmount = totalDonateCashAmount;
	}


	public BigDecimal getTotalDonateMaterialAmount() {
		return totalDonateMaterialAmount;
	}


	public void setTotalDonateMaterialAmount(BigDecimal totalDonateMaterialAmount) {
		this.totalDonateMaterialAmount = totalDonateMaterialAmount;
	}


	public BigDecimal getTotalReceiveCashAmount() {
		return totalReceiveCashAmount;
	}


	public void setTotalReceiveCashAmount(BigDecimal totalReceiveCashAmount) {
		this.totalReceiveCashAmount = totalReceiveCashAmount;
	}


	public BigDecimal getTotalReceiveMaterialAmount() {
		return totalReceiveMaterialAmount;
	}


	public void setTotalReceiveMaterialAmount(BigDecimal totalReceiveMaterialAmount) {
		this.totalReceiveMaterialAmount = totalReceiveMaterialAmount;
	}


	private List<ProjectCategory>  data;
	
	
	public BigDecimal getTotalProjectAmount() {
		return totalProjectAmount;
	}


	public void setTotalProjectAmount(BigDecimal totalProjectAmount) {
		this.totalProjectAmount = totalProjectAmount;
	}


	public BigDecimal getTotalDonateAmount() {
		return totalDonateAmount;
	}


	public void setTotalDonateAmount(BigDecimal totalDonateAmount) {
		this.totalDonateAmount = totalDonateAmount;
	}


	public BigDecimal getTotalReceiveAmount() {
		return totalReceiveAmount;
	}


	public void setTotalReceiveAmount(BigDecimal totalReceiveAmount) {
		this.totalReceiveAmount = totalReceiveAmount;
	}


	public BigDecimal getTotalBalanceAmount() {
		return totalBalanceAmount;
	}


	public void setTotalBalanceAmount(BigDecimal totalBalanceAmount) {
		this.totalBalanceAmount = totalBalanceAmount;
	}


	public List<ProjectCategory> getData() {
		return data;
	}


	public void setData(List<ProjectCategory> data) {
		this.data = data;
	}


	
    
}
