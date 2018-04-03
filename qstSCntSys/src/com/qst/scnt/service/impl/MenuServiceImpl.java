package com.qst.scnt.service.impl;

import javax.annotation.Resource;

import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.MenuDao;
import com.qst.scnt.model.Menu;
import com.qst.scnt.service.MenuService;

public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

	@Resource
	private MenuDao menuDao;
	
	@Override
	public BaseDao<Menu> getBaseDao() {
		// TODO Auto-generated method stub
		return menuDao;
	}
	
}

