package com.qst.scnt.dao;

import java.util.List;

import com.qst.scnt.model.CustomerInfo;

/**
 * CustomerInfo	¹Ë¿ÍDAO
 * Wed Dec 27 15:51:34 CST 2017
 */ 

public interface CustomerInfoDao  extends BaseDao<CustomerInfo> {

	public List selectByCNameAndCPhone(String cName,String cPhone);
}
