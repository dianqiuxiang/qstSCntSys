package com.qst.scnt.dao.impl;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.EveryMonthOtherInfoDao;
import com.qst.scnt.model.EveryMonthOtherInfo;

@Repository("everyMonthOtherInfoDao")
public class EveryMonthOtherInfoDaoImpl extends BaseDaoImpl<EveryMonthOtherInfo> implements EveryMonthOtherInfoDao {

	public EveryMonthOtherInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.EveryMonthOtherInfoDaoImpl");		
	}
	
}

