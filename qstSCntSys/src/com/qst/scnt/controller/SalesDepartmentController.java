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
		if(salesDepartmentName==null||salesDepartmentName.equals("")){
			salesDepartmentInfo.setSalesDepartmentName(null);
		}
		else{
			salesDepartmentInfo.setSalesDepartmentName(salesDepartmentName);
		}
		//salesDepartmentInfo.setSalesDepartmentName(salesDepartmentName);
		EUDataGridResult<SalesDepartmentInfo> list=salesDepartmentInfoService.selectParamFlexible(salesDepartmentInfo,page,rows);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	/**
	 * 查询所有销售部门信息，绑定到下拉列表
	 * @return
	 */
	@RequestMapping(value="/getInfo.do")
	@ResponseBody
	public Map<String,Object> getInfo() {

		Gson gson = new Gson();

		List<SalesDepartmentInfo> list=salesDepartmentInfoService.selectAll();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", list);
		System.out.println(gson.toJson(list));
		return map;
	}
	
	
	/**
	 * 查询所有销售部门信息，绑定到下拉列表
	 * @return
	 */
	@RequestMapping(value="/selectSalesDepartment.do")
	@ResponseBody
	public Object selectSalesDepartment() {

		Gson gson = new Gson();

		List<SalesDepartmentInfo> list=salesDepartmentInfoService.select();
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
			
			SalesDepartmentInfo parent_SalesDepartmentInfo=salesDepartmentInfoService.selectPK(salesDepartmentInfo.getParentID());

			salesDepartmentInfo.setLevel(parent_SalesDepartmentInfo.getLevel()+1);
			salesDepartmentInfo.setIsDelete(0);
			
			int result=salesDepartmentInfoService.insert(salesDepartmentInfo);
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
		SalesDepartmentInfo salesDepartmentInfo=salesDepartmentInfoService.selectPK(id);
		System.out.println(gson.toJson(salesDepartmentInfo));
		return gson.toJson(salesDepartmentInfo);
	}
	
	@RequestMapping(value="/updateSalesDepartmentInfo.do")
	@ResponseBody
	public Object updateSalesDepartmentInfo(@RequestBody  SalesDepartmentInfo salesDepartmentInfo) {
		
		SalesDepartmentInfo old_salesDepartmentInfo=salesDepartmentInfoService.selectPK(salesDepartmentInfo.getId());
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("salesDepartmentName",salesDepartmentInfo.getSalesDepartmentName());//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<SalesDepartmentInfo> list=salesDepartmentInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0||old_salesDepartmentInfo.getSalesDepartmentName().equals(salesDepartmentInfo.getSalesDepartmentName())){
			
			SalesDepartmentInfo parent_SalesDepartmentInfo=salesDepartmentInfoService.selectPK(salesDepartmentInfo.getParentID());

			salesDepartmentInfo.setLevel(parent_SalesDepartmentInfo.getLevel()+1);
			
			int result=salesDepartmentInfoService.update(salesDepartmentInfo);
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
	
	@RequestMapping(value="/deleteSalesDepartmentInfo.do")
	@ResponseBody
	public Object deleteSalesDepartmentInfo(int id) {

		String resultStr="";
		SalesDepartmentInfo salesDepartmentInfo=new SalesDepartmentInfo();
		salesDepartmentInfo.setId(id);		
		salesDepartmentInfo.setIsDelete(1);//"1"代表删除，"0"代表未删除
		int result=salesDepartmentInfoService.update(salesDepartmentInfo);
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
