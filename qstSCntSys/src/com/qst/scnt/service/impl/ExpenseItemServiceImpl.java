package com.qst.scnt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.ExpenseItemDao;
import com.qst.scnt.model.ExpenseItem;
import com.qst.scnt.service.ExpenseItemService;

@Service("expenseItemService")
public class ExpenseItemServiceImpl  extends  BaseServiceImpl<ExpenseItem> implements ExpenseItemService  {


	@Resource
	private ExpenseItemDao expenseItemDao;
		
	@Override
	public BaseDao<ExpenseItem> getBaseDao() {
		// TODO Auto-generated method stub
		return expenseItemDao;
	}
	
}
