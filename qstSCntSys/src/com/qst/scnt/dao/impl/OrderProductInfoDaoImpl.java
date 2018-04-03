package com.qst.scnt.dao.impl;

import com.qst.scnt.dao.OrderProductInfoDao;
import com.qst.scnt.model.OrderProductInfo;

public class OrderProductInfoDaoImpl extends BaseDaoImpl<OrderProductInfo> implements OrderProductInfoDao{

	public OrderProductInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.OrderProductInfoDaoImpl");		
	}

}

