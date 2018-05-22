package com.qst.scnt.model;

import java.math.BigDecimal;
import java.util.List;

public class OrderInfo {
    /**
     *主键（自增）
     */
	
    private Integer id;

    /**
     *订单编号
     */	
    private String orderCode;

    /**
     *会员类型
     */
    private String customerType;

    /**
     *顾客联系电话
     */
    private String customerPhone;

    /**
     *顾客姓名
     */
    private String customerName;

    /**
     *顾客住址
     */
    private String customerAddress;

    /**
     *订单时间
     */
    private String orderDate;

    /**
     *订单金额
     */
    private BigDecimal orderAmount;

    /**
     *健康代表
     */
    private String healthMember;

    /**
     *顾客签收
     */
    private String customerSign;

    /**
     *部门编号（外键）
     */
    private Integer salesDepartmentID;
    
    /**
     *部门名称
     */
    private String salesDepartmentName;

    /**
     *是否删除：0 没删除，1 删除
     */
    private Integer isDelete;

    private List<OrderProductInfo> products;
    
    private List<OrderProductInfo> insertProducts;
    
    private List<OrderProductInfo> updateProducts;
    
    private List<OrderProductInfo> deleteProducts;    
    
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
     *订单编号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     *订单编号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     *会员类型
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     *会员类型
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    /**
     *顾客联系电话
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     *顾客联系电话
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone == null ? null : customerPhone.trim();
    }

    /**
     *顾客姓名
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *顾客姓名
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     *顾客住址
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     *顾客住址
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     *订单时间
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     *订单时间
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     *订单金额
     */
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     *订单金额
     */
    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     *健康代表
     */
    public String getHealthMember() {
        return healthMember;
    }

    /**
     *健康代表
     */
    public void setHealthMember(String healthMember) {
        this.healthMember = healthMember == null ? null : healthMember.trim();
    }

    /**
     *顾客签收
     */
    public String getCustomerSign() {
        return customerSign;
    }

    /**
     *顾客签收
     */
    public void setCustomerSign(String customerSign) {
        this.customerSign = customerSign == null ? null : customerSign.trim();
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
     *部门名称
     */    
    public String getSalesDepartmentName() {
		return salesDepartmentName;
	}

    /**
     *部门名称
     */ 
	public void setSalesDepartmentName(String salesDepartmentName) {
		this.salesDepartmentName = salesDepartmentName;
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

	public List<OrderProductInfo> getProducts() {
		return products;
	}

	public void setProducts(List<OrderProductInfo> products) {
		this.products = products;
	}

	public List<OrderProductInfo> getInsertProducts() {
		return insertProducts;
	}

	public void setInsertProducts(List<OrderProductInfo> insertProducts) {
		this.insertProducts = insertProducts;
	}

	public List<OrderProductInfo> getUpdateProducts() {
		return updateProducts;
	}

	public void setUpdateProducts(List<OrderProductInfo> updateProducts) {
		this.updateProducts = updateProducts;
	}

	public List<OrderProductInfo> getDeleteProducts() {
		return deleteProducts;
	}

	public void setDeleteProducts(List<OrderProductInfo> deleteProducts) {
		this.deleteProducts = deleteProducts;
	}
    
	
    
}