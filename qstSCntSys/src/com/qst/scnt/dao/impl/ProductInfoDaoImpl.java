package com.qst.scnt.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.ProductInfoDao;
import com.qst.scnt.model.ProductInfo;

@Repository("productInfoDao")
public class ProductInfoDaoImpl extends BaseDaoImpl<ProductInfo> implements ProductInfoDao {

	// mapper.xmlÖÐµÄnamespace  
    private String namespace;
    
	public ProductInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.ProductInfoDaoImpl");	
		namespace="com.qst.scnt.dao.impl.ProductInfoDaoImpl";
	}
}

