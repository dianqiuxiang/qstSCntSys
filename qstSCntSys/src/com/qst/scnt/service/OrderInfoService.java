package com.qst.scnt.service;

import java.util.List;
import java.util.Map;

import com.qst.scnt.model.Cost;
import com.qst.scnt.model.OrderInfo;
import com.qst.scnt.model.OrderProductInfo;
import com.qst.scnt.utils.EUDataGridResult;

public interface OrderInfoService extends BaseService<OrderInfo> {

	public EUDataGridResult<Cost> selectByCNameAndCTypeAndDate(Map<String, Object> queryDate, int pageNum, int pageSize);

	public int insertOrderAndOProductInfo(OrderInfo orderInfo);

	public int updateOrderAndOProductInfo(OrderInfo orderInfo);

	public List<OrderProductInfo> selectOProductByOrderID(OrderProductInfo orderProductInfo);

	public int deleteOrderAndOProduct(OrderInfo orderInfo);
	
}

