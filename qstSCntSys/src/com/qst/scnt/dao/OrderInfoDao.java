package com.qst.scnt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qst.scnt.model.Cost;
import com.qst.scnt.model.OrderInfo;

/**
    * OrderInfo	¶©µ¥DAO
    * Wed Dec 27 15:51:34 CST 2017
    */ 


public interface OrderInfoDao  extends BaseDao<OrderInfo> {

	List<OrderInfo> selectByOCodeandCNameAndDate(Map<String, Object> queryDate);

	HashMap selectNewCustomer(Map<String, Object> params);

	HashMap selectNewCustomer1(Map<String, Object> params);

	List<OrderInfo> selectByDate(Map<String, Object> oParams);
	
	List<Map> list(Map<String, Object> queryDate);//µ¼³ö
	
}

