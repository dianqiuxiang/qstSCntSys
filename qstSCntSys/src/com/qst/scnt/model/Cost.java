package com.qst.scnt.model;

import java.math.BigDecimal;

public class Cost {
    /**
     *主键（自增）
     */
    private Integer id;

    /**
     *项目ID（外键）
     */
    private Integer expenseItemID;
    
    /**
     *项目名称（外键）
     */
    private String expenseItem;

    /**
     *费用金额
     */
    private BigDecimal expenseAmount;

    /**
     *费用时间
     */
    private String expenseDate;

    /**
     *部门编号（外键）
     */
    private Integer salesDepartmentID;

    /**
     *是否删除：0 没删除，1 删除
     */
    private Integer isDelete;

    /**
     *主键（自增）
     */
    public Integer getId() {
        return id;
    }

    /**
     *主键（自增）
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *产品名称（外键）
     */
    public Integer getExpenseItemID() {
        return expenseItemID;
    }

    /**
     *项目ID（外键）
     */
    public void setExpenseItemID(Integer expenseItemID) {
        this.expenseItemID = expenseItemID;
    }

    /**
     *项目名称（外键）
     */    
    public String getExpenseItem() {
		return expenseItem;
	}

	public void setExpenseIteme(String expenseItem) {
		this.expenseItem = expenseItem;
	}

	/**
     *费用金额
     */
    public BigDecimal getExpenseAmount() {
        return expenseAmount;
    }

    /**
     *费用金额
     */
    public void setExpenseAmount(BigDecimal expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    /**
     *费用时间
     */
    public String getExpenseDate() {
        return expenseDate;
    }

    /**
     *费用时间
     */
    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    /**
     *部门编号（外键）
     */
    public Integer getSalesDepartmentID() {
        return salesDepartmentID;
    }

    /**
     *部门编号（外键）
     */
    public void setSalesDepartmentID(Integer salesDepartmentID) {
        this.salesDepartmentID = salesDepartmentID;
    }

    /**
     *是否删除：0 没删除，1 删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     *是否删除：0 没删除，1 删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}