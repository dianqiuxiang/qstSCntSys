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
import com.qst.scnt.model.UserInfo;
import com.qst.scnt.service.UserInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {

	@Resource
	private UserInfoService userInfoService;
	
	@RequestMapping(value="/selectByUName.do")
	@ResponseBody
	public Object selectByUName(String userName,int page,int rows) {
		
		Gson gson = new Gson();		
		UserInfo userInfo=new UserInfo();
		userInfo.setUserName(userName);
		
		EUDataGridResult<UserInfo> list=userInfoService.selectParamFlexible(userInfo,page,rows);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/addUserInfo.do")
	@ResponseBody
	public Object addUserInfo(@RequestBody  UserInfo userInfo) {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("userName",userInfo.getUserName());//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<UserInfo> list=userInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0){

			userInfo.setIsDelete(0);
			
			int result=userInfoService.insert(userInfo);
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
		UserInfo userInfo=userInfoService.selectPK(ID);
		System.out.println(gson.toJson(userInfo));
		return gson.toJson(userInfo);
	}
	
	@RequestMapping(value="/updateUserInfo.do")
	@ResponseBody
	public Object updateUserInfo(@RequestBody  UserInfo userInfo) {
		
		UserInfo old_userInfo=userInfoService.selectPK(userInfo.getId());
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("userName",userInfo.getUserName());//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<UserInfo> list=userInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0||old_userInfo.getUserName().equals(userInfo.getUserName())){
			
			int result=userInfoService.update(userInfo);
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
	
	@RequestMapping(value="/deleteUserInfo.do")
	@ResponseBody
	public Object deleteUserInfo(int ID) {

		String resultStr="";
		UserInfo userInfo=new UserInfo();
		userInfo.setId(ID);		
		userInfo.setIsDelete(1);//"1"代表删除，"0"代表未删除
		int result=userInfoService.update(userInfo);
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
