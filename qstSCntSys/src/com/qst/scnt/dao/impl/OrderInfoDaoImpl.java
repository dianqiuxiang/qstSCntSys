package com.qst.scnt.dao.impl;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.OrderInfoDao;
import com.qst.scnt.model.OrderInfo;

@Repository("orderInfoDao")
public class OrderInfoDaoImpl extends BaseDaoImpl<OrderInfo> implements OrderInfoDao {

	public OrderInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.OrderInfoDaoImpl");		
	}
	
}

