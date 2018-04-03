package com.qst.scnt.model;

public class EmployeeInfo {
    /**
     *
     */
    private Integer id;

    /**
     *员工姓名
     */
    private String employeeName;

    /**
     *性别
     */
    private String sex;

    /**
     *员工联系电话
     */
    private String employeePhone;

    /**
     *员工住址
     */
    private String employeeAddress;

    /**
     *员工邮箱
     */
    private String employeeEmail;

    /**
     *部门编号（外键）
     */
    private Integer salesDepartmentID;

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
     *员工姓名
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     *员工姓名
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    /**
     *性别
     */
    public String getSex() {
        return sex;
    }

    /**
     *性别
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     *员工联系电话
     */
    public String getEmployeePhone() {
        return employeePhone;
    }

    /**
     *员工联系电话
     */
    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone == null ? null : employeePhone.trim();
    }

    /**
     *员工住址
     */
    public String getEmployeeAddress() {
        return employeeAddress;
    }

    /**
     *员工住址
     */
    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress == null ? null : employeeAddress.trim();
    }

    /**
     *员工邮箱
     */
    public String getEmployeeEmail() {
        return employeeEmail;
    }

    /**
     *员工邮箱
     */
    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail == null ? null : employeeEmail.trim();
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