package com.qst.scnt.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qst.scnt.model.Cost;
import com.qst.scnt.model.UserInfo;
import com.qst.scnt.utils.EUDataGridResult;

public interface CostService extends BaseService<Cost> {

	public EUDataGridResult<Cost> selectByItemIdAndStartAndEndDate(Map<String, Object> queryDate, int page, int rows);

	public HashMap selectBySDeptIdAndYM(Map<String, Object> params);

	public HashMap selectBySDeptIdAndY(Map<String, Object> params);
}
