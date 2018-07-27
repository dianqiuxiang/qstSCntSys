package com.qst.scnt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.OrderInfoDao;
import com.qst.scnt.dao.SorcReciPayExesDetailDao;
import com.qst.scnt.model.Cost;
import com.qst.scnt.model.OrderInfo;

@Repository("sorcReciPayExesDetailDao")
public class SorcReciPayExesDetailDaoImpl extends BaseDaoImpl<Object> implements SorcReciPayExesDetailDao {

	// mapper.xml�е�namespace  
    private String namespace;
    
	public SorcReciPayExesDetailDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.SorcReciPayExesDetailDaoImpl");	
		namespace="com.qst.scnt.dao.impl.SorcReciPayExesDetailDaoImpl";
	}

	/**
	 * ǩԼ���Żؿ��������
	 * @param params
	 * @return
	 */
	@Override
	public List<Map> countNewResour(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList(namespace + "." + "countNewResour", params);
	}
	
}

