package com.qst.scnt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.ExpenseItemDao;
import com.qst.scnt.model.ExpenseItem;
import com.qst.scnt.model.SalesDepartmentInfo;

@Repository("expenseItemDao")
public class ExpenseItemDaoImpl extends BaseDaoImpl<ExpenseItem> implements ExpenseItemDao {

	// mapper.xmlÖÐµÄnamespace  
    private String namespace;
    
	public ExpenseItemDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.ExpenseItemDaoImpl");
		namespace="com.qst.scnt.dao.impl.ExpenseItemDaoImpl";
	}
	
	@Override
	public List<ExpenseItem> selectAll() {
		try {  
            return this.getSqlSession().selectList(namespace + "." + "selectAll");  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}

	@Override
	public List<ExpenseItem> selectByParentID(ExpenseItem entity) {
		try {  
            return this.getSqlSession().selectList(namespace + "." + "selectByParentID",entity);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}
}
