package com.qst.scnt.service;

import java.util.List;

import com.qst.scnt.model.OrderProductInfo;

public interface OrderProductInfoService extends BaseService<OrderProductInfo> {

	public List<OrderProductInfo> selectOProductByOrderID(OrderProductInfo orderProductInfo);
	
}

