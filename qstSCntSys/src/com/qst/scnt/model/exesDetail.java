package com.qst.scnt.model;

public class exesDetail {
	private String id;
    private String expenseDate;//日期
    private String pName;//市场部
    private String deptName;//签约部门
    private String certificateId;//凭证号
    private String remark;//摘要
    private String pettycash;//备用金
	private String ffItemName;//一级费用项目
	private String pItemName;//二级费用项目
	private String expenseItem;//三级费用项目
	private String expenseAmount;//费用金额
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCertificateId() {
		return certificateId;
	}
	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPettycash() {
		return pettycash;
	}
	public void setPettycash(String pettycash) {
		this.pettycash = pettycash;
	}
	public String getFfItemName() {
		return ffItemName;
	}
	public void setFfItemName(String ffItemName) {
		this.ffItemName = ffItemName;
	}
	public String getpItemName() {
		return pItemName;
	}
	public void setpItemName(String pItemName) {
		this.pItemName = pItemName;
	}
	public String getExpenseItem() {
		return expenseItem;
	}
	public void setExpenseItem(String expenseItem) {
		this.expenseItem = expenseItem;
	}
	public String getExpenseAmount() {
		return expenseAmount;
	}
	public void setExpenseAmount(String expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	
	

}
