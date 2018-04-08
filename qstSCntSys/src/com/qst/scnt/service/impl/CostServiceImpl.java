package com.qst.scnt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.CostDao;
import com.qst.scnt.model.Cost;
import com.qst.scnt.service.CostService;

@Service("costService")
public class CostServiceImpl extends BaseServiceImpl<Cost> implements CostService {

	@Resource
	private CostDao costDao;
	
		@Override
	public BaseDao<Cost> getBaseDao() {
		// TODO Auto-generated method stub
		return costDao;
	}

}
