package com.qst.scnt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.SalesDepartmentInfoDao;
import com.qst.scnt.model.SalesDepartmentInfo;

@Repository("salesDepartmentInfoDao")
public class SalesDepartmentInfoDaoImpl extends BaseDaoImpl<SalesDepartmentInfo> implements SalesDepartmentInfoDao {

	// mapper.xmlÖÐµÄnamespace  
    private String namespace;
    
	public SalesDepartmentInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.SalesDepartmentInfoDaoImpl");	
		namespace="com.qst.scnt.dao.impl.SalesDepartmentInfoDaoImpl";
	}

	@Override
	public List<SalesDepartmentInfo> selectAll() {
		try {  
            return this.getSqlSession().selectList(namespace + "." + "selectAll");  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}

	@Override
	public List<SalesDepartmentInfo> selectByParentID(SalesDepartmentInfo entity) {
		try {  
            return this.getSqlSession().selectList(namespace + "." + "selectByParentID",entity);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}

}

