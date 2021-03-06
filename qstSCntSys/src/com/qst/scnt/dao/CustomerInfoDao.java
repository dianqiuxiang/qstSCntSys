package com.qst.scnt.dao;

import java.util.List;
import java.util.Map;

import com.qst.scnt.model.CustomerInfo;

/**
 * CustomerInfo	�˿�DAO
 * Wed Dec 27 15:51:34 CST 2017
 */ 

public interface CustomerInfoDao  extends BaseDao<CustomerInfo> {

	public List selectByCNameAndCPhone(String cName,String cPhone);

	public List<CustomerInfo> selectByCNameAndCPhone(Map<String, Object> queryDate);
}
