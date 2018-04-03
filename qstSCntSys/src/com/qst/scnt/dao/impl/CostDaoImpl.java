package com.qst.scnt.dao.impl;

import com.qst.scnt.dao.CostDao;
import com.qst.scnt.model.Cost;

public class CostDaoImpl extends BaseDaoImpl<Cost> implements CostDao {

	public CostDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.CostDaoImpl");		
	}
	
}
