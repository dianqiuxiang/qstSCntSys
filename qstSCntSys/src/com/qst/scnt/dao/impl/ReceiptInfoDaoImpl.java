package com.qst.scnt.dao.impl;

import java.util.HashMap;
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

	@Override
	public HashMap selectBySDeptIdAndYM(Map<String, Object> params) {
		try {  
            return this.getSqlSession().selectOne(namespace + "." + "selectBySDeptIdAndYM",params);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}

	@Override
	public HashMap selectSalesVolume(Map<String, Object> params) {
		try {  
            return this.getSqlSession().selectOne(namespace + "." + "selectSalesVolume",params);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}

	@Override
	public HashMap selectNewCustomerSalesVolume(Map<String, Object> params) {
		try {  
            return this.getSqlSession().selectOne(namespace + "." + "selectNewCustomerSalesVolume",params);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}

	@Override
	public HashMap selectBySDeptIdAndY(Map<String, Object> params) {
		try {  
            return this.getSqlSession().selectOne(namespace + "." + "selectBySDeptIdAndY",params);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}

	@Override
	public HashMap selectSalesVolume1(Map<String, Object> params) {
		try {  
            return this.getSqlSession().selectOne(namespace + "." + "selectSalesVolume1",params);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}

	@Override
	public HashMap selectNewCustomerSalesVolume1(Map<String, Object> params) {
		// TODO Auto-generated method stub
		try {  
            return this.getSqlSession().selectOne(namespace + "." + "selectNewCustomerSalesVolume1",params);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}

	@Override
	public List<ReceiptInfo> selectByOrderID(Map<String, Object> orParams) {
		// TODO Auto-generated method stub
		try {  
            return this.getSqlSession().selectList(namespace + "." + "selectByOrderID",orParams);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}
	
}

