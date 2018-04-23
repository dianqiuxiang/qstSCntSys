package com.qst.scnt.dao;

import java.util.List;

import com.qst.scnt.model.OrderProductInfo;

/**
    * OrderProductInfo	¶©µ¥²úÆ·DAO
    * Wed Dec 27 15:51:34 CST 2017
    */ 


public interface OrderProductInfoDao  extends BaseDao<OrderProductInfo> {

	public List<OrderProductInfo> selectOProductByOrderID(OrderProductInfo orderProductInfo);
	
}

