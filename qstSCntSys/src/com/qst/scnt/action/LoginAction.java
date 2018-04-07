package com.qst.scnt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
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
public class LoginAction extends BaseAction {
	
	@Resource
	private UserInfoService userInfoService;

	@RequestMapping(value="/login.do",method = RequestMethod.GET)
	public ModelAndView login(UserInfo userInfo,HttpSession httpSession) {
		System.out.println(userInfo.getUserName());
		Map<String, Object> whereMap = new HashMap();
		whereMap.put("userName", "123");	 
		whereMap.put("pwd", "123");
		
	    Map<String, Object> params = new HashMap();  
        params.put("where", whereMap);
		UserInfo currentUser=(UserInfo) this.userInfoService.selectParam(params).get(0);
		
		System.out.println(this.userInfoService.selectParam(params).get(0).getUserName());
		if(currentUser!=null){ 
			httpSession.setAttribute("currentUser", currentUser);  
            return new ModelAndView(new RedirectView("index.html"));  
        }else{  
            return new ModelAndView(new RedirectView("index.html"));  
        }  
	}
	
	
	@RequestMapping(value="/select.do")
	@ResponseBody
	public Object select() {
		 //System.out.println(userInfo.getUserName());
		Gson gson = new Gson();
		System.out.println(123);
		//调用存储过程，获取列表
		//this.userService.get(1);
		System.out.println(this.userInfoService.selectPK(1).getUserName());
		System.out.println(gson.toJson(this.userInfoService.selectPK(1)));
		return gson.toJson("123");
	}
	 public String execute() {
//		 this.contrList = this.tabService.queryContractList(new Contract());

	        return "";
	    }
}
