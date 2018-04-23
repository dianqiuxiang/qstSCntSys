package com.qst.scnt.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.EveryMonthOtherInfoDao;
import com.qst.scnt.model.EveryMonthOtherInfo;
import com.qst.scnt.service.EveryMonthOtherInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Service("everyMonthOtherInfoService")
public class EveryMonthOtherInfoServiceImpl  extends  BaseServiceImpl<EveryMonthOtherInfo> implements EveryMonthOtherInfoService {

	@Resource
	private EveryMonthOtherInfoDao everyMonthOtherInfoDao;
		
	@Override
	public BaseDao<EveryMonthOtherInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return everyMonthOtherInfoDao;
	}
	@Override
	public EUDataGridResult<EveryMonthOtherInfo> selectByStartAndEndDate(Map<String, Object> queryDate,int pageNum,int pageSize)
	{
		PageHelper.startPage(pageNum, pageSize);
		List<EveryMonthOtherInfo> list = everyMonthOtherInfoDao.selectByStartAndEndDate(queryDate);		
		
        //创建一个返回值对象
        EUDataGridResult<EveryMonthOtherInfo> result = new EUDataGridResult<EveryMonthOtherInfo>();
        result.setRows(list);
        //取记录总条数
        PageInfo<EveryMonthOtherInfo> pageInfo = new PageInfo<EveryMonthOtherInfo>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
		
	}
	
	@Override
	public List<EveryMonthOtherInfo> selectByDate(Map<String, Object> infoDate)
	{
		return everyMonthOtherInfoDao.selectByDate(infoDate);
	}
	
}

