package com.qst.scnt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qst.scnt.model.Cost;
import com.qst.scnt.model.OrderInfo;
import com.qst.scnt.model.exesDetail;



public interface SorcReciPayExesDetailDao  extends BaseDao<Object> {
	
	public List countNewResour(Map<String, Object> params);
	public List countProductNum(Map<String, Object> params);
	/**
	 * 市场部费用明细表
	 * @param params
	 * @return
	 */
	List<exesDetail> exesDetailExcelAll(Map<String,Object> params);	
}

