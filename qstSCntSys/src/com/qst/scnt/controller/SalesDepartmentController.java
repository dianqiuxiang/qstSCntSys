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
	
//	@RequestMapping(value="/selectByDName.do")
//	@ResponseBody
//	public Object selectByDName(String salesDepartmentName,int page,int rows) {
//		
//		Gson gson = new Gson();		
//		SalesDepartmentInfo salesDepartmentInfo=new SalesDepartmentInfo();
//		if(salesDepartmentName==null||salesDepartmentName.equals("")){
//			salesDepartmentInfo.setSalesDepartmentName(null);
//		}
//		else{
//			salesDepartmentInfo.setSalesDepartmentName(salesDepartmentName);
//		}
//		//salesDepartmentInfo.setSalesDepartmentName(salesDepartmentName);
//		EUDataGridResult<SalesDepartmentInfo> list=salesDepartmentInfoService.selectParamFlexible(salesDepartmentInfo,page,rows);
//		//System.out.println(gson.toJson(list));
//		return gson.toJson(list);
//	}
	
	/**
	 * 查询所有销售部门信息
	 * @return
	 */
	@RequestMapping(value="/getInfo.do")
	@ResponseBody
	public Map<String,Object> getInfo() {

		Gson gson = new Gson();

		List<SalesDepartmentInfo> list=salesDepartmentInfoService.selectAll();
		Map<String,Object> map=new HashMap<String,Object>();
		for(SalesDepartmentInfo item : list){
			if(item.getLevel()==1)
			{
				item.set_parentId(null);
			}
		}
		map.put("rows", list);
		//map.put("rows", "[{\"id\":1,\"salesDepartmentName\":\"虎踞总部\",\"salesDepartmentAddress\":\"虎踞路\",\"salesDepartmentPhone\":\"12332112332\",\"level\":1,\"isDelete\":0},{\"id\":2,\"salesDepartmentName\":\"虎踞路-浦口市场\",\"salesDepartmentAddress\":\"浦口\",\"salesDepartmentPhone\":\"12332112332\",\"_parentId\":1,\"parentSalesDepartmentName\":\"虎踞总部\",\"level\":2,\"isDelete\":0},{\"id\":3,\"salesDepartmentName\":\"殷李桂+李荣华销售部\",\"salesDepartmentAddress\":\"12332112332\",\"salesDepartmentPhone\":\"浦口\",\"_parentId\":2,\"parentSalesDepartmentName\":\"虎踞路-浦口市场\",\"level\":3,\"isDelete\":0},{\"id\":4,\"salesDepartmentName\":\"镇江市场\",\"salesDepartmentAddress\":\"镇江\",\"salesDepartmentPhone\":\"11111111111\",\"_parentId\":1,\"parentSalesDepartmentName\":\"虎踞总部\",\"level\":2,\"isDelete\":0},{\"id\":5,\"salesDepartmentName\":\"京口销售部\",\"salesDepartmentAddress\":\"镇江京口\",\"salesDepartmentPhone\":\"22222222222\",\"_parentId\":4,\"parentSalesDepartmentName\":\"镇江市场\",\"level\":3,\"isDelete\":1},{\"id\":6,\"salesDepartmentName\":\"南京江宁\",\"salesDepartmentAddress\":\"13232232323\",\"salesDepartmentPhone\":\"南京江宁\",\"_parentId\":1,\"parentSalesDepartmentName\":\"虎踞总部\",\"level\":2,\"isDelete\":0},{\"id\":7,\"salesDepartmentName\":\"123\",\"salesDepartmentAddress\":\"123\",\"salesDepartmentPhone\":\"123\",\"_parentId\":2,\"parentSalesDepartmentName\":\"虎踞路-浦口市场\",\"level\":3,\"isDelete\":0},{\"id\":8,\"salesDepartmentName\":\"1233\",\"salesDepartmentAddress\":\"1233\",\"salesDepartmentPhone\":\"123333\",\"_parentId\":1,\"parentSalesDepartmentName\":\"虎踞总部\",\"level\":2,\"isDelete\":0}]");
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

//		Gson gson = new Gson();
//
//		List<SalesDepartmentInfo> list=salesDepartmentInfoService.select();
//		//System.out.println(gson.toJson(list));
//		return gson.toJson(list);
		
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
				//returnJson+="\"text\":\""+ childNode.getSalesDepartmentName()+"\"";
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
		//System.out.println(gson.toJson(salesDepartmentInfo));
		return gson.toJson(salesDepartmentInfo);
	}
	
	@RequestMapping(value="/updateSalesDepartmentInfo.do")
	@ResponseBody
	public Object updateSalesDepartmentInfo(@RequestBody  SalesDepartmentInfo salesDepartmentInfo) {
		
		SalesDepartmentInfo old_salesDepartmentInfo=salesDepartmentInfoService.selectPK(salesDepartmentInfo.getId());
		
//		if(old_salesDepartmentInfo.getParentID()==0&&salesDepartmentInfo.getParentID()!=0){
//			return "{\"result\":\"CanNotUpdate\"}";
//		}
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("salesDepartmentName",salesDepartmentInfo.getSalesDepartmentName());//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<SalesDepartmentInfo> list=salesDepartmentInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0||old_salesDepartmentInfo.getSalesDepartmentName().equals(salesDepartmentInfo.getSalesDepartmentName())){
			
//			SalesDepartmentInfo parent_SalesDepartmentInfo=salesDepartmentInfoService.selectPK(salesDepartmentInfo.getParentID());
//
//			if(salesDepartmentInfo.getParentID()!=0){
//				salesDepartmentInfo.setLevel(parent_SalesDepartmentInfo.getLevel()+1);
//			}
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
		//salesDepartmentInfo.setIsDelete(1);//"1"代表删除，"0"代表未删除
		int result=salesDepartmentInfoService.deleteItselfAndItsChildren(salesDepartmentInfo);
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
