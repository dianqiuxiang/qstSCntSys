package com.qst.scnt.service.impl;

import javax.annotation.Resource;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.EveryMonthOtherInfoDao;
import com.qst.scnt.model.EveryMonthOtherInfo;
import com.qst.scnt.service.EveryMonthOtherInfoService;

public class EveryMonthOtherInfoServiceImpl  extends  BaseServiceImpl<EveryMonthOtherInfo> implements EveryMonthOtherInfoService {

	@Resource
	private EveryMonthOtherInfoDao everyMonthOtherInfoDao;
		
	@Override
	public BaseDao<EveryMonthOtherInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return everyMonthOtherInfoDao;
	}
	
}

