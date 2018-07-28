package com.qst.scnt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qst.scnt.model.Cost;
import com.qst.scnt.model.OrderInfo;



public interface SorcReciPayExesDetailDao  extends BaseDao<Object> {
	
	public List countNewResour(Map<String, Object> params);
	public List countProductNum(Map<String, Object> params);
}

