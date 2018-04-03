package com.qst.scnt.service.impl;

import javax.annotation.Resource;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.OrderInfoDao;
import com.qst.scnt.model.OrderInfo;
import com.qst.scnt.service.OrderInfoService;

public class OrderInfoServiceImpl extends BaseServiceImpl<OrderInfo> implements OrderInfoService {

	@Resource
	private OrderInfoDao orderInfoDao;
	
	@Override
	public BaseDao<OrderInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return orderInfoDao;
	}
	
}

