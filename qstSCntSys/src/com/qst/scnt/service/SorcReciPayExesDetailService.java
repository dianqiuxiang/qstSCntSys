package com.qst.scnt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qst.scnt.model.Cost;
import com.qst.scnt.model.EmployeeInfo;
import com.qst.scnt.model.OrderInfo;
import com.qst.scnt.model.OrderProductInfo;
import com.qst.scnt.utils.EUDataGridResult;

public interface SorcReciPayExesDetailService extends BaseService<Object> {
	
	List countNewResour(Map<String,Object> params);
	List countProductNumexcel(Map<String,Object> params);
	/**
	 * 签约部门新资源排名
	 * @param params
	 * @return
	 */
	EUDataGridResult<Map> countNewResourec(Map<String,Object> params);
	/**
	 * 签约部门新资源排名
	 * @param params
	 * @return
	 */
	EUDataGridResult<Map> countProductNum(Map<String,Object> params);
}

