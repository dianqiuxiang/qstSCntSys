package com.qst.scnt.service;

import java.util.Date;
import java.util.List;

import com.qst.scnt.model.Cost;
import com.qst.scnt.model.UserInfo;

public interface CostService extends BaseService<Cost> {
	public List selectByEItemIDAndEDate(int expenseItemID,Date expenseDate);
}
