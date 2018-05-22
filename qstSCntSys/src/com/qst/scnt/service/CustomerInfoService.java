package com.qst.scnt.service;

import java.util.List;
import java.util.Map;

import com.qst.scnt.model.CustomerInfo;
import com.qst.scnt.utils.EUDataGridResult;

public interface CustomerInfoService extends BaseService<CustomerInfo> {
	
	public List selectByCNameAndCPhone(String cName,String cPhone);

	public EUDataGridResult<CustomerInfo> selectByCNameAndCPhone(Map<String, Object> queryDate, int pageNum,int pageSize);

}
