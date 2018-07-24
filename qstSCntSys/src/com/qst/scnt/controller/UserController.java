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
import com.qst.scnt.model.UserInfo;
import com.qst.scnt.service.SalesDepartmentInfoService;
import com.qst.scnt.service.UserInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {

	@Resource
	private UserInfoService userInfoService;
	
	@Resource
	private SalesDepartmentInfoService salesDepartmentInfoService;
	
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
	
	@RequestMapping(value="/selectByUName.do")
	@ResponseBody
	public Object selectByUName(String userName,int page,int rows) {
		
		Gson gson = new Gson();		
		UserInfo userInfo=new UserInfo();
		//userInfo.setUserName(userName);
		
		if(userName==null||userName.equals(""))
		{
			userInfo.setUserName(null);
		}
		else
		{ 
			userInfo.setUserName(userName);
		}
		
		EUDataGridResult<UserInfo> list=userInfoService.selectParamFlexible(userInfo,page,rows);
		//System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/addUserInfo.do")
	@ResponseBody
	public Object addUserInfo(@RequestBody  UserInfo userInfo) {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("userName",userInfo.getUserName());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
		List<UserInfo> list=userInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0){

			userInfo.setPwd("888888");//Ĭ������
			userInfo.setIsDelete(0);
			
			int result=userInfoService.insert(userInfo);
			if(result>0)
			{
				resultStr="{\"result\":\"Success|888888\"}";
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
		UserInfo userInfo=userInfoService.selectPK(id);
		//System.out.println(gson.toJson(userInfo));
		return gson.toJson(userInfo);
	}
	
	@RequestMapping(value="/updateUserInfo.do")
	@ResponseBody
	public Object updateUserInfo(@RequestBody  UserInfo userInfo) {
		
		UserInfo old_userInfo=userInfoService.selectPK(userInfo.getId());
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("userName",userInfo.getUserName());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
		List<UserInfo> list=userInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0||old_userInfo.getUserName().equals(userInfo.getUserName())){
			
			int result=userInfoService.update(userInfo);
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
	
	@RequestMapping(value="/deleteUserInfo.do")
	@ResponseBody
	public Object deleteUserInfo(int id) {

		String resultStr="";
		UserInfo userInfo=new UserInfo();
		userInfo.setId(id);		
		userInfo.setIsDelete(1);//"1"����ɾ����"0"����δɾ��
		int result=userInfoService.update(userInfo);
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
	
	
	@RequestMapping(value="/updatePwd.do")
	@ResponseBody
	public Object updatePwd(@RequestBody  UserInfo userInfo) {

		String resultStr="";
		userInfo.setId(this.getCurrentUser().getId());		
		
		int result=userInfoService.update(userInfo);
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
