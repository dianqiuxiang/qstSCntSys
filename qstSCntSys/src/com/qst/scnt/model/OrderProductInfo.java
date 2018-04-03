package com.qst.scnt.model;

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
     *购买数量
     */
    private Long amount;

    /**
     *购买单价
     */
    private Long price;

    /**
     *购买总价
     */
    private Long totalMoney;

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
     *购买数量
     */
    public Long getAmount() {
        return amount;
    }

    /**
     *购买数量
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     *购买单价
     */
    public Long getPrice() {
        return price;
    }

    /**
     *购买单价
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     *购买总价
     */
    public Long getTotalMoney() {
        return totalMoney;
    }

    /**
     *购买总价
     */
    public void setTotalMoney(Long totalMoney) {
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