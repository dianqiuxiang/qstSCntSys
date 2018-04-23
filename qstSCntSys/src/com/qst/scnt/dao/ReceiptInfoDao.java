package com.qst.scnt.dao;

import java.util.List;
import java.util.Map;

import com.qst.scnt.model.ReceiptInfo;

/**
    * ReceiptInfo	 ’øÓDAO
    * Wed Dec 27 15:51:34 CST 2017
    */ 


public interface ReceiptInfoDao  extends BaseDao<ReceiptInfo> {

	public List<ReceiptInfo> selectByCodeAndRMemberAndDate(Map<String, Object> queryDate);

	public ReceiptInfo selectByID(int ID);
	

}

