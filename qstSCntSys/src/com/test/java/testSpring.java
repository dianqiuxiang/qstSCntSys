package com.test.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageInfo;
import com.qst.scnt.model.UserInfo;
import com.qst.scnt.service.UserInfoService;

public class testSpring {
	
	public static void test()
	{
	ApplicationContext ac =   
	          new ClassPathXmlApplicationContext("applicationContext*.xml");  
	  
	    UserInfoService userService=(UserInfoService)ac.getBean("userService");  
	    UserInfo u=userService.selectPK(new Integer(90));
	    System.out.println(u.getUserName());
	    
//	    System.out.println("insert.start");
//	    Tbl_user a=new Tbl_user();
//	    a.setUsername("aa");
//	    a.setPwd("aa");
//	    a.setStudentid(1);
//	    a.setTec_or_stu(1);
//	    a.setDescription("He or she is Student");
//	    int k=userService.insert(a);
//	    System.out.println(k);
//	    System.out.println("insert.end");
	    
//	    Tbl_user u1=userService.selectPK(new Integer(6));
//	    System.out.println(u1.getUsername());
//	    
//	    System.out.println("update.start");
//	    Tbl_user a1=new Tbl_user();
//	    a1.setId(1);
//	    a1.setUsername("aa");
//	    a1.setPwd("aa");
//	    a1.setTeacherid(null);
//	    a1.setStudentid(1);
//	    a1.setTec_or_stu(2);
//	    a1.setDescription("He or she is Student");
//	    int k1=userService.update(a1);
//	    System.out.println(k1);
//	    System.out.println("update.end");
	    
//	    System.out.println("updateParam.start");
//	    
//	    Map<String, Object> fieldsMap = new HashMap();
//	    fieldsMap.put("teacherid", 1);
//	    fieldsMap.put("studentid", null);
//	    fieldsMap.put("tec_or_stu", 1);
//	    fieldsMap.put("description", "He or she is Teacher");
//	    
//	    Map<String, Object> whereMap = new HashMap();
//	    whereMap.put("studentid", 1);	 
//	    whereMap.put("tec_or_stu", 2);	    
//	    
//	    Map<String, Object> params = new HashMap();  
//	    params.put("fields", fieldsMap);
//	    params.put("where", whereMap);
//	    int k1=userService.updateFields(params);
//	    System.out.println(k1);
//	    System.out.println("updateParam.end");
	    
//	    System.out.println("updateParam.start");
//	    
//	    Map<String, Object> fieldsMap = new HashMap();
//	    fieldsMap.put("studentid", 1);
//	    fieldsMap.put("teacherid", null);
//	    fieldsMap.put("tec_or_stu", 2);
//	    fieldsMap.put("description", "He or she is Student");
//	    
//	    Map<String, Object> whereMap = new HashMap();
//	    whereMap.put("teacherid", 1);	 
//	    whereMap.put("tec_or_stu", 1);	    
//	    
//	    Map<String, Object> params = new HashMap();  
//	    params.put("fields", fieldsMap);
//	    params.put("where", whereMap);
//	    int k1=userService.updateFields(params);
//	    System.out.println(k1);
//	    System.out.println("updateParam.end");
	    
	    
	    System.out.println("updateBatch.start");
	    
	    List<UserInfo> list=new ArrayList();
	    UserInfo a1=new UserInfo();
	    a1.setId(90);
	    a1.setUserName("aa123");
	    a1.setPwd("aa");
	    list.add(a1);
	    
	    UserInfo a2=new UserInfo();
	    a2.setId(91);
	    a2.setUserName("aa124");
	    a2.setPwd("aaa");
	    list.add(a2);
	    
	    UserInfo a3=new UserInfo();
	    a3.setId(92);
	    a3.setUserName("aa125");
	    a3.setPwd("aaaa");
	    list.add(a3);
	    
	    int k1=userService.updateBatch(list);
	    System.out.println(k1);
	    System.out.println("updateBatch.end");
	    
	    
//	    System.out.println("deletePK.start");
//	    int k1=userService.delete(new Integer(6));
//	    System.out.println(k1);
//	    System.out.println("deletePK.end");
	    
	    
//	    System.out.println("deleteParam.start");
//	    Map<String, Object> whereMap = new HashMap();
//	    whereMap.put("studentid", 4);	 
//	    whereMap.put("tec_or_stu", 2);
//	    
//	    Map<String, Object> params = new HashMap();  
//	    params.put("where", whereMap);
//	    int k1=userService.deleteParam(params);	
//	    System.out.println(k1);
//	    System.out.println("deleteParam.end");
	    
	    
//	    System.out.println("deleteBatch.start");
//	    List<Integer> list = new ArrayList();
//	    list.add(3);
//	    list.add(4);
//	    list.add(5);	    
//	    int k1=userService.deleteBatch(list);	
//	    System.out.println(k1);
//	    System.out.println("deleteBatch.end");
	    
	    
//	    System.out.println("truncate.start");	    
//	    int k1=userService.truncate();	
//	    System.out.println(k1);
//	    System.out.println("truncate.end");

	    
//	    System.out.println("truncate.start");	    
//	    int k1=userService.truncate();	
//	    System.out.println(k1);
//	    System.out.println("truncate.end");

	    
//	    System.out.println("count.start");	    
//	    int k1=userService.count();	
//	    System.out.println(k1);
//	    System.out.println("count.end");

	    
//	    System.out.println("countparams.start");
//	    Map<String, Object> whereMap = new HashMap();
//	    whereMap.put("studentid", 4);	 
//	    whereMap.put("tec_or_stu", 2);
//	    
//	    Map<String, Object> params = new HashMap();  
//	    params.put("where", whereMap);
//	    int k1=userService.count(params);	
//	    System.out.println(k1);
//	    System.out.println("countparams.end");

	    
//	    System.out.println("select.start");	    
//	    List<Tbl_user> list=userService.select();	
//	    System.out.println(list.size());
//	    System.out.println("select.end");

	    
//	    System.out.println("selectParam.start");
//	    Map<String, Object> whereMap = new HashMap();
//	    whereMap.put("studentid", 4);	 
//	    whereMap.put("tec_or_stu", 2);
//	    
//	    Map<String, Object> params = new HashMap();  
//	    params.put("where", whereMap);
//	    List<Tbl_user> list=userService.selectParam(params);	
//	    System.out.println(list.size());
//	    System.out.println("selectParam.end");

	    
//	    System.out.println("selectPagination.start");
//	    Map<String, Object> whereMap = new HashMap();
//	    whereMap.put("studentid", 4);	 
//	    whereMap.put("tec_or_stu", 2);
//	    
//	    Map<String, Object> params = new HashMap();  
//	    params.put("where", whereMap);
//	    PageInfo<Tbl_user> pageInfo=userService.selectPagination(params, 2, 2);	
//	    System.out.println(pageInfo);
//	    
//	    List<Tbl_user> user=pageInfo.getList();
//	    for(int i=0;i<user.size();i++)
//	    {
//	    	System.out.println(user.get(i));
//	    }
//	    
//	    System.out.println("selectPagination.end");

	    
//	    System.out.println("insertBatch.start");
//	    Tbl_user a1=new Tbl_user();
//	    //a1.setId(1);
//	    a1.setUsername("aa1");
//	    a1.setPwd("aa1");
//	    //a1.setTeacherid(null);
//	    a1.setStudentid(1);
//	    a1.setTec_or_stu(2);
//	    a1.setDescription("He or she is Student");
//	    
//	    Tbl_user a2=new Tbl_user();
//	    //a2.setId(1);
//	    a2.setUsername("aa2");
//	    a2.setPwd("aa2");
//	    //a2.setTeacherid(null);
//	    a2.setStudentid(1);
//	    a2.setTec_or_stu(2);
//	    a2.setDescription("He or she is Student");
//	    
//	    List<Tbl_user> user=new ArrayList();
//	    user.add(a1);
//	    user.add(a2);
//	    
//	    int k1=userService.insertBatch(user);
//	    System.out.println("insertBatch.end");
	}
	
	public static void main(String[] args) {
		test();
	}
}
