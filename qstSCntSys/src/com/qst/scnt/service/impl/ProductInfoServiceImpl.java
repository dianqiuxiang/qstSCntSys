package com.qst.scnt.service.impl;

import javax.annotation.Resource;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.ProductInfoDao;
import com.qst.scnt.model.ProductInfo;
import com.qst.scnt.service.ProductInfoService;

public class ProductInfoServiceImpl extends BaseServiceImpl<ProductInfo> implements ProductInfoService {

	@Resource
	private ProductInfoDao productInfoDao;
	
	@Override
	public BaseDao<ProductInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return productInfoDao;
	}
	
}

