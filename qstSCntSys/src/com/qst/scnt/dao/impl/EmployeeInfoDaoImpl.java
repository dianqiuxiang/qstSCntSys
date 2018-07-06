package com.qst.scnt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.EmployeeInfoDao;
import com.qst.scnt.model.EmployeeInfo;

@Repository("employeeInfoDao")
public class EmployeeInfoDaoImpl extends BaseDaoImpl<EmployeeInfo> implements EmployeeInfoDao {
	
	// mapper.xmlÖÐµÄnamespace  
    private String namespace;
    
	public EmployeeInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.EmployeeInfoDaoImpl");	
		namespace="com.qst.scnt.dao.impl.EmployeeInfoDaoImpl";
	}

	@Override
	public List<EmployeeInfo> selectByNameAndSex(Map<String, Object> queryParam) {
		try {  
            return this.getSqlSession().selectList(namespace + "." + "selectByNameAndSex",queryParam);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}
	
}

