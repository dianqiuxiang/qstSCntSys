package com.qst.scnt.dao.impl;

import com.qst.scnt.dao.ParameterInfoDao;
import com.qst.scnt.model.ParameterInfo;

public class ParameterInfoDaoImpl extends BaseDaoImpl<ParameterInfo> implements ParameterInfoDao {

	public ParameterInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.ParameterInfoDaoImpl");		
	}
	
}

