package com.qst.scnt.dao.impl;

import com.qst.scnt.dao.EmployeeInfoDao;
import com.qst.scnt.model.EmployeeInfo;

public class EmployeeInfoDaoImpl extends BaseDaoImpl<EmployeeInfo> implements EmployeeInfoDao {

	public EmployeeInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.EmployeeInfoDaoImpl");		
	}
	
}

