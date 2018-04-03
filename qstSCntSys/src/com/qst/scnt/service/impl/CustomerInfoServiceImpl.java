package com.qst.scnt.service.impl;

import javax.annotation.Resource;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.CustomerInfoDao;
import com.qst.scnt.model.CustomerInfo;
import com.qst.scnt.service.CustomerInfoService;

public class CustomerInfoServiceImpl extends BaseServiceImpl<CustomerInfo> implements CustomerInfoService {

	@Resource
	private CustomerInfoDao customerInfoDao;
	
	@Override
	public BaseDao<CustomerInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return customerInfoDao;
	}

}
