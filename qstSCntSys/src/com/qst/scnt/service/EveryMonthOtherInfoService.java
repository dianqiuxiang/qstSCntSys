package com.qst.scnt.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qst.scnt.model.EveryMonthOtherInfo;
import com.qst.scnt.utils.EUDataGridResult;

public interface EveryMonthOtherInfoService extends BaseService<EveryMonthOtherInfo> {
	
	public EUDataGridResult<EveryMonthOtherInfo> selectByStartAndEndDate(Map<String, Object> queryDate,int page,int rows);
	public List<EveryMonthOtherInfo> selectByDate(Map<String, Object> infoDate);
	public HashMap selectBySDeptIdAndYM(Map<String, Object> params);
	public HashMap selectBySDeptIdAndY(Map<String, Object> params);
}

