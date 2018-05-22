package com.qst.scnt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.ReceiptInfoDao;
import com.qst.scnt.dao.SalesDepartmentInfoDao;
import com.qst.scnt.model.OrderInfo;
import com.qst.scnt.model.ReceiptInfo;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.service.ReceiptInfoService;
import com.qst.scnt.utils.EUDataGridResult;
import com.qst.scnt.utils.ListSortUtil;

@Service("receiptInfoService")
public class ReceiptInfoServiceImpl extends BaseServiceImpl<ReceiptInfo> implements ReceiptInfoService {

	@Resource
	private ReceiptInfoDao receiptInfoDao;

	@Resource
	private SalesDepartmentInfoDao salesDepartmentInfoDao;
	
	@Override
	public BaseDao<ReceiptInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return receiptInfoDao;
	}

	@Override
	public EUDataGridResult<ReceiptInfo> selectByCodeAndRMemberAndDate(Map<String, Object> queryDate, int pageNum, int pageSize) {

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
		List<ReceiptInfo> list =receiptInfoDao.selectByCodeAndRMemberAndDate(queryDate);
		
        //创建一个返回值对象
        EUDataGridResult<ReceiptInfo>  result = new EUDataGridResult<ReceiptInfo> ();
        result.setRows(list);
        //取记录总条数
        PageInfo<ReceiptInfo> pageInfo = new PageInfo<ReceiptInfo>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
	}
	//返回所有数据（页面带参数的查询到的）
	public List<ReceiptInfo> selectByCodeAndRMemberAndDate(Map<String, Object> queryDate){
		
		List<ReceiptInfo> list =new ArrayList<ReceiptInfo>();
		if(queryDate.get("salesDepartmentID")==null){
			list = receiptInfoDao.selectByCodeAndRMemberAndDate(queryDate);
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
				
				for(SalesDepartmentInfo entity_Level3:salesDeptList_Level3){
					queryDate.put("salesDepartmentID",entity_Level3.getId());
					list.addAll(receiptInfoDao.selectByCodeAndRMemberAndDate(queryDate));
				}
			}
			else if(salesDept.getLevel()==2){
				salesDeptList_Level3.addAll(salesDepartmentInfoDao.selectByParentID(salesDept));
				for(SalesDepartmentInfo entity_Level3:salesDeptList_Level3){
					queryDate.put("salesDepartmentID",entity_Level3.getId());
					list.addAll(receiptInfoDao.selectByCodeAndRMemberAndDate(queryDate));
				}
			}
			else{
				list = receiptInfoDao.selectByCodeAndRMemberAndDate(queryDate);
			}
		}

		//对链表进行降序排列
		ListSortUtil<ReceiptInfo> sortUtil = new ListSortUtil(ReceiptInfo.class);  
        try {  
            sortUtil.addDesc("receiptDate");
        } catch (NoSuchMethodException e) {  
            e.printStackTrace();  
        }
	    sortUtil.sortList(list);
		
		return list;
	}

	@Override
	public ReceiptInfo selectByID(int ID) {
		// TODO Auto-generated method stub
		return receiptInfoDao.selectByID(ID);
	}
	

}

