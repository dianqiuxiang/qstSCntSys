package com.qst.scnt.dao.impl;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.CustomerInfoDao;
import com.qst.scnt.model.CustomerInfo;

@Repository("customerInfoDao")
public class CustomerInfoDaoImpl extends BaseDaoImpl<CustomerInfo> implements CustomerInfoDao {

	public CustomerInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.CustomerInfoDaoImpl");		
	}
	
}
