package com.qst.scnt.dao.impl;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.SalesDepartmentInfoDao;
import com.qst.scnt.model.SalesDepartmentInfo;

@Repository("salesDepartmentInfoDao")
public class SalesDepartmentInfoDaoImpl extends BaseDaoImpl<SalesDepartmentInfo> implements SalesDepartmentInfoDao {

	// mapper.xmlÖÐµÄnamespace  
    private String namespace;
    
	public SalesDepartmentInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.SalesDepartmentInfoDaoImpl");	
		namespace="com.qst.scnt.dao.impl.SalesDepartmentInfoDaoImpl";
	}

}

