package com.qst.scnt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;
import com.qst.scnt.model.UserInfo;
import com.qst.scnt.service.UserInfoService;
@Controller
@RequestMapping(value="/login")
public class LoginController extends BaseController {
	
	@Resource
	private UserInfoService userInfoService;

	@RequestMapping(value="/login.do")
	@ResponseBody // String userName,String pwd,
	public Object login(HttpServletRequest request,HttpServletResponse response,@RequestBody UserInfo userInfo,HttpSession httpSession) {
		response.addHeader("Access-Control-Allow-Origin", "*"); 
//		UserInfo userInfo=new UserInfo();
//		userInfo.setUserName(userName);
//		userInfo.setPwd(pwd);
		//System.out.println(userInfo.getUserName());
		//System.out.println(userInfo.getPwd());

		UserInfo currentUser=userInfoService.login(userInfo);
		
		String resultStr="";
		if(currentUser!=null){ 
			httpSession.setAttribute("currentUser", currentUser);  
            resultStr="{\"result\":\"Success\"}";  
             
        }else{  
        	resultStr="{\"result\":\"Failed\"}";  
        }  
		return resultStr;
	}
	
//	UserInfo userInfo=new UserInfo();
//	userInfo.setUserName(userName);
//	userInfo.setPwd(pwd);
}
