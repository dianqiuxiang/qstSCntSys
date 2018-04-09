package com.qst.scnt.dao.impl;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.ParameterInfoDao;
import com.qst.scnt.model.ParameterInfo;

@Repository("parameterInfoDao")
public class ParameterInfoDaoImpl extends BaseDaoImpl<ParameterInfo> implements ParameterInfoDao {

	// mapper.xmlÖÐµÄnamespace  
    private String namespace;
    
	public ParameterInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.ParameterInfoDaoImpl");	
		namespace="com.qst.scnt.dao.impl.ParameterInfoDaoImpl";
	}
	
}

