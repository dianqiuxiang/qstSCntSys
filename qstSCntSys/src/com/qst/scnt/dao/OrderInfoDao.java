package com.qst.scnt.dao;

import java.util.List;
import java.util.Map;

import com.qst.scnt.model.Cost;
import com.qst.scnt.model.OrderInfo;

/**
    * OrderInfo	¶©µ¥DAO
    * Wed Dec 27 15:51:34 CST 2017
    */ 


public interface OrderInfoDao  extends BaseDao<OrderInfo> {

	List<Cost> selectByOCodeandCNameAndDate(Map<String, Object> queryDate);
	
}

