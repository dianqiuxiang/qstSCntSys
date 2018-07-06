package com.qst.scnt.dao;

import java.util.HashMap;
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

	public HashMap selectBySDeptIdAndYM(Map<String, Object> params);

	public HashMap selectSalesVolume(Map<String, Object> params);

	public HashMap selectNewCustomerSalesVolume(Map<String, Object> params);

	public HashMap selectBySDeptIdAndY(Map<String, Object> params);

	public HashMap selectSalesVolume1(Map<String, Object> params);

	public HashMap selectNewCustomerSalesVolume1(Map<String, Object> params);

	public List<ReceiptInfo> selectByOrderID(Map<String, Object> orParams);
	

}

