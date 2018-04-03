package com.qst.scnt.dao.impl;

import com.qst.scnt.dao.UserInfoDao;
import com.qst.scnt.model.UserInfo;

public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo> implements UserInfoDao {

	public UserInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.UserInfoDaoImpl");		
	}
	
}

