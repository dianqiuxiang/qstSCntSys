package com.qst.scnt.dao.impl;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.EmployeeInfoDao;
import com.qst.scnt.model.EmployeeInfo;

@Repository("employeeInfoDao")
public class EmployeeInfoDaoImpl extends BaseDaoImpl<EmployeeInfo> implements EmployeeInfoDao {

	public EmployeeInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.EmployeeInfoDaoImpl");		
	}
	
}

