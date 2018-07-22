package com.qst.scnt.model;

public class UserInfo {
    /**
     *主键（自增）
     */
    private Integer id;

    /**
     *员工姓名
     */
    private String userName;

    /**
     *密码
     */
    private String pwd;

    /**
     *用户联系电话
     */
    private String userPhone;

    /**
     *用户邮箱
     */
    private String userEmail;

    /**
     *部门编号（外键）
     */
    private Integer salesDepartmentID;

    /**
     *是否删除：0 没删除，1 删除
     */
    private Integer isDelete;
    
    /**
     *用户类型，大区总部用户  or 市场部用户
     */
    private Integer userType;


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
     *员工姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     *员工姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     *密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     *密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    /**
     *用户联系电话
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     *用户联系电话
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     *用户邮箱
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     *用户邮箱
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
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
    
    /**
     *用户类型，大区总部用户  or 市场部用户
     */
	public Integer getUserType() {
		return userType;
	}
    
    /**
     *用户类型，大区总部用户  or 市场部用户
     */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

    
}