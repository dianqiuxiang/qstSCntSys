package com.qst.scnt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.ReceiptInfoDao;
import com.qst.scnt.model.ReceiptInfo;

@Repository("peceiptInfoDao")
public class ReceiptInfoDaoImpl extends BaseDaoImpl<ReceiptInfo> implements ReceiptInfoDao {

	// mapper.xmlÖÐµÄnamespace  
    private String namespace;
    
	public ReceiptInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.ReceiptInfoDaoImpl");		
		namespace="com.qst.scnt.dao.impl.ReceiptInfoDaoImpl";
	}

	@Override
	public List<ReceiptInfo> selectByCodeAndRMemberAndDate(Map<String, Object> queryDate) {
		try {  
            return this.getSqlSession().selectList(namespace + "." + "selectByCodeAndRMemberAndDate",queryDate);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}

	@Override
	public ReceiptInfo selectByID(int ID) {
		try {  
            return this.getSqlSession().selectOne(namespace + "." + "selectByID",ID);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}
	
}

