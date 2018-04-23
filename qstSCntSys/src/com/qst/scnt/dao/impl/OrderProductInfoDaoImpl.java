package com.qst.scnt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.OrderProductInfoDao;
import com.qst.scnt.model.OrderProductInfo;

@Repository("orderProductInfoDao")
public class OrderProductInfoDaoImpl extends BaseDaoImpl<OrderProductInfo> implements OrderProductInfoDao{

	// mapper.xmlÖÐµÄnamespace  
    private String namespace;
    
	public OrderProductInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.OrderProductInfoDaoImpl");
		namespace="com.qst.scnt.dao.impl.OrderProductInfoDaoImpl";
	}

	@Override
	public List<OrderProductInfo> selectOProductByOrderID(OrderProductInfo orderProductInfo) {
		try {  
            return this.getSqlSession().selectList(namespace + "." + "selectOProductByOrderID",orderProductInfo);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}

}

