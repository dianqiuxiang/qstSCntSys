package com.qst.scnt.dao.impl;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.UserInfoDao;
import com.qst.scnt.model.UserInfo;

@Repository("userInfoDao")
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo> implements UserInfoDao {

	// mapper.xmlÖÐµÄnamespace  
    private String namespace;
    
	public UserInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.UserInfoDaoImpl");
		namespace="com.qst.scnt.dao.impl.UserInfoDaoImpl";
	}
	
}

