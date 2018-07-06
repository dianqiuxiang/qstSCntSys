package com.qst.scnt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qst.scnt.model.EmployeeInfo;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.service.EmployeeInfoService;
import com.qst.scnt.service.SalesDepartmentInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/employee")
public class EmployeeController extends BaseController {

	@Resource
	private EmployeeInfoService employeeInfoService;
	
	@Resource
	private SalesDepartmentInfoService salesDepartmentInfoService;
	
	/**
	 * 查询所有部门信息
	 * @return
	 */
	@RequestMapping(value="/getSalesDept.do")
	@ResponseBody
	public Object getSalesDept(){
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("parentID",0);//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		
		List<SalesDepartmentInfo> list=salesDepartmentInfoService.selectParam(params);
		
		String returnJson="[";
		int i=0;
		for(SalesDepartmentInfo item:list){
			i++;
			returnJson+="{";
			returnJson+="\"id\":"+ item.getId() +",";
			returnJson+="\"text\":\""+ item.getSalesDepartmentName() +"\",";
			returnJson+="\"children\":[";
			Map<String, Object> fieldMap = new HashMap<String, Object>();
			fieldMap.put("parentID",item.getId());//指定查询范围,此处默认查询本部门下的顾客信息	 
			
			Map<String, Object> queryParams = new HashMap<String, Object>();  
			queryParams.put("where", fieldMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
			
			List<SalesDepartmentInfo> childrenList=salesDepartmentInfoService.selectParam(queryParams);
			
			int j=0;
			for(SalesDepartmentInfo childNode:childrenList){
				j++;
				returnJson+="{";
				returnJson+="\"id\":"+ childNode.getId() +",";
				returnJson+="\"text\":\""+ childNode.getSalesDepartmentName() +"\",";
				returnJson+="\"children\":[";
				
				Map<String, Object> fieldMap2 = new HashMap<String, Object>();
				fieldMap2.put("parentID",childNode.getId());//指定查询范围,此处默认查询本部门下的顾客信息	 
				
				Map<String, Object> queryParams2 = new HashMap<String, Object>();  
				queryParams2.put("where", fieldMap2); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
				
				List<SalesDepartmentInfo> childrenList2=salesDepartmentInfoService.selectParam(queryParams2);
				
				int k=0;
				for(SalesDepartmentInfo childNode2:childrenList2){
					k++;
					returnJson+="{";
					returnJson+="\"id\":"+ childNode2.getId() +",";
					returnJson+="\"text\":\""+ childNode2.getSalesDepartmentName() +"\"";
					
					
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
	
	@RequestMapping(value="/selectByENameAndESex.do")
	@ResponseBody
	public Object selectByENameAndESex(Integer salesDepartmentID,String employeeName,String sex,int page,int rows) {
		
		Gson gson = new Gson();		
		Map<String, Object> queryParam = new HashMap<String, Object>();
		
		//queryDate.put("orderCode",orderCode);
		
		if(employeeName==null||employeeName.equals(""))
		{
			queryParam.put("employeeName",null);
		}
		else
		{ 
			queryParam.put("employeeName",employeeName);
		}
		
		if(sex==null||sex.equals(""))
		{
			queryParam.put("sex",null);
		}
		else
		{ 
			queryParam.put("sex",sex);
		}
		
		//employeeInfo.setEmployeeName(employeeName);
		//employeeInfo.setSex(sex);
		queryParam.put("salesDepartmentID",salesDepartmentID);
		
		EUDataGridResult<EmployeeInfo> list=employeeInfoService.selectByNameAndSex(queryParam,page,rows);
		//System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/addEmployeeInfo.do")
	@ResponseBody
	public Object addEmployeeInfo(@RequestBody  EmployeeInfo employeeInfo) {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("employeeName",employeeInfo.getEmployeeName());//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<EmployeeInfo> list=employeeInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0){

			//employeeInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
			employeeInfo.setIsDelete(0);
			
			int result=employeeInfoService.insert(employeeInfo);
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
	
	@RequestMapping(value="/selectByID.do")
	@ResponseBody
	public Object selectByID(int id) {
		
		Gson gson = new Gson();		
		EmployeeInfo employeeInfo=employeeInfoService.selectPK(id);
		//System.out.println(gson.toJson(employeeInfo));
		return gson.toJson(employeeInfo);
	}
	
	@RequestMapping(value="/updateEmployeeInfo.do")
	@ResponseBody
	public Object updateEmployeeInfo(@RequestBody  EmployeeInfo employeeInfo) {
		
		EmployeeInfo old_employeeInfo=employeeInfoService.selectPK(employeeInfo.getId());
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("employeeName",employeeInfo.getEmployeeName());//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<EmployeeInfo> list=employeeInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0||old_employeeInfo.getEmployeeName().equals(employeeInfo.getEmployeeName())){
			
			int result=employeeInfoService.update(employeeInfo);
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
	
	@RequestMapping(value="/deleteEmployeeInfo.do")//,method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object deleteEmployeeInfo(int id) {

		String resultStr="";
		EmployeeInfo employeeInfo=new EmployeeInfo();
		employeeInfo.setId(id);		
		employeeInfo.setIsDelete(1);//"1"代表删除，"0"代表未删除
		int result=employeeInfoService.update(employeeInfo);
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
