package com.qst.scnt.dao;

import java.util.List;

import com.qst.scnt.model.SalesDepartmentInfo;

/**
    * SalesDepartmentInfo	���۲���DAO
    * Wed Dec 27 15:51:34 CST 2017
    */ 


public interface SalesDepartmentInfoDao  extends BaseDao<SalesDepartmentInfo> {

	public List<SalesDepartmentInfo> selectAll();
	
}

