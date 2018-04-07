package com.qst.scnt.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qst.scnt.service.UserInfoService;

@Controller
@RequestMapping(value="/test")
public class Test extends BaseAction {

	@Resource
	private UserInfoService userInfoService;	

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
		
		System.out.println("currentUser"+this.getCurrentUser().getUserName());
		return gson.toJson("1234");
	}
}
