package com.qst.scnt.dao;

import org.mybatis.spring.annotation.MapperScan;
import com.qst.scnt.model.UserInfo;
/**
 * UserInfo	ÓÃ»§DAO
 * Wed Dec 27 15:51:34 CST 2017
 */ 

public interface UserInfoDao extends BaseDao<UserInfo> {
	
	public UserInfo login(UserInfo userInfo);
}

