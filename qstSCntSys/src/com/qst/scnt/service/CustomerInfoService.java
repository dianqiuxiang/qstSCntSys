package com.qst.scnt.service;

import java.util.List;

import com.qst.scnt.model.CustomerInfo;

public interface CustomerInfoService extends BaseService<CustomerInfo> {
	
	public List selectByCNameAndCPhone(String cName,String cPhone);

}
