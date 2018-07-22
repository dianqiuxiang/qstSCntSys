package com.qst.scnt.dao;

import java.util.List;

import com.qst.scnt.model.ExpenseItem;
import com.qst.scnt.model.SalesDepartmentInfo;

public interface ExpenseItemDao   extends BaseDao<ExpenseItem> {
	
	public List<ExpenseItem> selectAll();

	public List<ExpenseItem> selectByParentID(ExpenseItem entity);

}
