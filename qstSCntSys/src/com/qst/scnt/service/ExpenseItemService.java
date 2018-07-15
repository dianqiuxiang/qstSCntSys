package com.qst.scnt.service;

import java.util.List;

import com.qst.scnt.model.ExpenseItem;

public interface ExpenseItemService extends BaseService<ExpenseItem>  {
	public List<ExpenseItem> selectAll();
}
