package com.qst.scnt.model;

import java.math.BigDecimal;

public class OrderProductInfo {
    /**
     *主键（自增）
     */
    private Integer id;

    /**
     *订单ID（外键）
     */
    private Integer orderID;

    /**
     *产品ID（外键）
     */
    private Integer productID;

    /**
     *产品名称（外键）
     */
    private String productName;
    
    /**
     *购买数量
     */
    private BigDecimal amount;

    /**
     *购买单价
     */
    private BigDecimal price;

    /**
     *购买总价
     */
    private BigDecimal totalMoney;

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
     *订单ID（外键）
     */
    public Integer getOrderID() {
        return orderID;
    }

    /**
     *订单ID（外键）
     */
    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    /**
     *产品ID（外键）
     */
    public Integer getProductID() {
        return productID;
    }

    /**
     *产品ID（外键）
     */
    public void setProductID(Integer productID) {
        this.productID = productID == null ? null : productID;
    }
    
    /**
     *产品名称（外键）
     */
    public String getProductName() {
		return productName;
	}

    /**
     *产品名称（外键）
     */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
     *购买数量
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     *购买数量
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     *购买单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     *购买单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     *购买总价
     */
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    /**
     *购买总价
     */
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
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