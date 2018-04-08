package com.qst.scnt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.OrderProductInfoDao;
import com.qst.scnt.model.OrderProductInfo;
import com.qst.scnt.service.OrderProductInfoService;

@Service("orderProductInfoService")
public class OrderProductInfoServiceImpl extends BaseServiceImpl<OrderProductInfo> implements OrderProductInfoService{

	@Resource
	private OrderProductInfoDao orderProductInfoDao;
	
	@Override
	public BaseDao<OrderProductInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return orderProductInfoDao;
	}
	

}

