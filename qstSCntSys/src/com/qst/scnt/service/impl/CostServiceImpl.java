package com.qst.scnt.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.CostDao;
import com.qst.scnt.model.Cost;
import com.qst.scnt.model.EveryMonthOtherInfo;
import com.qst.scnt.service.CostService;
import com.qst.scnt.utils.EUDataGridResult;

@Service("costService")
public class CostServiceImpl extends BaseServiceImpl<Cost> implements CostService {

		@Resource
		private CostDao costDao;
		
			@Override
		public BaseDao<Cost> getBaseDao() {
			// TODO Auto-generated method stub
			return costDao;
		}
		@Override
		public EUDataGridResult<Cost> selectByItemIdAndStartAndEndDate(Map<String, Object> queryDate, int pageNum,
				int pageSize) {
			PageHelper.startPage(pageNum, pageSize);
			List<Cost> list = costDao.selectByItemIdAndStartAndEndDate(queryDate);		
			
	        //创建一个返回值对象
	        EUDataGridResult<Cost> result = new EUDataGridResult<Cost>();
	        result.setRows(list);
	        //取记录总条数
	        PageInfo<Cost> pageInfo = new PageInfo<Cost>(list);
	        result.setTotal(pageInfo.getTotal());
	        return result;
		}

}
