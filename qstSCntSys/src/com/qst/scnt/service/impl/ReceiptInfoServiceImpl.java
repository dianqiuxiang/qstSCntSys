package com.qst.scnt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.ReceiptInfoDao;
import com.qst.scnt.model.ReceiptInfo;
import com.qst.scnt.service.ReceiptInfoService;

@Service("receiptInfoService")
public class ReceiptInfoServiceImpl extends BaseServiceImpl<ReceiptInfo> implements ReceiptInfoService {

	@Resource
	private ReceiptInfoDao receiptInfoDao;
	
	@Override
	public BaseDao<ReceiptInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return receiptInfoDao;
	}
	

}

