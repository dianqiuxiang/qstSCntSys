package com.qst.scnt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.EmployeeInfoDao;
import com.qst.scnt.model.EmployeeInfo;
import com.qst.scnt.service.EmployeeInfoService;

@Service("employeeInfoService")
public class EmployeeInfoServiceImpl extends  BaseServiceImpl<EmployeeInfo> implements EmployeeInfoService {

	@Resource
	private EmployeeInfoDao employeeInfoDao;
	
	@Override
	public BaseDao<EmployeeInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return employeeInfoDao;
	}
	
}

