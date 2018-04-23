package com.qst.scnt.model;

import java.math.BigDecimal;
import java.util.Date;

public class ReceiptInfo {
    /**
     *主键
     */
    private Integer id;

    /**
     *订单ID
     */
    private Integer orderID;
    
    /**
     *订单编号
     */
    private String orderCode;

    /**
     *收款日期
     */
    private String receiptDate;

    /**
     *收款金额
     */
    private BigDecimal receiptAmount;

    /**
     *收款人
     */
    private String receiptMember;

    /**
     *部门编号（外键）
     */
    private Integer salesDepartmentID;

    /**
     *备注
     */
    private String remark;

    /**
     *是否删除：0 没删除，1 删除
     */
    private Integer isDelete;

    /**
     *主键
     */
    public Integer getId() {
        return id;
    }

    /**
     *主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *订单ID
     */
    public Integer getOrderID() {
        return orderID;
    }

    /**
     *订单ID
     */
    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }
    
    /**
     *订单编号
     */
    public String getOrderCode() {
		return orderCode;
	}

    /**
     *订单编号
     */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	/**
     *收款日期
     */
    public String getReceiptDate() {
        return receiptDate;
    }

    /**
     *收款日期
     */
    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    /**
     *收款金额
     */
    public BigDecimal getReceiptAmount() {
        return receiptAmount;
    }

    /**
     *收款金额
     */
    public void setReceiptAmount(BigDecimal receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    /**
     *收款人
     */
    public String getReceiptMember() {
        return receiptMember;
    }

    /**
     *收款人
     */
    public void setReceiptMember(String receiptMember) {
        this.receiptMember = receiptMember == null ? null : receiptMember.trim();
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
     *备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     *备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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