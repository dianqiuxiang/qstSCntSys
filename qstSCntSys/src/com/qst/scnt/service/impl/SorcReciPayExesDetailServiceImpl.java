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
import com.qst.scnt.dao.ExpenseItemDao;
import com.qst.scnt.dao.OrderInfoDao;
import com.qst.scnt.dao.OrderProductInfoDao;
import com.qst.scnt.dao.SalesDepartmentInfoDao;
import com.qst.scnt.dao.SorcReciPayExesDetailDao;
import com.qst.scnt.model.Cost;
import com.qst.scnt.model.EmployeeInfo;
import com.qst.scnt.model.ExpenseItem;
import com.qst.scnt.model.OrderInfo;
import com.qst.scnt.model.OrderProductInfo;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.model.exesDetail;
import com.qst.scnt.service.OrderInfoService;
import com.qst.scnt.service.SorcReciPayExesDetailService;
import com.qst.scnt.utils.EUDataGridResult;
import com.qst.scnt.utils.ListSortUtil;

@Service("sorcReciPayExesDetailService")
public class SorcReciPayExesDetailServiceImpl extends BaseServiceImpl<Object> implements SorcReciPayExesDetailService {

	@Resource
	private SorcReciPayExesDetailDao sorcReciPayExesDetailDao;
	
	@Resource
	private SalesDepartmentInfoDao salesDepartmentInfoDao;
	
	@Resource
	private ExpenseItemDao expenseItemDao;
	
	@Override
	public BaseDao<Object> getBaseDao() {
		// TODO Auto-generated method stub
		return sorcReciPayExesDetailDao;
	}

	@Override
	public List countNewResour(Map<String, Object> params) {
		if(params.get("salesDepartmentID")==null){
			params.put("salesDepartmentIDList", null);
		}
		else{
			int salesDeptID=Integer.parseInt(params.get("salesDepartmentID").toString());
			SalesDepartmentInfo salesDept=new SalesDepartmentInfo();
			salesDept=salesDepartmentInfoDao.selectPK(salesDeptID);
			
			List<SalesDepartmentInfo> salesDeptList_Level3 =new ArrayList();
			if(salesDept.getLevel()==1){
				List<SalesDepartmentInfo> salesDeptList_Level2 =new ArrayList();
				salesDeptList_Level2=salesDepartmentInfoDao.selectByParentID(salesDept);
				
				for(SalesDepartmentInfo entity_Level2:salesDeptList_Level2){
					salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(entity_Level2));
				}
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
			else if(salesDept.getLevel()==2){
				salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(salesDept));
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
			else{
				salesDeptList_Level3.add(salesDept);
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
		}
		// TODO Auto-generated method stub
		return sorcReciPayExesDetailDao.countNewResour(params);
	}
	
	@Override
	public List countProductNumexcel(Map<String, Object> params) {
		if(params.get("salesDepartmentID")==null){
			params.put("salesDepartmentIDList", null);
		}
		else{
			int salesDeptID=Integer.parseInt(params.get("salesDepartmentID").toString());
			SalesDepartmentInfo salesDept=new SalesDepartmentInfo();
			salesDept=salesDepartmentInfoDao.selectPK(salesDeptID);
			
			List<SalesDepartmentInfo> salesDeptList_Level3 =new ArrayList();
			if(salesDept.getLevel()==1){
				List<SalesDepartmentInfo> salesDeptList_Level2 =new ArrayList();
				salesDeptList_Level2=salesDepartmentInfoDao.selectByParentID(salesDept);
				
				for(SalesDepartmentInfo entity_Level2:salesDeptList_Level2){
					salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(entity_Level2));
				}
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
			else if(salesDept.getLevel()==2){
				salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(salesDept));
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
			else{
				salesDeptList_Level3.add(salesDept);
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
		}
		// TODO Auto-generated method stub
		return sorcReciPayExesDetailDao.countProductNum(params);
	}
	
	/**
	 * 签约部门新资源排名
	 */
	@Override
	public EUDataGridResult<Map> countNewResourec(Map<String, Object> params) {
		if(params.get("salesDepartmentID")==null){
			params.put("salesDepartmentIDList", null);
		}
		else{
			int salesDeptID=Integer.parseInt(params.get("salesDepartmentID").toString());
			SalesDepartmentInfo salesDept=new SalesDepartmentInfo();
			salesDept=salesDepartmentInfoDao.selectPK(salesDeptID);
			
			List<SalesDepartmentInfo> salesDeptList_Level3 =new ArrayList();
			if(salesDept.getLevel()==1){
				List<SalesDepartmentInfo> salesDeptList_Level2 =new ArrayList();
				salesDeptList_Level2=salesDepartmentInfoDao.selectByParentID(salesDept);
				
				for(SalesDepartmentInfo entity_Level2:salesDeptList_Level2){
					salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(entity_Level2));
				}
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
			else if(salesDept.getLevel()==2){
				salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(salesDept));
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
			else{
				salesDeptList_Level3.add(salesDept);
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
		}
		
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
	
	/**
	 * 签约部门盒数排名
	 */
	@Override
	public EUDataGridResult<Map> countProductNum(Map<String, Object> params) {
		if(params.get("salesDepartmentID")==null){
			params.put("salesDepartmentIDList", null);
		}
		else{
			int salesDeptID=Integer.parseInt(params.get("salesDepartmentID").toString());
			SalesDepartmentInfo salesDept=new SalesDepartmentInfo();
			salesDept=salesDepartmentInfoDao.selectPK(salesDeptID);
			
			List<SalesDepartmentInfo> salesDeptList_Level3 =new ArrayList();
			if(salesDept.getLevel()==1){
				List<SalesDepartmentInfo> salesDeptList_Level2 =new ArrayList();
				salesDeptList_Level2=salesDepartmentInfoDao.selectByParentID(salesDept);
				
				for(SalesDepartmentInfo entity_Level2:salesDeptList_Level2){
					salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(entity_Level2));
				}
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
			else if(salesDept.getLevel()==2){
				salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(salesDept));
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
			else{
				salesDeptList_Level3.add(salesDept);
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
		}
		// TODO Auto-generated method stub
		int pageNum = (int)params.get("pageNum");
		int pageSize = (int)params.get("pageSize");
		PageHelper.startPage(pageNum, pageSize);
		List<Map> list = sorcReciPayExesDetailDao.countProductNum(params);
		
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
	
	@Override
	public List<exesDetail> exesDetailExcelAll(Map<String, Object> params) {
		
		if(params.get("salesDepartmentID")==null){
			params.put("salesDepartmentIDList", null);
		}
		else{
			int salesDeptID=Integer.parseInt(params.get("salesDepartmentID").toString());
			SalesDepartmentInfo salesDept=new SalesDepartmentInfo();
			salesDept=salesDepartmentInfoDao.selectPK(salesDeptID);
			
			List<SalesDepartmentInfo> salesDeptList_Level3 =new ArrayList();
			if(salesDept.getLevel()==1){
				List<SalesDepartmentInfo> salesDeptList_Level2 =new ArrayList();
				salesDeptList_Level2=salesDepartmentInfoDao.selectByParentID(salesDept);
				
				for(SalesDepartmentInfo entity_Level2:salesDeptList_Level2){
					salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(entity_Level2));
				}
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
			else if(salesDept.getLevel()==2){
				salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(salesDept));
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
			else{
				salesDeptList_Level3.add(salesDept);
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
		}
		
		if(params.get("expenseItemId")==null){
			params.put("expenseItemIdList", null);
		}
		else{
			int salesDeptID=Integer.parseInt(params.get("expenseItemId").toString());
			ExpenseItem expenseItem=new ExpenseItem();
			expenseItem=expenseItemDao.selectPK(salesDeptID);
			
			List<ExpenseItem> expenseItemList_Level3 =new ArrayList();
			if(expenseItem.getLevel()==1){
				List<ExpenseItem> expenseItemList_Level2 =new ArrayList();
				expenseItemList_Level2=expenseItemDao.selectByParentID(expenseItem);
				
				for(ExpenseItem entity_Level2:expenseItemList_Level2){
					expenseItemList_Level3.addAll(expenseItemDao.selectByParentID(entity_Level2));
				}
				if(expenseItemList_Level3==null){
					params.put("expenseItemIdList", null);
				}
				else{
					params.put("expenseItemIdList", expenseItemList_Level3);
				}
			}
			else if(expenseItem.getLevel()==2){
				expenseItemList_Level3.addAll(expenseItemDao.selectByParentID(expenseItem));
				if(expenseItemList_Level3==null){
					params.put("expenseItemIdList", null);
				}
				else{
					params.put("expenseItemIdList", expenseItemList_Level3);
				}
			}
			else{
				expenseItemList_Level3.add(expenseItem);
				if(expenseItemList_Level3==null){
					params.put("expenseItemIdList", null);
				}
				else{
					params.put("expenseItemIdList", expenseItemList_Level3);
				}
			}
		}
		
		// TODO Auto-generated method stub
		List<exesDetail> exesDetailList = sorcReciPayExesDetailDao.exesDetailExcelAll(params);
		List<exesDetail> exesDetailListTemp = new LinkedList<exesDetail>();
		for(exesDetail exesDetil:exesDetailList) {
			
			exesDetil.setPettycash("");//备用金
			exesDetailListTemp.add(exesDetil);
		}
		return exesDetailListTemp;
	}
	
	@Override
	public EUDataGridResult<exesDetail> exesDetailExcel(Map<String, Object> params) {
		// TODO Auto-generated method stub
		
		if(params.get("salesDepartmentID")==null){
			params.put("salesDepartmentIDList", null);
		}
		else{
			int salesDeptID=Integer.parseInt(params.get("salesDepartmentID").toString());
			SalesDepartmentInfo salesDept=new SalesDepartmentInfo();
			salesDept=salesDepartmentInfoDao.selectPK(salesDeptID);
			
			List<SalesDepartmentInfo> salesDeptList_Level3 =new ArrayList();
			if(salesDept.getLevel()==1){
				List<SalesDepartmentInfo> salesDeptList_Level2 =new ArrayList();
				salesDeptList_Level2=salesDepartmentInfoDao.selectByParentID(salesDept);
				
				for(SalesDepartmentInfo entity_Level2:salesDeptList_Level2){
					salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(entity_Level2));
				}
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
			else if(salesDept.getLevel()==2){
				salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(salesDept));
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
			else{
				salesDeptList_Level3.add(salesDept);
				if(salesDeptList_Level3==null){
					params.put("salesDepartmentIDList", null);
				}
				else{
					params.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
		}
		
		if(params.get("expenseItemId")==null){
			params.put("expenseItemIdList", null);
		}
		else{
			int salesDeptID=Integer.parseInt(params.get("expenseItemId").toString());
			ExpenseItem expenseItem=new ExpenseItem();
			expenseItem=expenseItemDao.selectPK(salesDeptID);
			
			List<ExpenseItem> expenseItemList_Level3 =new ArrayList();
			if(expenseItem.getLevel()==1){
				List<ExpenseItem> expenseItemList_Level2 =new ArrayList();
				expenseItemList_Level2=expenseItemDao.selectByParentID(expenseItem);
				
				for(ExpenseItem entity_Level2:expenseItemList_Level2){
					expenseItemList_Level3.addAll(expenseItemDao.selectByParentID(entity_Level2));
				}
				if(expenseItemList_Level3==null){
					params.put("expenseItemIdList", null);
				}
				else{
					params.put("expenseItemIdList", expenseItemList_Level3);
				}
			}
			else if(expenseItem.getLevel()==2){
				expenseItemList_Level3.addAll(expenseItemDao.selectByParentID(expenseItem));
				if(expenseItemList_Level3==null){
					params.put("expenseItemIdList", null);
				}
				else{
					params.put("expenseItemIdList", expenseItemList_Level3);
				}
			}
			else{
				expenseItemList_Level3.add(expenseItem);
				if(expenseItemList_Level3==null){
					params.put("expenseItemIdList", null);
				}
				else{
					params.put("expenseItemIdList", expenseItemList_Level3);
				}
			}
		}
		
				int pageNum = (int)params.get("pageNum");
				int pageSize = (int)params.get("pageSize");
				PageHelper.startPage(pageNum, pageSize);
				List<exesDetail> list = sorcReciPayExesDetailDao.exesDetailExcelAll(params);
				
		        //创建一个返回值对象
		        EUDataGridResult<exesDetail> result = new EUDataGridResult<exesDetail>();
		        result.setRows(list);
		        //取记录总条数
		        PageInfo<exesDetail> pageInfo = new PageInfo<exesDetail>(list);
		        result.setTotal(pageInfo.getTotal());
		        return result;
	}

}

