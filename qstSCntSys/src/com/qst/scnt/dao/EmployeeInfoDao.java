package com.qst.scnt.dao;

import java.util.List;
import java.util.Map;

import com.qst.scnt.model.EmployeeInfo;

/**
    * EmployeeInfo	Ô±¹¤DAO
    * Wed Dec 27 15:51:34 CST 2017
    */ 


public interface EmployeeInfoDao  extends BaseDao<EmployeeInfo> {

	public List<EmployeeInfo> selectByNameAndSex(Map<String, Object> queryParam);
	
}

