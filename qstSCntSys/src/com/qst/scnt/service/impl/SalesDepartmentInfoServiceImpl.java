package com.qst.scnt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.SalesDepartmentInfoDao;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.service.SalesDepartmentInfoService;

@Service("salesDepartmentInfoService")
public class SalesDepartmentInfoServiceImpl extends BaseServiceImpl<SalesDepartmentInfo> implements SalesDepartmentInfoService {

	@Resource    
    private SalesDepartmentInfoDao salesDepartmentInfoDao;
    
	@Override
	public BaseDao<SalesDepartmentInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return salesDepartmentInfoDao;
	}

	@Override
	public List<SalesDepartmentInfo> selectAll() {
		// TODO Auto-generated method stub
		return salesDepartmentInfoDao.selectAll();
	}

	@Override
    @Transactional
	public int deleteItselfAndItsChildren(SalesDepartmentInfo salesDepartmentInfo) {
		//List<SalesDepartmentInfo> list=salesDepartmentInfoDao.selectByParentID(salesDepartmentInfo);
		List<SalesDepartmentInfo> list=new ArrayList();
		list.add(salesDepartmentInfo);
		getChildrenList(list);
//		Gson gson = new Gson();
//		System.out.println("aa: "+gson.toJson(childrenList));
//		System.out.println("aa: "+childrenList.size());
		int aa=0;
		int ab=0;
		
		salesDepartmentInfo.setIsDelete(1);//"1"代表删除，"0"代表未删除
		aa=salesDepartmentInfoDao.update(salesDepartmentInfo);
		
		if(childrenList.size()>0){
			for(SalesDepartmentInfo item : childrenList){
				item.setIsDelete(1);//"1"代表删除，"0"代表未删除
				ab+=salesDepartmentInfoDao.update(item);
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
	
	List<SalesDepartmentInfo> childrenList= new ArrayList();;
	 private void getChildrenList(List<SalesDepartmentInfo> list){
	
		for(SalesDepartmentInfo item:list)
		{
			List<SalesDepartmentInfo> c_list=salesDepartmentInfoDao.selectByParentID(item);
//			System.out.println("listSize: "+c_list.size());
			if(c_list.size()>0)
			{
				childrenList.addAll(c_list);
				getChildrenList(c_list);
			}
			
		}
		 
	 }
	
	
	
}

