package com.qst.scnt.action.loginAction;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qst.scnt.model.UserInfo;
import com.qst.scnt.service.UserInfoService;

@Controller
@RequestMapping(value="/login")
public class LoginAction{
	
	private List<UserInfo> userList;

	public List<UserInfo> getUserList() {
		return userList;
	}

	public void setUserList(List<UserInfo> userList) {
		this.userList = userList;
	}
	@Resource
	private UserInfoService userService;
	
	public UserInfoService getUserService() {
		return userService;
	}

	public void setUserService(UserInfoService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/select.do")
	@ResponseBody
	public Object select() {
		Gson gson = new Gson();
		System.out.println(123);
		//调用存储过程，获取列表
		//this.userService.get(1);
		System.out.println(this.userService.selectPK(91).getUserName());
		return gson.toJson("123");
	}
	 public String execute() {
//		 this.contrList = this.tabService.queryContractList(new Contract());

	        return "";
	    }
}
