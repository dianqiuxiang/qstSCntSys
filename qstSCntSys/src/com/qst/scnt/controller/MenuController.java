package com.qst.scnt.controller;

import java.util.Arrays;
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
import com.qst.scnt.model.UserInfo;
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
		whereMap.put("parentID",0);//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
		
		List<Menu> list=menuService.selectParam(params);
		
		String returnJson="[";
		int i=0;
		for(Menu item:list){
			
			if(item.getRoleType().contains(this.getCurrentUser().getUserType().toString())){
				i++;
				if(i==1){
					returnJson+="{";
				}
				else{
					returnJson+=",{";
				}
				//returnJson+="{";
				returnJson+="\"id\":"+ item.getId() +",";
				returnJson+="\"text\":\""+ item.getMenuName() +"\",";
				returnJson+="\"children\":[";
				Map<String, Object> fieldMap = new HashMap<String, Object>();
				fieldMap.put("parentID",item.getId());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
				
				Map<String, Object> queryParams = new HashMap<String, Object>();  
				queryParams.put("where", fieldMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
				
				List<Menu> childrenList=menuService.selectParam(queryParams);
				
				int j=0;
				for(Menu childNode:childrenList){
					
					if(childNode.getRoleType().contains(this.getCurrentUser().getUserType().toString())){
						j++;
						if(j==1){
							returnJson+="{";
						}
						else{
							returnJson+=",{";
						}
						returnJson+="\"id\":"+ childNode.getId() +",";
						returnJson+="\"text\":\""+ childNode.getMenuName() +"\",";
						returnJson+="\"attributes\":{\"url\":\""+ childNode.getMenuUrl() +"\"}";
						returnJson+="}";
//						if(j==childrenList.size()){
//							returnJson+="}";
//						}
//						else{
//							returnJson+="},";
//						}
					}
					
				}
				returnJson+="]";
				returnJson+="}";
//				if(i==list.size()){
//					returnJson+="}";
//				}
//				else{
//					returnJson+="},";
//				}
			}			
		}
		returnJson+="]";
		//System.out.println(returnJson);
		return returnJson;
	}
	
//	[{
//		"id":1,
//		"text":"ϵͳ����",
//		"children":[{
//			"id":11,
//			"text":"Ա������",
//			"attributes":{
//					"url":"employee.html"
//				}
//		},{
//			"id":12,
//			"text":"���ù���",
//			"attributes":{
//					"url":"expense.html"
//				}
//		},{
//			"id":13,
//			"text":"�û�����",
//			"attributes":{
//					"url":"user.html"
//				}
//		},{
//			"id":14,
//			"text":"��Ʒ����",
//			"attributes":{
//					"url":"product.html"
//				}
//		},{
//			"id":15,
//			"text":"���Ź���",
//			"attributes":{
//					"url":"department.html"
//				}
//		},{
//			"id":16,
//			"text":"�ͻ�����",
//			"attributes":{
//					"url":"customer.html"
//				}
//		},{
//			"id":17,
//			"text":"�տ����",
//			"attributes":{
//					"url":"receipt.html"
//				}
//		},{
//			"id":18,
//			"text":"�±�������Ϣ����",
//			"attributes":{
//					"url":"monthReport.html"
//				}
//		},{
//			"id":19,
//			"text":"�������",
//			"attributes":{
//					"url":"reportExport.html"
//				}
//		}]
//	}]

	/**
	 * index.htmlҳ���ȡ��ǰ�û�
	 * @return
	 */
	@RequestMapping(value="/getCUser.do")
	@ResponseBody
	public Object getCUser() {
		
		UserInfo cUser=this.getCurrentUser();
		Gson gson=new Gson();
		return gson.toJson(cUser);
	}
}
