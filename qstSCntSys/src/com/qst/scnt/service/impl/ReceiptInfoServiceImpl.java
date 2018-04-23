package com.qst.scnt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.ReceiptInfoDao;
import com.qst.scnt.model.ReceiptInfo;
import com.qst.scnt.service.ReceiptInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Service("receiptInfoService")
public class ReceiptInfoServiceImpl extends BaseServiceImpl<ReceiptInfo> implements ReceiptInfoService {

	@Resource
	private ReceiptInfoDao receiptInfoDao;
	
	@Override
	public BaseDao<ReceiptInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return receiptInfoDao;
	}

	@Override
	public EUDataGridResult<ReceiptInfo> selectByCodeAndRMemberAndDate(Map<String, Object> queryDate, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
//		List<T> list=getBaseDao().selectParamFlexible(entity); 
//		PageInfo<T> pageInfo=new PageInfo<T>(list);
		
		List<ReceiptInfo> list = receiptInfoDao.selectByCodeAndRMemberAndDate(queryDate);
        //创建一个返回值对象
        EUDataGridResult<ReceiptInfo>  result = new EUDataGridResult<ReceiptInfo> ();
        result.setRows(list);
        //取记录总条数
        PageInfo<ReceiptInfo> pageInfo = new PageInfo<ReceiptInfo>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
	}

	@Override
	public ReceiptInfo selectByID(int ID) {
		// TODO Auto-generated method stub
		return receiptInfoDao.selectByID(ID);
	}
	

}

