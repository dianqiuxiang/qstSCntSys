package com.qst.scnt.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qst.scnt.model.EveryMonthOtherInfo;

/**
    * EveryMonthOtherInfo	月报其他信息DAO
    * Wed Dec 27 15:51:34 CST 2017
    */ 


public interface EveryMonthOtherInfoDao  extends BaseDao<EveryMonthOtherInfo> {
	
	public List<EveryMonthOtherInfo> selectByStartAndEndDate(Map<String, Object> queryDate);
	public List<EveryMonthOtherInfo> selectByDate(Map<String, Object> infoDate);
	public HashMap selectBySDeptIdAndYM(Map<String, Object> params);
	public HashMap selectBySDeptIdAndY(Map<String, Object> params);
}

