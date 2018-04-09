package com.qst.scnt.dao.impl;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.CostDao;
import com.qst.scnt.model.Cost;

@Repository("costDao")
public class CostDaoImpl extends BaseDaoImpl<Cost> implements CostDao {
	
	// mapper.xmlÖÐµÄnamespace  
    private String namespace;
    
	public CostDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.CostDaoImpl");
		namespace="com.qst.scnt.dao.impl.CostDaoImpl";
	}
	
}
