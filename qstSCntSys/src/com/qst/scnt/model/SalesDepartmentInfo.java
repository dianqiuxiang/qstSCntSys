package com.qst.scnt.model;

public class SalesDepartmentInfo {
    /**
     *
     */
    private Integer id;

    /**
     *销售部名称
     */
    private String salesDepartmentName;

    /**
     *销售部地址
     */
    private String salesDepartmentAddress;

    /**
     *销售部联系电话
     */
    private String salesDepartmentPhone;

    /**
     *父亲节点
     */
    private Integer parentID;

    /**
     *是否删除：0 没删除，1 删除
     */
    private Integer isDelete;

    /**
     *
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *销售部名称
     */
    public String getSalesDepartmentName() {
        return salesDepartmentName;
    }

    /**
     *销售部名称
     */
    public void setSalesDepartmentName(String salesDepartmentName) {
        this.salesDepartmentName = salesDepartmentName == null ? null : salesDepartmentName.trim();
    }

    /**
     *销售部地址
     */
    public String getSalesDepartmentAddress() {
        return salesDepartmentAddress;
    }

    /**
     *销售部地址
     */
    public void setSalesDepartmentAddress(String salesDepartmentAddress) {
        this.salesDepartmentAddress = salesDepartmentAddress == null ? null : salesDepartmentAddress.trim();
    }

    /**
     *销售部联系电话
     */
    public String getSalesDepartmentPhone() {
        return salesDepartmentPhone;
    }

    /**
     *销售部联系电话
     */
    public void setSalesDepartmentPhone(String salesDepartmentPhone) {
        this.salesDepartmentPhone = salesDepartmentPhone == null ? null : salesDepartmentPhone.trim();
    }

    /**
     *父亲节点
     */
    public Integer getParentID() {
        return parentID;
    }

    /**
     *父亲节点
     */
    public void setParentID(Integer parentID) {
        this.parentID = parentID;
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