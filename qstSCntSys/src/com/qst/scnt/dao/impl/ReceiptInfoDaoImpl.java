package com.qst.scnt.dao.impl;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.ReceiptInfoDao;
import com.qst.scnt.model.ReceiptInfo;

@Repository("peceiptInfoDao")
public class ReceiptInfoDaoImpl extends BaseDaoImpl<ReceiptInfo> implements ReceiptInfoDao {

	public ReceiptInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.ReceiptInfoDaoImpl");		
	}
	
}

