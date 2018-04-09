package com.qst.scnt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.CustomerInfoDao;
import com.qst.scnt.model.CustomerInfo;

@Repository("customerInfoDao")
public class CustomerInfoDaoImpl extends BaseDaoImpl<CustomerInfo> implements CustomerInfoDao {

	// mapper.xmlÖÐµÄnamespace  
    private String namespace;
    
	public CustomerInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.CustomerInfoDaoImpl");
		namespace="com.qst.scnt.dao.impl.CustomerInfoDaoImpl";		
	}

	@Override
	public List selectByCNameAndCPhone(String cName, String cPhone) {
		try {  
            return this.getSqlSession().selectList(namespace + "." + SQLID_SELECT);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}
	
}
