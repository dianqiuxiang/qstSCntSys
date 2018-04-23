package com.qst.scnt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.CostDao;
import com.qst.scnt.model.Cost;

@Repository("costDao")
public class CostDaoImpl extends BaseDaoImpl<Cost> implements CostDao {
	
	// mapper.xmlÖÐµÄnamespace  
    private String namespace;
    
	public CostDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.CostDaoImpl");
		namespace="com.qst.scnt.dao.impl.CostDaoImpl";
	}

	@Override
	public List<Cost> selectByItemIdAndStartAndEndDate(Map<String, Object> queryDate) {
		try {  
            return this.getSqlSession().selectList(namespace + "." + "selectByItemIdAndStartAndEndDate",queryDate);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}
	
}
