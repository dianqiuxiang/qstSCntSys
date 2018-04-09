package com.qst.scnt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.model.UserInfo;
import com.qst.scnt.service.BaseService;
import com.qst.scnt.service.UserInfoService;
import com.qst.scnt.utils.EUDataGridResult;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	/** 
     * 由业务类实现 
     * @return 
     */  
    public abstract BaseDao<T> getBaseDao();  
    
	@Override
	public int insert(T entity) {
		// TODO Auto-generated method stub
		return getBaseDao().insert(entity);
	}

	@Override
	public int insertBatch(List<T> list) {
		// TODO Auto-generated method stub
		return getBaseDao().insertBatch(list);
	}
	
	@Override
	public int update(T entity) {
		// TODO Auto-generated method stub
		return getBaseDao().update(entity);
	}

	@Override
	public int updateFields(Map param) {
		// TODO Auto-generated method stub
		return getBaseDao().updateFields(param);
	}
	
	@Override
	@Transactional
	public int updateBatch(List<T> list) {
		// TODO Auto-generated method stub
		return getBaseDao().updateBatch(list);
	}

	@Override
	public int delete(int primaryKey) {
		// TODO Auto-generated method stub
		return getBaseDao().delete(primaryKey);
	}

	@Override
	public int deleteParam(Map param) {
		// TODO Auto-generated method stub
		return getBaseDao().deleteParam(param);
	}
	
	@Override
	@Transactional
	public int deleteBatch(List<Integer> list) {
		// TODO Auto-generated method stub
		return getBaseDao().deleteBatch(list);
	}

	@Override
	public int truncate() {
		// TODO Auto-generated method stub
		return getBaseDao().truncate();
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return getBaseDao().count();
	}

	@Override
	public int count(Object param) {
		// TODO Auto-generated method stub
		return getBaseDao().count(param);
	}

	@Override
	public List<T> select() {
		// TODO Auto-generated method stub
		return getBaseDao().select();
	}

	@Override
	public T selectPK(int primaryKey) {
		// TODO Auto-generated method stub
		return getBaseDao().selectPK(primaryKey);
	}

	@Override
	public List<T> selectParam(Map param) {
		// TODO Auto-generated method stub
		return getBaseDao().selectParam(param);
	}

	@Override
	public EUDataGridResult<T> selectParamFlexible(T entity,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
//		List<T> list=getBaseDao().selectParamFlexible(entity); 
//		PageInfo<T> pageInfo=new PageInfo<T>(list);
		
		List<T> list = getBaseDao().selectParamFlexible(entity);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<T> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
	}
	
	@Override
	public PageInfo<T> selectPagination(Map param,int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<T> list=getBaseDao().selectParam(param); 
		PageInfo<T> pageInfo=new PageInfo<T>(list);
		return pageInfo;
	}
	
}
