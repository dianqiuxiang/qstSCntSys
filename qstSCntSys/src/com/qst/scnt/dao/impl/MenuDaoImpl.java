package com.qst.scnt.dao.impl;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.MenuDao;
import com.qst.scnt.model.Menu;

@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao {

	// mapper.xmlÖÐµÄnamespace  
    private String namespace;
    
	public MenuDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.MenuDaoImpl");
		namespace="com.qst.scnt.dao.impl.MenuDaoImpl";
	}
	
}

