package com.qst.scnt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qst.scnt.model.Cost;

/**
 * Cost	∑—”√DAO
 * Wed Dec 27 15:51:34 CST 2017
 */ 

public interface CostDao  extends BaseDao<Cost> {

	public List<Cost> selectByItemIdAndStartAndEndDate(Map<String, Object> queryDate);

	public HashMap selectBySDeptIdAndYM(Map<String, Object> params);

	public HashMap selectBySDeptIdAndY(Map<String, Object> params);

}
