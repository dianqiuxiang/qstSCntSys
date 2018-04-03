package com.qst.scnt.service.impl;

import javax.annotation.Resource;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.SalesDepartmentInfoDao;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.service.SalesDepartmentInfoService;

public class SalesDepartmentInfoServiceImpl extends BaseServiceImpl<SalesDepartmentInfo> implements SalesDepartmentInfoService {

	@Resource    
    private SalesDepartmentInfoDao salesDepartmentInfoDao;
    
	@Override
	public BaseDao<SalesDepartmentInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return salesDepartmentInfoDao;
	}
	
}

