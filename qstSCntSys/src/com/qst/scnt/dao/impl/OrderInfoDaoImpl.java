package com.qst.scnt.dao.impl;

import com.qst.scnt.dao.OrderInfoDao;
import com.qst.scnt.model.OrderInfo;

public class OrderInfoDaoImpl extends BaseDaoImpl<OrderInfo> implements OrderInfoDao {

	public OrderInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.OrderInfoDaoImpl");		
	}
	
}

