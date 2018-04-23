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
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.service.SalesDepartmentInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/salesDepartment")
public class SalesDepartmentController extends BaseController {

	@Resource
	private SalesDepartmentInfoService salesDepartmentInfoService;
	
	@RequestMapping(value="/selectByDName.do")
	@ResponseBody
	public Object selectByDName(String salesDepartmentName,int page,int rows) {
		
		Gson gson = new Gson();		
		SalesDepartmentInfo salesDepartmentInfo=new SalesDepartmentInfo();
		salesDepartmentInfo.setSalesDepartmentName(salesDepartmentName);
		
		EUDataGridResult<SalesDepartmentInfo> list=salesDepartmentInfoService.selectParamFlexible(salesDepartmentInfo,page,rows);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/addSalesDepartmentInfo.do")
	@ResponseBody
	public Object addSalesDepartmentInfo(@RequestBody  SalesDepartmentInfo salesDepartmentInfo) {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("salesDepartmentName",salesDepartmentInfo.getSalesDepartmentName());//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<SalesDepartmentInfo> list=salesDepartmentInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0){

			salesDepartmentInfo.setIsDelete(0);
			
			int result=salesDepartmentInfoService.insert(salesDepartmentInfo);
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
	
	@RequestMapping(value="/selectByID.do")
	@ResponseBody
	public Object selectByID(int ID) {
		
		Gson gson = new Gson();		
		SalesDepartmentInfo salesDepartmentInfo=salesDepartmentInfoService.selectPK(ID);
		System.out.println(gson.toJson(salesDepartmentInfo));
		return gson.toJson(salesDepartmentInfo);
	}
	
	@RequestMapping(value="/updateSalesDepartmentInfo.do")
	@ResponseBody
	public Object updateSalesDepartmentInfo(@RequestBody  SalesDepartmentInfo salesDepartmentInfo) {
		
		SalesDepartmentInfo old_salesDepartmentInfo=salesDepartmentInfoService.selectPK(salesDepartmentInfo.getId());
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("productName",salesDepartmentInfo.getSalesDepartmentName());//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<SalesDepartmentInfo> list=salesDepartmentInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0||old_salesDepartmentInfo.getSalesDepartmentName().equals(salesDepartmentInfo.getSalesDepartmentName())){
			
			int result=salesDepartmentInfoService.update(salesDepartmentInfo);
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
	
	@RequestMapping(value="/deleteSalesDepartmentInfo.do")
	@ResponseBody
	public Object deleteSalesDepartmentInfo(int ID) {

		String resultStr="";
		SalesDepartmentInfo salesDepartmentInfo=new SalesDepartmentInfo();
		salesDepartmentInfo.setId(ID);		
		salesDepartmentInfo.setIsDelete(1);//"1"代表删除，"0"代表未删除
		int result=salesDepartmentInfoService.update(salesDepartmentInfo);
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
