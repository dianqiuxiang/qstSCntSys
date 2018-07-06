package com.qst.scnt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.CostDao;
import com.qst.scnt.dao.SalesDepartmentInfoDao;
import com.qst.scnt.model.Cost;
import com.qst.scnt.model.EveryMonthOtherInfo;
import com.qst.scnt.model.ReceiptInfo;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.service.CostService;
import com.qst.scnt.utils.EUDataGridResult;
import com.qst.scnt.utils.ListSortUtil;

@Service("costService")
public class CostServiceImpl extends BaseServiceImpl<Cost> implements CostService {

		@Resource
		private CostDao costDao;
		
		@Resource
		private SalesDepartmentInfoDao salesDepartmentInfoDao;
		
			@Override
		public BaseDao<Cost> getBaseDao() {
			// TODO Auto-generated method stub
			return costDao;
		}
		@Override
		public EUDataGridResult<Cost> selectByItemIdAndStartAndEndDate(Map<String, Object> queryDate, int pageNum,
				int pageSize) {
			
			if(queryDate.get("salesDepartmentID")==null){
				queryDate.put("salesDepartmentIDList", null);
			}
			else{
				int salesDeptID=(int)queryDate.get("salesDepartmentID");
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
						queryDate.put("salesDepartmentIDList", null);
					}
					else{
						queryDate.put("salesDepartmentIDList", salesDeptList_Level3);
					}
				}
				else if(salesDept.getLevel()==2){
					salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(salesDept));
					if(salesDeptList_Level3==null){
						queryDate.put("salesDepartmentIDList", null);
					}
					else{
						queryDate.put("salesDepartmentIDList", salesDeptList_Level3);
					}
				}
				else{
					salesDeptList_Level3.add(salesDept);
					if(salesDeptList_Level3==null){
						queryDate.put("salesDepartmentIDList", null);
					}
					else{
						queryDate.put("salesDepartmentIDList", salesDeptList_Level3);
					}
				}
			}
			
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
		@Override
		public HashMap selectBySDeptIdAndYM(Map<String, Object> params) {
			// TODO Auto-generated method stub
			return costDao.selectBySDeptIdAndYM(params);
		}
		@Override
		public HashMap selectBySDeptIdAndY(Map<String, Object> params) {
			// TODO Auto-generated method stub
			return costDao.selectBySDeptIdAndY(params);
		}

}
