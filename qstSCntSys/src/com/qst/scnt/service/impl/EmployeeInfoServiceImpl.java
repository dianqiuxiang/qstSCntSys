package com.qst.scnt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.EmployeeInfoDao;
import com.qst.scnt.dao.SalesDepartmentInfoDao;
import com.qst.scnt.model.EmployeeInfo;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.service.EmployeeInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Service("employeeInfoService")
public class EmployeeInfoServiceImpl extends  BaseServiceImpl<EmployeeInfo> implements EmployeeInfoService {

	@Resource
	private EmployeeInfoDao employeeInfoDao;
	
	@Resource
	private SalesDepartmentInfoDao salesDepartmentInfoDao;
	
	@Override
	public BaseDao<EmployeeInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return employeeInfoDao;
	}
	
	@Override
	public EUDataGridResult<EmployeeInfo> selectByNameAndSex(Map<String, Object> queryParam, int pageNum, int pageSize) {
		if(queryParam.get("salesDepartmentID")==null){
			queryParam.put("salesDepartmentIDList", null);
		}
		else{
			int salesDeptID=(int)queryParam.get("salesDepartmentID");
			SalesDepartmentInfo salesDept=new SalesDepartmentInfo();
			salesDept=salesDepartmentInfoDao.selectPK(salesDeptID);
			
			List<SalesDepartmentInfo> salesDeptList_Level3 =new ArrayList();
			if(salesDept.getLevel()==1){
				salesDeptList_Level3.add(salesDept);
				
				List<SalesDepartmentInfo> salesDeptList_Level2 =new ArrayList();
				salesDeptList_Level2=salesDepartmentInfoDao.selectByParentID(salesDept);
				salesDeptList_Level3.addAll(salesDeptList_Level2);
				
				for(SalesDepartmentInfo entity_Level2:salesDeptList_Level2){
					salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(entity_Level2));
				}
				if(salesDeptList_Level3==null){
					queryParam.put("salesDepartmentIDList", null);
				}
				else{
					queryParam.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
			else if(salesDept.getLevel()==2){
				salesDeptList_Level3.add(salesDept);
				salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(salesDept));
				if(salesDeptList_Level3==null){
					queryParam.put("salesDepartmentIDList", null);
				}
				else{
					queryParam.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
			else{
				salesDeptList_Level3.add(salesDept);
				if(salesDeptList_Level3==null){
					queryParam.put("salesDepartmentIDList", null);
				}
				else{
					queryParam.put("salesDepartmentIDList", salesDeptList_Level3);
				}
			}
		}

		PageHelper.startPage(pageNum, pageSize);
		List<EmployeeInfo> list = employeeInfoDao.selectByNameAndSex(queryParam);
		
        //创建一个返回值对象
        EUDataGridResult<EmployeeInfo> result = new EUDataGridResult<EmployeeInfo>();
        result.setRows(list);
        //取记录总条数
        PageInfo<EmployeeInfo> pageInfo = new PageInfo<EmployeeInfo>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
	}
	
}

