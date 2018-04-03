package com.qst.scnt.dao.impl;

import com.qst.scnt.dao.MenuDao;
import com.qst.scnt.model.Menu;

public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao {

	public MenuDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.MenuDaoImpl");		
	}
	
}

