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
	
	@RequestMapping(value="/list.do")
	@ResponseBody
	public Object getList(String expenseItemName,int page,int rows) {
		Gson gson = new Gson();
		ExpenseItem expenseIteminfo=new ExpenseItem();
		if(expenseItemName==null||expenseItemName.equals("")){
			expenseIteminfo.setExpenseItem(null);
		}
		else{
			expenseIteminfo.setExpenseItem(expenseItemName);
		}
		//salesDepartmentInfo.setSalesDepartmentName(salesDepartmentName);
		EUDataGridResult<ExpenseItem> list=expenseItemService.selectParamFlexible(expenseIteminfo,page,rows);
		//System.out.println(gson.toJson(list));expenseItemService
		return gson.toJson(list);
	}
	
	/**
	 * 查询所有信息，绑定到下拉列表
	 * @return
	 */
	@RequestMapping(value="/listAll.do")
	@ResponseBody
	public Map<String,Object> getInfo() {

		Gson gson = new Gson();

		List<ExpenseItem> list=expenseItemService.selectAll();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", list);
		//System.out.println(gson.toJson(list));
		return map;
	}
	
	
	/**
	 * 根据Id找
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
	 * 删除
	 * 
	 */
	@RequestMapping(value="/deleteExpenseItem.do")
	@ResponseBody
	public Object deleteExpenseItem(int id) {

		String resultStr="";
		ExpenseItem expenseItem=new ExpenseItem();
		expenseItem.setId(id);		
		expenseItem.setIsDelete(1);//"1"代表删除，"0"代表未删除
		int result=expenseItemService.update(expenseItem);
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
	 * 新增--待修改
	 */
	@RequestMapping(value="/addExpenseItemInfo.do")
	@ResponseBody
	public Object addExpenseItemInfo(@RequestBody  ExpenseItem expenseItem) {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		//待修改
		whereMap.put("expenseItem",expenseItem.getExpenseItem());//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<ExpenseItem> list=expenseItemService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0){
			
			ExpenseItem parent_expenseItem=expenseItemService.selectPK(expenseItem.getParentID());

			expenseItem.setLevel(parent_expenseItem.getLevel()+1);
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
	 * 修改
	 */
	@RequestMapping(value="/updateExpenseItemInfo.do")
	@ResponseBody
	public Object updateSalesDepartmentInfo(@RequestBody  ExpenseItem expenseItem) {
		
		ExpenseItem old_expenseItem=expenseItemService.selectPK(expenseItem.getId());
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("expenseItem",expenseItem.getExpenseItem());//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<ExpenseItem> list=expenseItemService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0||old_expenseItem.getExpenseItem().equals(expenseItem.getExpenseItem())){
			
			ExpenseItem parent_expenseItem=expenseItemService.selectPK(expenseItem.getParentID());

			expenseItem.setLevel(parent_expenseItem.getLevel()+1);
			
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
	
	/**
	 * 查询所有费用项目信息，绑定到下拉列表
	 * @return
	 */
	@RequestMapping(value="/selectExpenseItem.do")
	@ResponseBody
	public Object selectExpenseItem() {

		Gson gson = new Gson();

		List<ExpenseItem> list=expenseItemService.select();
		//System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}

}
