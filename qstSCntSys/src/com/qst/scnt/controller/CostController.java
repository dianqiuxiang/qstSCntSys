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
import com.qst.scnt.model.CustomerInfo;
import com.qst.scnt.service.CostService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/cost")
public class CostController extends BaseController {
	
	@Resource
	private CostService costService;
	
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
	public Object selectByEItemIDAndEDate(int expenseItemID,Date expenseDate,int page,int rows) {
		
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
	public Object addCostInfo(int expenseItemID,long expenseAmount,Date expenseDate) {	
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("expenseItemID",expenseItemID);//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<Cost> list=costService.selectParam(params);
		String resultStr="";	
		if(list.size()==0) {				
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
		}else 
		{
			resultStr="[{\"result\":\"isExist\"}]";
		}
		
		
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
	public Object updateCostInfo(int ID,int expenseItemID,long expenseAmount,Date expenseDate) {

		Cost old_costInfo=costService.selectPK(ID);
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("expenseItemID",expenseItemID);//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<Cost> list=costService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0||old_costInfo.getExpenseItemID().equals(expenseItemID)){
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
		}
		else
		{
			resultStr="[{\"result\":\"isExist\"}]";
		}
		return resultStr;
	}
	
	
	@RequestMapping(value="/deleteCostInfo.do")
	@ResponseBody
	public Object deleteCostInfo(int ID) {

		String resultStr="";
		Cost cost=new Cost();
		cost.setId(ID);		
		cost.setIsDelete(1);//"1"代表删除，"0"代表未删除
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
