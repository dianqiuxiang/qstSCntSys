package com.qst.scnt.dao.impl;

import com.qst.scnt.dao.ReceiptInfoDao;
import com.qst.scnt.model.ReceiptInfo;

public class ReceiptInfoDaoImpl extends BaseDaoImpl<ReceiptInfo> implements ReceiptInfoDao {

	public ReceiptInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.ReceiptInfoDaoImpl");		
	}
	
}

