package com.qst.scnt.service;

import java.util.Map;

import com.qst.scnt.model.EmployeeInfo;
import com.qst.scnt.utils.EUDataGridResult;

public interface EmployeeInfoService extends BaseService<EmployeeInfo> {

	public EUDataGridResult<EmployeeInfo> selectByNameAndSex(Map<String, Object> queryParam, int pageNum, int pageSize);
	
}

