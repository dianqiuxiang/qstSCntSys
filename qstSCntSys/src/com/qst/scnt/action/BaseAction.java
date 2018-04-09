package com.qst.scnt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.qst.scnt.model.UserInfo;

public class BaseAction   {

	private UserInfo CurrentUser;	

	@Autowired  
    private HttpSession session;  
      
    @Autowired  
    private HttpServletRequest request; 
    
    public UserInfo getCurrentUser() {
		//HttpSession httpSession= request.getSession();
    	
    	String testUserName="123";
    	String testPwd="123";
    	String testUserPhone="123";
    	String testUserEmail="123";
    	int testSalesDepartmentID=1;
    	int isDelete=0;
    	CurrentUser.setUserName(testUserName);
    	CurrentUser.setPwd(testPwd);
    	CurrentUser.setUserPhone(testUserPhone);
    	CurrentUser.setUserEmail(testUserEmail);
    	CurrentUser.setSalesDepartmentID(testSalesDepartmentID);
    	CurrentUser.setIsDelete(isDelete);
    	
    	
	    //System.out.println((UserInfo)session.getAttribute("currentUser"));
	    //UserInfo CurrentUser=(UserInfo) session.getAttribute("currentUser");
		return CurrentUser;
	}
    public void setCurrentUser(UserInfo currentUser) {
		CurrentUser = currentUser;
	}
}
