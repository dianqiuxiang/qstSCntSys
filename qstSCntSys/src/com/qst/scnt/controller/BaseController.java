package com.qst.scnt.controller;

import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.model.UserInfo;
import com.qst.scnt.service.SalesDepartmentInfoService;

public class BaseController   {

	private UserInfo CurrentUser;	

	@Autowired  
    private HttpSession session;  
      
    @Autowired  
    private HttpServletRequest request; 
    
    @Resource
	private SalesDepartmentInfoService salesDepartmentInfoService;
    
    public UserInfo getCurrentUser() {
		//HttpSession httpSession= request.getSession();
    	
//    	String testUserName="123";
//    	String testPwd="123";
//    	String testUserPhone="123";
//    	String testUserEmail="123";
//    	int testSalesDepartmentID=3;
//    	int isDelete=0;
//    	UserInfo CurrentUser=new UserInfo();
//    	CurrentUser.setUserName(testUserName);
//    	CurrentUser.setPwd(testPwd);
//    	CurrentUser.setUserPhone(testUserPhone);
//    	CurrentUser.setUserEmail(testUserEmail);
//    	CurrentUser.setSalesDepartmentID(testSalesDepartmentID);
//    	CurrentUser.setIsDelete(isDelete);
    	
    	
	    //System.out.println((UserInfo)session.getAttribute("currentUser"));
	    UserInfo CurrentUser=(UserInfo) session.getAttribute("currentUser");
	    SalesDepartmentInfo SalesDeptInfoModel=salesDepartmentInfoService.selectPK(CurrentUser.getSalesDepartmentID());
	    CurrentUser.setUserType(SalesDeptInfoModel.getLevel());
		return CurrentUser;
	}
    public void setCurrentUser(UserInfo currentUser) {
		CurrentUser = currentUser;
	}
    
}
