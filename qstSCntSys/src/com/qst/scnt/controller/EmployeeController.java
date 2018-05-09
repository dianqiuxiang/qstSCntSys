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
import com.qst.scnt.service.EmployeeInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/employee")
public class EmployeeController extends BaseController {

	@Resource
	private EmployeeInfoService employeeInfoService;
	
	@RequestMapping(value="/selectByENameAndESex.do")
	@ResponseBody
	public Object selectByENameAndESex(String employeeName,String sex,int page,int rows) {
		
		Gson gson = new Gson();		
		EmployeeInfo employeeInfo=new EmployeeInfo();
		
		if(employeeName==null||employeeName.equals(""))
		{
			employeeInfo.setEmployeeName(null);
		}
		else
		{ 
			employeeInfo.setEmployeeName(employeeName);
		}
		
		if(sex==null||sex.equals(""))
		{
			employeeInfo.setSex(null);
		}
		else
		{ 
			employeeInfo.setSex(sex);
		}
		
		//employeeInfo.setEmployeeName(employeeName);
		//employeeInfo.setSex(sex);
		employeeInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
		
		EUDataGridResult<EmployeeInfo> list=employeeInfoService.selectParamFlexible(employeeInfo,page,rows);
		System.out.println(gson.toJson(list));
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

			employeeInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
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
		System.out.println(gson.toJson(employeeInfo));
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
