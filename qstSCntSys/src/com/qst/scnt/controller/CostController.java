package com.qst.scnt.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qst.scnt.model.Cost;
import com.qst.scnt.model.EveryMonthOtherInfo;
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
		ExpenseItem model=new ExpenseItem();
		model.setId(-1);
		model.setExpenseItem("选择所有");
		list.add(0, model); 
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/getAllExpenseItem.do")
	@ResponseBody
	public Object getAllExpenseItem() {		
		Gson gson = new Gson();

		List<ExpenseItem> list=expenseItemService.select();
//		ExpenseItem model=new ExpenseItem();
//		model.setId(-1);
//		model.setExpenseItem("选择所有");
//		list.add(0, model); 
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
	
	@RequestMapping(value="/selectByItemIdAndStartAndEndDate.do")
	@ResponseBody
	public Object selectByItemIdAndStartAndEndDate(Integer expenseItemID,String startDate,String endDate,int page,int rows) {
		
		Gson gson = new Gson();	
		
		Map<String, Object> queryDate = new HashMap<String, Object>();
		if(expenseItemID==null||expenseItemID==-1){
			queryDate.put("expenseItemID",null);
		}
		else{
			queryDate.put("expenseItemID",expenseItemID);
		}
		if(startDate==null||startDate.equals(""))
		{
			queryDate.put("startDate",null);
		}
		else
		{ 
			queryDate.put("startDate",startDate);
		}
		
		if(endDate==null||endDate.equals(""))
		{
			queryDate.put("endDate",null);
		}
		else
		{ 
			queryDate.put("endDate",endDate);
		}
		
		queryDate.put("salesDepartmentID",this.getCurrentUser().getSalesDepartmentID());
				
		EUDataGridResult<Cost> list=costService.selectByItemIdAndStartAndEndDate(queryDate,page,rows);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/addCostInfo.do")
	@ResponseBody
	public Object addCostInfo(@RequestBody Cost cost) {	
//		/**********费用可以重复（ZL修改）**********/
//		Map<String, Object> whereMap = new HashMap<String, Object>();
//		whereMap.put("expenseItemID",expenseItemID);//指定查询范围,此处默认查询本部门下的顾客信息	 
//		
//		Map<String, Object> params = new HashMap<String, Object>();  
//		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
//		List<Cost> list=costService.selectParam(params);
		String resultStr="";	
//		if(list.size()==0) {				
			
			cost.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
			cost.setIsDelete(0);
			int result=costService.insert(cost);
			if(result>0)
			{
				resultStr="{\"result\":\"Success\"}";
			}
			else
			{
				resultStr="{\"result\":\"Failed\"}";
			}
//		}else 
//		{
//			resultStr="{\"result\":\"isExist\"}";
//		}
		return resultStr;
	}
	
	@RequestMapping(value="/selectByID.do")
	@ResponseBody
	public Object selectByID(int id) {

		Gson gson = new Gson();
		Cost cost=costService.selectPK(id);
		return gson.toJson(cost);
	}
	
	@RequestMapping(value="/updateCostInfo.do")
	@ResponseBody
	public Object updateCostInfo(@RequestBody Cost cost) {
//		/**********费用可以重复（ZL修改）**********/
//		Cost old_costInfo=costService.selectPK(ID);
//		
//		Map<String, Object> whereMap = new HashMap<String, Object>();
//		whereMap.put("expenseItemID",expenseItemID);//指定查询范围,此处默认查询本部门下的顾客信息	 
//		
//		Map<String, Object> params = new HashMap<String, Object>();  
//		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
//		List<Cost> list=costService.selectParam(params);
		
		String resultStr="";
//		if(list.size()==0||old_costInfo.getExpenseItemID().equals(expenseItemID)){
//			Cost costinfo=new Cost();
//			costinfo.setId(ID);
//			costinfo.setExpenseItemID(expenseItemID);
//			costinfo.setExpenseAmount(expenseAmount);
//			costinfo.setExpenseDate(expenseDate);
			cost.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
			cost.setIsDelete(0);
			int result=costService.update(cost);
			if(result>0)
			{
				resultStr="{\"result\":\"Success\"}";
			}
			else
			{
				resultStr="{\"result\":\"Failed\"}";
			}
//		}
//		else
//		{
//			resultStr="{\"result\":\"isExist\"}";
//		}
		return resultStr;			
	}
	
	
	@RequestMapping(value="/deleteCostInfo.do")
	@ResponseBody
	public Object deleteCostInfo(int id) {

		String resultStr="";
		Cost cost=new Cost();
		cost.setId(id);		
		cost.setIsDelete(1);//"1"代表删除，"0"代表未删除
		int result=costService.update(cost);
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

}
