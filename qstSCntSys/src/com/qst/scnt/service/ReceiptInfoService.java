package com.qst.scnt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qst.scnt.model.ReceiptInfo;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.utils.EUDataGridResult;

public interface ReceiptInfoService extends BaseService<ReceiptInfo> {

	public EUDataGridResult<ReceiptInfo> selectByCodeAndRMemberAndDate(Map<String, Object> queryDate,int pageNum, int pageSize);

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

