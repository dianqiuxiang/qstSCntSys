package com.qst.scnt.model;

public class CustomerInfo {
    /**
     *主键（自增）
     */
    private Integer id;

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
        this.customerAddress = customerAddress == null ? null : customerAddress.trim();
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