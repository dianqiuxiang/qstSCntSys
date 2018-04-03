package com.qst.scnt.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.UserInfoDao;
import com.qst.scnt.model.UserInfo;
import com.qst.scnt.service.UserInfoService;


@Service("userService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {
	
	@Resource    
    private UserInfoDao userDao;  
      
    @Override  
    public BaseDao<UserInfo> getBaseDao() {  
        return userDao;  
    }
    
    @Override
    @Transactional
    public int update()
    {
    	UserInfo a1=new UserInfo();
	    a1.setId(1);
	    a1.setUserName("321");
	    a1.setPwd("321");
	   
	    System.out.println("aaaaaaaaaaaaaaaaaa1");
	    int aa=userDao.update(a1);
	    System.out.println("aaaaaaaaaaaaaaaaaa2");
	    UserInfo a2=new UserInfo();
	    a2.setId(2);
	    a2.setUserName("aa");
	    a2.setPwd("aa");
	    
	    int i = 1 / 0; // 抛出运行时异常，事务回滚
	    System.out.println("aaaaaaaaaaaaaaaaaa3");
	    int ab=userDao.update(a2);
	    System.out.println("aaaaaaaaaaaaaaaaaa4");
	    if (aa == 1 && ab == 1) {  
            return 1;  
        }  
        return 0;
    }
}

