package com.qst.scnt.dao;

import java.util.List;
import java.util.Map;

import com.qst.scnt.model.Cost;

/**
 * Cost	∑—”√DAO
 * Wed Dec 27 15:51:34 CST 2017
 */ 

public interface CostDao  extends BaseDao<Cost> {

	List<Cost> selectByItemIdAndStartAndEndDate(Map<String, Object> queryDate);

}
