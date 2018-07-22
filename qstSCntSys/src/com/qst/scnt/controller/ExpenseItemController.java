package com.qst.scnt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qst.scnt.model.ExpenseItem;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.service.ExpenseItemService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/expenseItem")
public class ExpenseItemController extends BaseController{
	
	@Resource
	private ExpenseItemService expenseItemService;
	
	/**
	 * ��ѯ�������۲�����Ϣ
	 * @return
	 */
	@RequestMapping(value="/getInfo.do")
	@ResponseBody
	public Map<String,Object> getInfo() {

		Gson gson = new Gson();

		List<ExpenseItem> list=expenseItemService.selectAll();
		Map<String,Object> map=new HashMap<String,Object>();
		for(ExpenseItem item : list){
			if(item.getLevel()==1)
			{
				item.set_parentId(null);
			}
		}
		map.put("rows", list);
		//System.out.println(gson.toJson(list));
		return map;
	}
	
	/**
	 * ��ѯ�������۲�����Ϣ���󶨵������б�
	 * @return
	 */
	@RequestMapping(value="/selectExpenseItem.do")
	@ResponseBody
	public Object selectSalesDepartment() {

//		Gson gson = new Gson();
//
//		List<SalesDepartmentInfo> list=salesDepartmentInfoService.select();
//		//System.out.println(gson.toJson(list));
//		return gson.toJson(list);
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("parentID",0);//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
		
		List<ExpenseItem> list=expenseItemService.selectParam(params);
		
		String returnJson="[";
		int i=0;
		for(ExpenseItem item:list){
			i++;
			returnJson+="{";
			returnJson+="\"id\":"+ item.getId() +",";
			returnJson+="\"text\":\""+ item.getExpenseItem() +"\",";
			returnJson+="\"children\":[";
			Map<String, Object> fieldMap = new HashMap<String, Object>();
			fieldMap.put("parentID",item.getId());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
			
			Map<String, Object> queryParams = new HashMap<String, Object>();  
			queryParams.put("where", fieldMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
			
			List<ExpenseItem> childrenList=expenseItemService.selectParam(queryParams);
			
			int j=0;
			for(ExpenseItem childNode:childrenList){
				j++;
				returnJson+="{";
				returnJson+="\"id\":"+ childNode.getId() +",";
				//returnJson+="\"text\":\""+ childNode.getSalesDepartmentName()+"\"";
				returnJson+="\"text\":\""+ childNode.getExpenseItem() +"\",";
				returnJson+="\"children\":[";
				
				Map<String, Object> fieldMap2 = new HashMap<String, Object>();
				fieldMap2.put("parentID",childNode.getId());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
				
				Map<String, Object> queryParams2 = new HashMap<String, Object>();  
				queryParams2.put("where", fieldMap2); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
				
				List<ExpenseItem> childrenList2=expenseItemService.selectParam(queryParams2);
				
				int k=0;
				for(ExpenseItem childNode2:childrenList2){
					k++;
					returnJson+="{";
					returnJson+="\"id\":"+ childNode2.getId() +",";
					returnJson+="\"text\":\""+ childNode2.getExpenseItem() +"\"";
					
					
					if(k==childrenList2.size()){
						returnJson+="}";
					}
					else{
						returnJson+="},";
					}
				}
				returnJson+="]";
				
				if(j==childrenList.size()){
					returnJson+="}";
				}
				else{
					returnJson+="},";
				}
			}
			returnJson+="]";
			
			if(i==list.size()){
				returnJson+="}";
			}
			else{
				returnJson+="},";
			}
		}
		returnJson+="]";
		//System.out.println(returnJson);
		return returnJson;
	}
	
	
	
	
	/**
	 * ����Id��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/findByID.do")
	@ResponseBody
	public Object selectByID(int id) {
		
		Gson gson = new Gson();		
		ExpenseItem expenseItem=expenseItemService.selectPK(id);
		//System.out.println(gson.toJson(salesDepartmentInfo));
		return gson.toJson(expenseItem);
	}
	/**
	 * ɾ��
	 * 
	 */
	@RequestMapping(value="/deleteExpenseItem.do")
	@ResponseBody
	public Object deleteExpenseItem(int id) {

		String resultStr="";
		ExpenseItem expenseItem=new ExpenseItem();
		expenseItem.setId(id);		
		//expenseItem.setIsDelete(1);//"1"����ɾ����"0"����δɾ��
		int result=expenseItemService.deleteItselfAndItsChildren(expenseItem);
		if(result>0)
		{
			resultStr="{\"result\":\"Success\"}";
		}
		else
		{
			resultStr="{\"result\":\"Failed\"}";
		}
		
		return resultStr;
	}
	
	/**
	 * ����--���޸�
	 */
	@RequestMapping(value="/addExpenseItemInfo.do")
	@ResponseBody
	public Object addExpenseItemInfo(@RequestBody  ExpenseItem expenseItem) {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();	
		//���޸�
		whereMap.put("expenseItem",expenseItem.getExpenseItem());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µķ�����Ŀ��Ϣ	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
		List<ExpenseItem> list=expenseItemService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0){
			
			if(expenseItem.getParentID()==0){
				expenseItem.setLevel(1);
			}
			else{
				ExpenseItem parent_expenseItem=expenseItemService.selectPK(expenseItem.getParentID());
				expenseItem.setLevel(parent_expenseItem.getLevel()+1);
			}
			expenseItem.setIsDelete(0);
			
			int result=expenseItemService.insert(expenseItem);
			if(result>0)
			{
				resultStr="{\"result\":\"Success\"}";
			}
			else
			{
				resultStr="{\"result\":\"Failed\"}";
			}
		}
		else
		{
			resultStr="{\"result\":\"isExist\"}";
		}
		return resultStr;
	}
	
	/**
	 * �޸�
	 */
	@RequestMapping(value="/updateExpenseItemInfo.do")
	@ResponseBody
	public Object updateSalesDepartmentInfo(@RequestBody  ExpenseItem expenseItem) {
		
		ExpenseItem old_expenseItem=expenseItemService.selectPK(expenseItem.getId());
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("expenseItem",expenseItem.getExpenseItem());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
		List<ExpenseItem> list=expenseItemService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0||old_expenseItem.getExpenseItem().equals(expenseItem.getExpenseItem())){
			
//			ExpenseItem parent_expenseItem=expenseItemService.selectPK(expenseItem.getParentID());
//			expenseItem.setLevel(parent_expenseItem.getLevel()+1);
			
			int result=expenseItemService.update(expenseItem);
			if(result>0)
			{
				resultStr="{\"result\":\"Success\"}";
			}
			else
			{
				resultStr="{\"result\":\"Failed\"}";
			}
		}
		else
		{
			resultStr="{\"result\":\"isExist\"}";
		}
		return resultStr;
	}
	
//	/**
//	 * ��ѯ���з�����Ŀ��Ϣ���󶨵������б�
//	 * @return
//	 */
//	@RequestMapping(value="/selectExpenseItem.do")
//	@ResponseBody
//	public Object selectExpenseItem() {
//
//		Gson gson = new Gson();
//
//		List<ExpenseItem> list=expenseItemService.select();
//		//System.out.println(gson.toJson(list));
//		return gson.toJson(list);
//	}

}
