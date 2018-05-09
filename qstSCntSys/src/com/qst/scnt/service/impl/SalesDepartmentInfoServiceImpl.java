package com.qst.scnt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.SalesDepartmentInfoDao;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.service.SalesDepartmentInfoService;

@Service("salesDepartmentInfoService")
public class SalesDepartmentInfoServiceImpl extends BaseServiceImpl<SalesDepartmentInfo> implements SalesDepartmentInfoService {

	@Resource    
    private SalesDepartmentInfoDao salesDepartmentInfoDao;
    
	@Override
	public BaseDao<SalesDepartmentInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return salesDepartmentInfoDao;
	}

	@Override
	public List<SalesDepartmentInfo> selectAll() {
		// TODO Auto-generated method stub
		return salesDepartmentInfoDao.selectAll();
	}
	
}

