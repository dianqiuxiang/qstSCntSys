package com.qst.scnt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.CustomerInfoDao;
import com.qst.scnt.dao.SalesDepartmentInfoDao;
import com.qst.scnt.model.CustomerInfo;
import com.qst.scnt.model.CustomerInfo;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.service.CustomerInfoService;
import com.qst.scnt.utils.EUDataGridResult;
import com.qst.scnt.utils.ListSortUtil;

@Service("customerInfoService")
public class CustomerInfoServiceImpl extends BaseServiceImpl<CustomerInfo> implements CustomerInfoService {

	@Resource
	private CustomerInfoDao customerInfoDao;

	@Resource
	private SalesDepartmentInfoDao salesDepartmentInfoDao;
	
	@Override
	public BaseDao<CustomerInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return customerInfoDao;
	}

	@Override
	public List selectByCNameAndCPhone(String cName, String cPhone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EUDataGridResult<CustomerInfo> selectByCNameAndCPhone(Map<String, Object> queryDate, int pageNum,int pageSize) {
	
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
				queryDate.put("salesDepartmentIDList", salesDeptList_Level3);
			}
			else if(salesDept.getLevel()==2){
				salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(salesDept));
				queryDate.put("salesDepartmentIDList", salesDeptList_Level3);
			}
			else{
				salesDeptList_Level3.add(salesDept);
				queryDate.put("salesDepartmentIDList", salesDeptList_Level3);
			}
		}

		PageHelper.startPage(pageNum, pageSize);
		List<CustomerInfo> list =customerInfoDao.selectByCNameAndCPhone(queryDate);
		
		
        //创建一个返回值对象
        EUDataGridResult<CustomerInfo> result = new EUDataGridResult<CustomerInfo>();
        result.setRows(list);
        //取记录总条数
        PageInfo<CustomerInfo> pageInfo = new PageInfo<CustomerInfo>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
	}

}
