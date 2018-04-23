package com.qst.scnt.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.scnt.dao.BaseDao;
import com.qst.scnt.dao.OrderInfoDao;
import com.qst.scnt.dao.OrderProductInfoDao;
import com.qst.scnt.model.Cost;
import com.qst.scnt.model.OrderInfo;
import com.qst.scnt.model.OrderProductInfo;
import com.qst.scnt.service.OrderInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Service("orderInfoService")
public class OrderInfoServiceImpl extends BaseServiceImpl<OrderInfo> implements OrderInfoService {

	@Resource
	private OrderInfoDao orderInfoDao;
	
	@Resource
	private OrderProductInfoDao orderProductInfoDao;
	
	@Override
	public BaseDao<OrderInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return orderInfoDao;
	}

	@Override
	public EUDataGridResult<Cost> selectByCNameAndCTypeAndDate(Map<String, Object> queryDate, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Cost> list = orderInfoDao.selectByCNameAndCTypeAndDate(queryDate);		
		
        //创建一个返回值对象
        EUDataGridResult<Cost> result = new EUDataGridResult<Cost>();
        result.setRows(list);
        //取记录总条数
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
	}

	@Override
	@Transactional
	public int insertOrderAndOProductInfo(OrderInfo orderInfo) {
		
		int i=orderInfoDao.insert(orderInfo);
		
		/****获取新增的orderInfo的ID****/
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("orderCode",orderInfo.getOrderCode());//指定查询范围		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<OrderInfo> list=orderInfoDao.selectParam(params);
		orderInfo.setId(list.get(0).getId());
		
		int j=0;
		for(OrderProductInfo item:orderInfo.getProducts())
		{
			item.setOrderID(orderInfo.getId());
			BigDecimal amout = new BigDecimal(String.valueOf(item.getAmount()));
			BigDecimal price = new BigDecimal(String.valueOf(item.getPrice()));
			BigDecimal b_totalMoney=amout.multiply(price);
			
			DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数			
			String str_totalMoney = df.format(b_totalMoney); // 先将Object 转化成 String
			BigDecimal totalMoney = new BigDecimal(str_totalMoney); // 再将String 转换成 BigDecimal
			
			item.setTotalMoney(totalMoney);
			item.setIsDelete(0);
			j+=orderProductInfoDao.insert(item);
		}
		
	    if (i == 1 && j == orderInfo.getProducts().size()) {  
            return 1;  
        }  
        return 0;
	}

	@Override
	@Transactional
	public int updateOrderAndOProductInfo(OrderInfo orderInfo) {
		
		int i=orderInfoDao.update(orderInfo);
		
		int j=0;
		if(orderInfo.getUpdateProducts().size()!=0)
		{
			for(OrderProductInfo item:orderInfo.getUpdateProducts())
			{
				BigDecimal amout = new BigDecimal(String.valueOf(item.getAmount()));
				BigDecimal price = new BigDecimal(String.valueOf(item.getPrice()));
				BigDecimal b_totalMoney=amout.multiply(price);
				
				DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数			
				String str_totalMoney = df.format(b_totalMoney); // 先将Object 转化成 String
				BigDecimal totalMoney = new BigDecimal(str_totalMoney); // 再将String 转换成 BigDecimal
				
				item.setTotalMoney(totalMoney);
				j+=orderProductInfoDao.update(item);
			}
		}
		
		if(orderInfo.getInsertProducts().size()!=0)
		{
			for(OrderProductInfo item:orderInfo.getInsertProducts())
			{
				item.setOrderID(orderInfo.getId());
				BigDecimal amout = new BigDecimal(String.valueOf(item.getAmount()));
				BigDecimal price = new BigDecimal(String.valueOf(item.getPrice()));
				BigDecimal b_totalMoney=amout.multiply(price);
				
				DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数			
				String str_totalMoney = df.format(b_totalMoney); // 先将Object 转化成 String
				BigDecimal totalMoney = new BigDecimal(str_totalMoney); // 再将String 转换成 BigDecimal
				
				item.setTotalMoney(totalMoney);
				item.setIsDelete(0);
				j+=orderProductInfoDao.insert(item);
			}
		}
		
		if(orderInfo.getDeleteProducts().size()!=0)
		{
			for(OrderProductInfo item:orderInfo.getDeleteProducts())
			{
				item.setIsDelete(1);
				j+=orderProductInfoDao.update(item);
			}
		}
		
	    if (i == 1 && j == orderInfo.getUpdateProducts().size()+orderInfo.getInsertProducts().size()+orderInfo.getDeleteProducts().size()) {  
            return 1;  
        }  
        return 0;
	}

	@Override
	public 	List<OrderProductInfo> selectOProductByOrderID(OrderProductInfo orderProductInfo) {
		// TODO Auto-generated method stub
		return orderProductInfoDao.selectOProductByOrderID(orderProductInfo);
	}

	@Override
	@Transactional
	public int deleteOrderAndOProduct(OrderInfo orderInfo) {
		int i=orderInfoDao.update(orderInfo);
		
		int j=0;
		
	    Map<String, Object> fieldsMap = new HashMap();
	    fieldsMap.put("isDelete", 1);
	    
	    Map<String, Object> whereMap = new HashMap();
	    whereMap.put("orderID", orderInfo.getId());	 	    
	    
	    Map<String, Object> params = new HashMap();  
	    params.put("fields", fieldsMap);
	    params.put("where", whereMap);
	    j=orderProductInfoDao.updateFields(params);		
			
		
		if (i == 1 && j >=1) {  
            return 1;  
        }  
	        return 0;
		
	}
	
}

