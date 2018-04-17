package com.qst.scnt.dao.impl;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.ExpenseItemDao;
import com.qst.scnt.model.ExpenseItem;

@Repository("expenseItemDao")
public class ExpenseItemDaoImpl extends BaseDaoImpl<ExpenseItem> implements ExpenseItemDao {

	// mapper.xmlÖÐµÄnamespace  
    private String namespace;
    
	public ExpenseItemDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.ExpenseItemDaoImpl");
		namespace="com.qst.scnt.dao.impl.ExpenseItemDaoImpl";
	}
}
