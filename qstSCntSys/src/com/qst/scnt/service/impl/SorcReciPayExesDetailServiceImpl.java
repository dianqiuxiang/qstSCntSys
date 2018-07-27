package com.qst.scnt.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.OrderInfoDao;
import com.qst.scnt.dao.OrderProductInfoDao;
import com.qst.scnt.dao.SalesDepartmentInfoDao;
import com.qst.scnt.dao.SorcReciPayExesDetailDao;
import com.qst.scnt.model.Cost;
import com.qst.scnt.model.EmployeeInfo;
import com.qst.scnt.model.OrderInfo;
import com.qst.scnt.model.OrderProductInfo;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.service.OrderInfoService;
import com.qst.scnt.service.SorcReciPayExesDetailService;
import com.qst.scnt.utils.EUDataGridResult;
import com.qst.scnt.utils.ListSortUtil;

@Service("sorcReciPayExesDetailService")
public class SorcReciPayExesDetailServiceImpl extends BaseServiceImpl<Object> implements SorcReciPayExesDetailService {

	@Resource
	private SorcReciPayExesDetailDao sorcReciPayExesDetailDao;
	
	@Override
	public BaseDao<Object> getBaseDao() {
		// TODO Auto-generated method stub
		return sorcReciPayExesDetailDao;
	}

	@Override
	public List countNewResour(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return sorcReciPayExesDetailDao.countNewResour(params);
	}
	
	/**
	 * 签约部门回款盒数排名
	 */
	@Override
	public EUDataGridResult<Map> countNewResourec(Map<String, Object> params) {
		// TODO Auto-generated method stub
		int pageNum = (int)params.get("pageNum");
		int pageSize = (int)params.get("pageSize");
		PageHelper.startPage(pageNum, pageSize);
		List<Map> list = sorcReciPayExesDetailDao.countNewResour(params);
		
        //创建一个返回值对象
        EUDataGridResult<Map> result = new EUDataGridResult<Map>();
        result.setRows(list);
        //取记录总条数
        PageInfo<Map> pageInfo = new PageInfo<Map>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
	}
	
//	PageHelper.startPage(pageNum, pageSize);
//	List<EmployeeInfo> list = employeeInfoDao.selectByNameAndSex(queryParam);
//	
//    //创建一个返回值对象
//    EUDataGridResult<EmployeeInfo> result = new EUDataGridResult<EmployeeInfo>();

}

