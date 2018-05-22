package com.qst.scnt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.OrderInfoDao;
import com.qst.scnt.model.Cost;
import com.qst.scnt.model.OrderInfo;

@Repository("orderInfoDao")
public class OrderInfoDaoImpl extends BaseDaoImpl<OrderInfo> implements OrderInfoDao {

	// mapper.xml�е�namespace  
    private String namespace;
    
	public OrderInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.OrderInfoDaoImpl");	
		namespace="com.qst.scnt.dao.impl.OrderInfoDaoImpl";
	}

	@Override
	public List<OrderInfo> selectByOCodeandCNameAndDate(Map<String, Object> queryDate) {
		try {  
            return this.getSqlSession().selectList(namespace + "." + "selectByOCodeandCNameAndDate",queryDate);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}
	
}

