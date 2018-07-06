package com.qst.scnt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qst.scnt.model.ExpenseItem;
import com.qst.scnt.model.Menu;
import com.qst.scnt.service.MenuService;

@Controller
@RequestMapping(value="/menu")
public class MenuController extends BaseController {

	@Resource
	private MenuService menuService;
	
	@RequestMapping(value="/getMenuInfo.do")
	@ResponseBody
	public Object getMenuInfo() {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("parentID",0);//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		
		List<Menu> list=menuService.selectParam(params);
		
		String returnJson="[";
		int i=0;
		for(Menu item:list){
			i++;
			returnJson+="{";
			returnJson+="\"id\":"+ item.getId() +",";
			returnJson+="\"text\":\""+ item.getMenuName() +"\",";
			returnJson+="\"children\":[";
			Map<String, Object> fieldMap = new HashMap<String, Object>();
			fieldMap.put("parentID",item.getId());//指定查询范围,此处默认查询本部门下的顾客信息	 
			
			Map<String, Object> queryParams = new HashMap<String, Object>();  
			queryParams.put("where", fieldMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
			
			List<Menu> childrenList=menuService.selectParam(queryParams);
			
			int j=0;
			for(Menu childNode:childrenList){
				j++;
				returnJson+="{";
				returnJson+="\"id\":"+ childNode.getId() +",";
				returnJson+="\"text\":\""+ childNode.getMenuName() +"\",";
				returnJson+="\"attributes\":{\"url\":\""+ childNode.getMenuUrl() +"\"}";
				
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
	
//	[{
//		"id":1,
//		"text":"系统管理",
//		"children":[{
//			"id":11,
//			"text":"员工管理",
//			"attributes":{
//					"url":"employee.html"
//				}
//		},{
//			"id":12,
//			"text":"费用管理",
//			"attributes":{
//					"url":"expense.html"
//				}
//		},{
//			"id":13,
//			"text":"用户管理",
//			"attributes":{
//					"url":"user.html"
//				}
//		},{
//			"id":14,
//			"text":"产品管理",
//			"attributes":{
//					"url":"product.html"
//				}
//		},{
//			"id":15,
//			"text":"部门管理",
//			"attributes":{
//					"url":"department.html"
//				}
//		},{
//			"id":16,
//			"text":"客户管理",
//			"attributes":{
//					"url":"customer.html"
//				}
//		},{
//			"id":17,
//			"text":"收款管理",
//			"attributes":{
//					"url":"receipt.html"
//				}
//		},{
//			"id":18,
//			"text":"月报其他信息管理",
//			"attributes":{
//					"url":"monthReport.html"
//				}
//		},{
//			"id":19,
//			"text":"报表管理",
//			"attributes":{
//					"url":"reportExport.html"
//				}
//		}]
//	}]

	
}
