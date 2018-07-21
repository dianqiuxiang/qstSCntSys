package com.qst.scnt.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	/**
	 * 上面的login.do中的参数@RequestBody UserInfo userInfo，手机登录会报空指针异常（除非在LoginFilter中加入resp.setHeader5行代码）
	 * 不然，使用下面这个方法登录
	 * @param request
	 * @param response
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/loginPhone.do")
	@ResponseBody // String userName,String pwd,
	public Object phoneLogin(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) {
		//response.addHeader("Access-Control-Allow-Origin", "*"); 
		UserInfo userInfo=new UserInfo();
		userInfo.setUserName(request.getParameter("userName").toString());
		userInfo.setPwd(request.getParameter("pwd").toString());
		//System.out.println(str);
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
