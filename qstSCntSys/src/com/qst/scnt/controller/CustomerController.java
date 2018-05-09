package com.qst.scnt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qst.scnt.model.CustomerInfo;
import com.qst.scnt.model.EmployeeInfo;
import com.qst.scnt.service.CustomerInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/customer")
public class CustomerController extends BaseController {
	
	@Resource
	private CustomerInfoService customerInfoService;
	
	@RequestMapping(value="/getInfo.do")
	@ResponseBody
	public Object getInfo() {		
		Gson gson = new Gson();

		List<CustomerInfo> list=customerInfoService.select();
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/selectByCNameAndCPhone.do")
	@ResponseBody
	public Object selectByCNameAndCPhone(String customerName,String customerPhone,int page,int rows) {
		
		Gson gson = new Gson();		
		CustomerInfo customerInfo=new CustomerInfo();
		
		if(customerName==null||customerName.equals(""))
		{
			customerInfo.setCustomerName(null);
		}
		else
		{ 
			customerInfo.setCustomerName(customerName);
		}
		if(customerPhone==null||customerPhone.equals(""))
		{
			customerInfo.setCustomerPhone(null);
		}
		else
		{ 
			customerInfo.setCustomerPhone(customerPhone);
		}
		
		customerInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
		
		EUDataGridResult<CustomerInfo> list=customerInfoService.selectParamFlexible(customerInfo,page,rows);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/addCustomerInfo.do")
	@ResponseBody
	public Object addCustomerInfo(@RequestBody  CustomerInfo customerInfo) {

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("customerPhone",customerInfo.getCustomerPhone());//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<CustomerInfo> list=customerInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0){
			customerInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
			customerInfo.setIsDelete(0);
			int result=customerInfoService.insert(customerInfo);
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
		CustomerInfo customerInfo=customerInfoService.selectPK(id);
		return gson.toJson(customerInfo);
	}
	
	@RequestMapping(value="/updateCustomerInfo.do")
	@ResponseBody
	public Object updateCustomerInfo(@RequestBody  CustomerInfo customerInfo) {

		CustomerInfo old_customerInfo=customerInfoService.selectPK(customerInfo.getId());
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("customerPhone",customerInfo.getCustomerPhone());//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<CustomerInfo> list=customerInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0||old_customerInfo.getCustomerPhone().equals(customerInfo.getCustomerPhone())){
			
//			customerInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
//			customerInfo.setIsDelete(0);
			int result=customerInfoService.update(customerInfo);
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
	
	@RequestMapping(value="/deleteCustomerInfo.do")
	@ResponseBody
	public Object deleteCustomerInfo(int id) {

		String resultStr="";
		CustomerInfo customerInfo=new CustomerInfo();
		customerInfo.setId(id);		
		customerInfo.setIsDelete(1);//"1"代表删除，"0"代表未删除
		int result=customerInfoService.update(customerInfo);
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
