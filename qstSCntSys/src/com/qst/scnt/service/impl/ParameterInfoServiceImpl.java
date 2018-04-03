package com.qst.scnt.service.impl;

import javax.annotation.Resource;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.ParameterInfoDao;
import com.qst.scnt.model.ParameterInfo;
import com.qst.scnt.service.ParameterInfoService;

public class ParameterInfoServiceImpl extends BaseServiceImpl<ParameterInfo> implements ParameterInfoService {

	@Resource
	private ParameterInfoDao parameterInfoDao;
	
	@Override
	public BaseDao<ParameterInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return parameterInfoDao;
	}
	
}

