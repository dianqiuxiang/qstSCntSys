package com.qst.scnt.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.ParameterInfoDao;
import com.qst.scnt.model.ParameterInfo;
import com.qst.scnt.service.ParameterInfoService;

@Service("parameterInfoService")
public class ParameterInfoServiceImpl extends BaseServiceImpl<ParameterInfo> implements ParameterInfoService {

	@Resource
	private ParameterInfoDao parameterInfoDao;
	
	@Override
	public BaseDao<ParameterInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return parameterInfoDao;
	}
	
}

