package com.qst.scnt.service;

import java.util.List;
import java.util.Map;

import com.qst.scnt.model.ReceiptInfo;
import com.qst.scnt.utils.EUDataGridResult;

public interface ReceiptInfoService extends BaseService<ReceiptInfo> {

	public EUDataGridResult<ReceiptInfo> selectByCodeAndRMemberAndDate(Map<String, Object> queryDate,int pageNum, int pageSize);

	public List<ReceiptInfo> selectByCodeAndRMemberAndDate(Map<String, Object> queryDate);
	
	public ReceiptInfo selectByID(int ID);
	
}

