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
	 * ��ѯ�������۲�����Ϣ
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
		//map.put("rows", "[{\"id\":1,\"salesDepartmentName\":\"�����ܲ�\",\"salesDepartmentAddress\":\"����·\",\"salesDepartmentPhone\":\"12332112332\",\"level\":1,\"isDelete\":0},{\"id\":2,\"salesDepartmentName\":\"����·-�ֿ��г�\",\"salesDepartmentAddress\":\"�ֿ�\",\"salesDepartmentPhone\":\"12332112332\",\"_parentId\":1,\"parentSalesDepartmentName\":\"�����ܲ�\",\"level\":2,\"isDelete\":0},{\"id\":3,\"salesDepartmentName\":\"�����+���ٻ����۲�\",\"salesDepartmentAddress\":\"12332112332\",\"salesDepartmentPhone\":\"�ֿ�\",\"_parentId\":2,\"parentSalesDepartmentName\":\"����·-�ֿ��г�\",\"level\":3,\"isDelete\":0},{\"id\":4,\"salesDepartmentName\":\"���г�\",\"salesDepartmentAddress\":\"��\",\"salesDepartmentPhone\":\"11111111111\",\"_parentId\":1,\"parentSalesDepartmentName\":\"�����ܲ�\",\"level\":2,\"isDelete\":0},{\"id\":5,\"salesDepartmentName\":\"�������۲�\",\"salesDepartmentAddress\":\"�򽭾���\",\"salesDepartmentPhone\":\"22222222222\",\"_parentId\":4,\"parentSalesDepartmentName\":\"���г�\",\"level\":3,\"isDelete\":1},{\"id\":6,\"salesDepartmentName\":\"�Ͼ�����\",\"salesDepartmentAddress\":\"13232232323\",\"salesDepartmentPhone\":\"�Ͼ�����\",\"_parentId\":1,\"parentSalesDepartmentName\":\"�����ܲ�\",\"level\":2,\"isDelete\":0},{\"id\":7,\"salesDepartmentName\":\"123\",\"salesDepartmentAddress\":\"123\",\"salesDepartmentPhone\":\"123\",\"_parentId\":2,\"parentSalesDepartmentName\":\"����·-�ֿ��г�\",\"level\":3,\"isDelete\":0},{\"id\":8,\"salesDepartmentName\":\"1233\",\"salesDepartmentAddress\":\"1233\",\"salesDepartmentPhone\":\"123333\",\"_parentId\":1,\"parentSalesDepartmentName\":\"�����ܲ�\",\"level\":2,\"isDelete\":0}]");
		System.out.println(gson.toJson(list));
		return map;
	}
	
	
	/**
	 * ��ѯ�������۲�����Ϣ���󶨵������б�
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
		whereMap.put("parentID",0);//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
		
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
			fieldMap.put("parentID",item.getId());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
			
			Map<String, Object> queryParams = new HashMap<String, Object>();  
			queryParams.put("where", fieldMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
			
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
				fieldMap2.put("parentID",childNode.getId());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
				
				Map<String, Object> queryParams2 = new HashMap<String, Object>();  
				queryParams2.put("where", fieldMap2); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
				
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
		whereMap.put("salesDepartmentName",salesDepartmentInfo.getSalesDepartmentName());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
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
		whereMap.put("salesDepartmentName",salesDepartmentInfo.getSalesDepartmentName());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
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
		//salesDepartmentInfo.setIsDelete(1);//"1"����ɾ����"0"����δɾ��
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
