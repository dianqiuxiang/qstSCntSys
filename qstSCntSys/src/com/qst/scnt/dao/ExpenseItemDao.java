package com.qst.scnt.dao;

import java.util.List;

import com.qst.scnt.model.ExpenseItem;

public interface ExpenseItemDao   extends BaseDao<ExpenseItem> {
	
	public List<ExpenseItem> selectAll();

}
