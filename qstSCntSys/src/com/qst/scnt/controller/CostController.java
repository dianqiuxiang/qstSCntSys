package com.qst.scnt.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qst.scnt.model.Cost;
import com.qst.scnt.model.ExpenseItem;
import com.qst.scnt.service.CostService;
import com.qst.scnt.service.ExpenseItemService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/cost")
public class CostController extends BaseController {
	
	@Resource
	private CostService costService;
	
	@Resource
	private ExpenseItemService expenseItemService;
	
	@RequestMapping(value="/getExpenseItem.do")
	@ResponseBody
	public Object getExpenseItem() {		
		Gson gson = new Gson();

		List<ExpenseItem> list=expenseItemService.select();
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
		
	@RequestMapping(value="/getInfo.do")
	@ResponseBody
	public Object getInfo() {		
		Gson gson = new Gson();

		List<Cost> list=costService.select();
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/selectByEItemIDAndEDate.do")
	@ResponseBody
	public Object selectByEItemIDAndEDate(int expenseItemID,String expenseDate,int page,int rows) {
		
		Gson gson = new Gson();		
		Cost cost=new Cost();
		cost.setExpenseItemID(expenseItemID);
		cost.setExpenseDate(expenseDate);
		cost.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
		
		EUDataGridResult<Cost> list=costService.selectParamFlexible(cost,page,rows);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/addCostInfo.do")
	@ResponseBody
	public Object addCostInfo(int expenseItemID,long expenseAmount,String expenseDate) {	
//		/**********���ÿ����ظ���ZL�޸ģ�**********/
//		Map<String, Object> whereMap = new HashMap<String, Object>();
//		whereMap.put("expenseItemID",expenseItemID);//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
//		
//		Map<String, Object> params = new HashMap<String, Object>();  
//		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
//		List<Cost> list=costService.selectParam(params);
		String resultStr="";	
//		if(list.size()==0) {				
			Cost cost=new Cost();
			cost.setExpenseItemID(expenseItemID);
			cost.setExpenseAmount(expenseAmount);
			cost.setExpenseDate(expenseDate);
			cost.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
			cost.setIsDelete(0);
			int result=costService.insert(cost);
			if(result>0)
			{
				resultStr="[{\"result\":\"Success\"}]";
			}
			else
			{
				resultStr="[{\"result\":\"Failed\"}]";
			}
//		}else 
//		{
//			resultStr="[{\"result\":\"isExist\"}]";
//		}
		return resultStr;
	}
	
	@RequestMapping(value="/selectByID.do")
	@ResponseBody
	public Object selectByID(int ID) {

		Gson gson = new Gson();
		Cost cost=costService.selectPK(ID);
		return gson.toJson(cost);
	}
	
	@RequestMapping(value="/updateCostInfo.do")
	@ResponseBody
	public Object updateCostInfo(int ID,int expenseItemID,long expenseAmount,String expenseDate) {
//		/**********���ÿ����ظ���ZL�޸ģ�**********/
//		Cost old_costInfo=costService.selectPK(ID);
//		
//		Map<String, Object> whereMap = new HashMap<String, Object>();
//		whereMap.put("expenseItemID",expenseItemID);//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
//		
//		Map<String, Object> params = new HashMap<String, Object>();  
//		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
//		List<Cost> list=costService.selectParam(params);
		
		String resultStr="";
//		if(list.size()==0||old_costInfo.getExpenseItemID().equals(expenseItemID)){
			Cost costinfo=new Cost();
			costinfo.setId(ID);
			costinfo.setExpenseItemID(expenseItemID);
			costinfo.setExpenseAmount(expenseAmount);
			costinfo.setExpenseDate(expenseDate);
			costinfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
			costinfo.setIsDelete(0);
			int result=costService.update(costinfo);
			if(result>0)
			{
				resultStr="[{\"result\":\"Success\"}]";
			}
			else
			{
				resultStr="[{\"result\":\"Failed\"}]";
			}
//		}
//		else
//		{
//			resultStr="[{\"result\":\"isExist\"}]";
//		}
		return resultStr;			
	}
	
	
	@RequestMapping(value="/deleteCostInfo.do")
	@ResponseBody
	public Object deleteCostInfo(int ID) {

		String resultStr="";
		Cost cost=new Cost();
		cost.setId(ID);		
		cost.setIsDelete(1);//"1"����ɾ����"0"����δɾ��
		int result=costService.update(cost);
		if(result>0)
		{
			resultStr="[{\"result\":\"Success\"}]";
		}
		else
		{
			resultStr="[{\"result\":\"Failed\"}]";
		}
		
		return resultStr;
	}

}
