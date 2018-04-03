package com.qst.scnt.service.impl;

import javax.annotation.Resource;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.EmployeeInfoDao;
import com.qst.scnt.model.EmployeeInfo;
import com.qst.scnt.service.EmployeeInfoService;

public class EmployeeInfoServiceImpl extends  BaseServiceImpl<EmployeeInfo> implements EmployeeInfoService {

	@Resource
	private EmployeeInfoDao employeeInfoDao;
	
	@Override
	public BaseDao<EmployeeInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return employeeInfoDao;
	}
	
}

