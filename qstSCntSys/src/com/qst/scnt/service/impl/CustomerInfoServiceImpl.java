package com.qst.scnt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.CustomerInfoDao;
import com.qst.scnt.model.CustomerInfo;
import com.qst.scnt.service.CustomerInfoService;

@Service("customerInfoService")
public class CustomerInfoServiceImpl extends BaseServiceImpl<CustomerInfo> implements CustomerInfoService {

	@Resource
	private CustomerInfoDao customerInfoDao;
	
	@Override
	public BaseDao<CustomerInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return customerInfoDao;
	}

	@Override
	public List selectByCNameAndCPhone(String cName, String cPhone) {
		// TODO Auto-generated method stub
		return null;
	}

}
