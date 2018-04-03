package com.qst.scnt.model;

import java.math.BigDecimal;

public class ProductInfo {
    /**
     *主键（自增）
     */
    private Integer id;

    /**
     *产品名称
     */
    private String productName;

    /**
     *产品单位
     */
    private String productUnit;

    /**
     *产品单价
     */
    private BigDecimal productPrice;

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
     *产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     *产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    /**
     *产品单位
     */
    public String getProductUnit() {
        return productUnit;
    }

    /**
     *产品单位
     */
    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit == null ? null : productUnit.trim();
    }

    /**
     *产品单价
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    /**
     *产品单价
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
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