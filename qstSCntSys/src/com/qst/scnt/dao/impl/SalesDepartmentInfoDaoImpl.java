package com.qst.scnt.dao.impl;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.SalesDepartmentInfoDao;
import com.qst.scnt.model.SalesDepartmentInfo;

@Repository("salesDepartmentInfoDao")
public class SalesDepartmentInfoDaoImpl extends BaseDaoImpl<SalesDepartmentInfo> implements SalesDepartmentInfoDao {

	public SalesDepartmentInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.SalesDepartmentInfoDaoImpl");		
	}

}

