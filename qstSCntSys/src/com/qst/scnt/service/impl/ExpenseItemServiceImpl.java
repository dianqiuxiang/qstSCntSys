package com.qst.scnt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.ExpenseItemDao;
import com.qst.scnt.model.ExpenseItem;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.service.ExpenseItemService;

@Service("expenseItemService")
public class ExpenseItemServiceImpl  extends  BaseServiceImpl<ExpenseItem> implements ExpenseItemService  {


	@Resource
	private ExpenseItemDao expenseItemDao;
		
	@Override
	public BaseDao<ExpenseItem> getBaseDao() {
		// TODO Auto-generated method stub
		return expenseItemDao;
	}

	@Override
	public List<ExpenseItem> selectAll() {
		// TODO Auto-generated method stub
		return expenseItemDao.selectAll();
	}

	@Override
	 @Transactional
	public int deleteItselfAndItsChildren(ExpenseItem expenseItem) {
		//List<SalesDepartmentInfo> list=salesDepartmentInfoDao.selectByParentID(salesDepartmentInfo);
		List<ExpenseItem> list=new ArrayList();
		list.add(expenseItem);
		getChildrenList(list);
//				Gson gson = new Gson();
//				System.out.println("aa: "+gson.toJson(childrenList));
//				System.out.println("aa: "+childrenList.size());
		int aa=0;
		int ab=0;
		
		expenseItem.setIsDelete(1);//"1"代表删除，"0"代表未删除
		aa=expenseItemDao.update(expenseItem);
		
		if(childrenList.size()>0){
			for(ExpenseItem item : childrenList){
				item.setIsDelete(1);//"1"代表删除，"0"代表未删除
				ab+=expenseItemDao.update(item);
			}
			if (aa == 1 && ab == childrenList.size()) { 
				childrenList.clear();
	            return 1;  
	        }  
			else
			{
				childrenList.clear();
				return 0;
			}
		}
		else
		{
			if (aa == 1) {  
	            return 1;  
	        } 
			else
			{
				 return 0;
			}
		}
	}
	List<ExpenseItem> childrenList= new ArrayList();;
	private void getChildrenList(List<ExpenseItem> list){
		for(ExpenseItem item:list)
		{
			List<ExpenseItem> c_list=expenseItemDao.selectByParentID(item);
	//			System.out.println("listSize: "+c_list.size());
			if(c_list.size()>0)
			{
				childrenList.addAll(c_list);
				getChildrenList(c_list);
			}
			
		}
		 
	 }
}
