package com.qst.scnt.model;

import java.math.BigDecimal;
import java.util.Date;

public class EveryMonthOtherInfo {
    /**
     *
     */
    private Integer id;

    /**
     *管理费用
     */
    private BigDecimal manageCost;

    /**
     *期初人数
     */
    private Integer earlyNumber;

    /**
     *期末人数
     */
    private Integer finalNumber;

    /**
     *单笔累计赠送超标金额
     */
    private BigDecimal singleExcessAmount;

    /**
     *整体考量赠送超标金额
     */
    private BigDecimal overallExcessAmount;

    /**
     *部门编号（外键）
     */
    private Integer salesDepartmentID;

    /**
     *月报其他信息时间
     */
    private Date infoDate;

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
     *管理费用
     */
    public BigDecimal getManageCost() {
        return manageCost;
    }

    /**
     *管理费用
     */
    public void setManageCost(BigDecimal manageCost) {
        this.manageCost = manageCost;
    }

    /**
     *期初人数
     */
    public Integer getEarlyNumber() {
        return earlyNumber;
    }

    /**
     *期初人数
     */
    public void setEarlyNumber(Integer earlyNumber) {
        this.earlyNumber = earlyNumber;
    }

    /**
     *期末人数
     */
    public Integer getFinalNumber() {
        return finalNumber;
    }

    /**
     *期末人数
     */
    public void setFinalNumber(Integer finalNumber) {
        this.finalNumber = finalNumber;
    }

    /**
     *单笔累计赠送超标金额
     */
    public BigDecimal getSingleExcessAmount() {
        return singleExcessAmount;
    }

    /**
     *单笔累计赠送超标金额
     */
    public void setSingleExcessAmount(BigDecimal singleExcessAmount) {
        this.singleExcessAmount = singleExcessAmount;
    }

    /**
     *整体考量赠送超标金额
     */
    public BigDecimal getOverallExcessAmount() {
        return overallExcessAmount;
    }

    /**
     *整体考量赠送超标金额
     */
    public void setOverallExcessAmount(BigDecimal overallExcessAmount) {
        this.overallExcessAmount = overallExcessAmount;
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
     *月报其他信息时间
     */
    public Date getInfoDate() {
        return infoDate;
    }

    /**
     *月报其他信息时间
     */
    public void setInfoDate(Date infoDate) {
        this.infoDate = infoDate;
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