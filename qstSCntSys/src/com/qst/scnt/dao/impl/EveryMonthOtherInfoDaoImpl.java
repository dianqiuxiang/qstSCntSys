package com.qst.scnt.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qst.scnt.dao.EveryMonthOtherInfoDao;
import com.qst.scnt.model.EveryMonthOtherInfo;
import com.qst.scnt.utils.EUDataGridResult;

@Repository("everyMonthOtherInfoDao")
public class EveryMonthOtherInfoDaoImpl extends BaseDaoImpl<EveryMonthOtherInfo> implements EveryMonthOtherInfoDao {

	// mapper.xmlÖÐµÄnamespace  
    private String namespace;
    
	public EveryMonthOtherInfoDaoImpl(){
		super.setNamespace("com.qst.scnt.dao.impl.EveryMonthOtherInfoDaoImpl");
		namespace="com.qst.scnt.dao.impl.EveryMonthOtherInfoDaoImpl";
	}
	@Override
	public List<EveryMonthOtherInfo> selectByStartAndEndDate(Map<String, Object> queryDate)
	{
		try {  
            return this.getSqlSession().selectList(namespace + "." + "selectByStartAndEndDate",queryDate);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}
	
	@Override
	public List<EveryMonthOtherInfo> selectByDate(Map<String, Object> infoDate)
	{
		try {  
            return this.getSqlSession().selectList(namespace + "." + "selectByDate",infoDate);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}
}

