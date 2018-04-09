package com.qst.scnt.dao.impl;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.OrderProductInfoDao;
import com.qst.scnt.model.OrderProductInfo;

@Repository("orderProductInfoDao")
public class OrderProductInfoDaoImpl extends BaseDaoImpl<OrderProductInfo> implements OrderProductInfoDao{

	public OrderProductInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.OrderProductInfoDaoImpl");		
	}

}

