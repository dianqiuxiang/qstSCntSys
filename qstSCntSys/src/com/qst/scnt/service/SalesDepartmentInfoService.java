package com.qst.scnt.service;

import java.util.List;

import com.qst.scnt.model.SalesDepartmentInfo;

public interface SalesDepartmentInfoService extends BaseService<SalesDepartmentInfo> {

	public List<SalesDepartmentInfo> selectAll();

	public int deleteItselfAndItsChildren(SalesDepartmentInfo salesDepartmentInfo);
	

}

