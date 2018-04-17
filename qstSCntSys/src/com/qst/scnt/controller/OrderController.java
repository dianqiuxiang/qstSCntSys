package com.qst.scnt.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qst.scnt.model.OrderInfo;
import com.qst.scnt.service.OrderInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/order")
public class OrderController extends BaseController {
	@Resource
	private OrderInfoService orderInfoService;
	
	/**
	 * 查询所有结果
	 * @return
	 */
	@RequestMapping(value="/getInfo.do")
	@ResponseBody
	public Object getInfo() {
		Gson gson = new Gson();
		List<OrderInfo> orderInfoList=orderInfoService.select();
		return gson.toJson(orderInfoList);
	}
	/**
	 * 根据ID查询
	 * @param ID
	 * @return
	 */
	@RequestMapping(value="/selectByID.do")
	@ResponseBody
	public Object selectByID(int ID) {
		Gson gson=new Gson();
		OrderInfo orderInfo=orderInfoService.selectPK(ID);
		return gson.toJson(orderInfo);
	}
	
	
	@RequestMapping(value="/selectByParams.do")
	@ResponseBody
	public Object selectByParams(String orderCode,String customerType,String orderDate,int page,int rows) {
		Gson gson = new Gson();		
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setOrderCode(orderCode);
		orderInfo.setCustomerType(customerType);
		orderInfo.setOrderDate(orderDate);
		orderInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
		
		EUDataGridResult<OrderInfo> list=orderInfoService.selectParamFlexible(orderInfo,page,rows);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	
	/**
	 * 新增
	 * @param orderCode
	 * @param customerType
	 * @param customerPhone
	 * @param customerName
	 * @param customerAddress
	 * @param orderDate
	 * @param orderAmount
	 * @param healthMember
	 * @param customerSign
	 * @param salesDepartmentID
	 * @return
	 */
	@RequestMapping(value="/addOrderInfo.do")
	@ResponseBody
	public Object addOrderInfo(String orderCode,String customerType,String customerPhone,String customerName,
			String customerAddress,String orderDate,BigDecimal orderAmount,String healthMember,String customerSign,int salesDepartmentID) {
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("orderCode",orderCode);//指定查询范围
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<OrderInfo> list=orderInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0){
			OrderInfo orderInfo=new OrderInfo();
			orderInfo.setOrderCode(orderCode);
			orderInfo.setCustomerType(customerType);
			orderInfo.setCustomerPhone(customerPhone);
			orderInfo.setCustomerName(customerName);
			orderInfo.setCustomerAddress(customerAddress);
			orderInfo.setOrderDate(orderDate);
			orderInfo.setOrderAmount(orderAmount);
			orderInfo.setHealthMember(healthMember);
			orderInfo.setCustomerSign(customerSign);
			orderInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
			orderInfo.setIsDelete(0);
			int result=orderInfoService.insert(orderInfo);
			if(result>0)
			{
				resultStr="[{\"result\":\"Success\"}]";
			}
			else
			{
				resultStr="[{\"result\":\"Failed\"}]";
			}
		}
		else
		{
			resultStr="[{\"result\":\"isExist\"}]";
		}
		return resultStr;
	}
	
	
	/**
	 * 根据订单号修改
	 * @param ID
	 * @param orderCode
	 * @param customerType
	 * @param customerPhone
	 * @param customerName
	 * @param customerAddress
	 * @param orderDate
	 * @param orderAmount
	 * @param healthMember
	 * @param customerSign
	 * @param salesDepartmentID
	 * @return
	 */
	@RequestMapping(value="/updateOrderInfo.do")
	@ResponseBody
	public Object updateOrderInfo(int ID,String orderCode,String customerType,String customerPhone,String customerName,
			String customerAddress,String orderDate,BigDecimal orderAmount,String healthMember,String customerSign,int salesDepartmentID) {
		OrderInfo old_orderInfo=orderInfoService.selectPK(ID);
		Map<String,Object> whereMap=new HashMap<String, Object>();
		whereMap.put("orderCode", orderCode);
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<OrderInfo> list=orderInfoService.selectParam(params);
		String resultStr="";
		if(list.size()==0||old_orderInfo.getOrderCode().equals(orderCode)) {
			OrderInfo orderInfo=new OrderInfo();
			orderInfo.setId(ID);
			orderInfo.setOrderCode(orderCode);
			orderInfo.setCustomerType(customerType);
			orderInfo.setCustomerPhone(customerPhone);
			orderInfo.setCustomerName(customerName);
			orderInfo.setCustomerAddress(customerAddress);
			orderInfo.setOrderDate(orderDate);
			orderInfo.setOrderAmount(orderAmount);
			orderInfo.setHealthMember(healthMember);
			orderInfo.setCustomerSign(customerSign);
			orderInfo.setSalesDepartmentID(salesDepartmentID);
			int result=orderInfoService.update(orderInfo);
			if(result>0)
			{
				resultStr="[{\"result\":\"Success\"}]";
			}
			else
			{
				resultStr="[{\"result\":\"Failed\"}]";
			}			
		}else
		{
			resultStr="[{\"result\":\"isExist\"}]";
		}
		return resultStr;
		
		
	}
	
	
	
	/**
	 * 根据ID删除(实际上是修改isDelete字段)
	 * @param ID
	 * @return
	 */
	@RequestMapping(value="/deleteOrderInfo.do")
	@ResponseBody
	public Object deleteOrderInfo(int ID) {
		String resultStr="";
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setId(ID);
		orderInfo.setIsDelete(1);
		int result=orderInfoService.update(orderInfo);
		
		if(result>0) {
			resultStr="[{\"result\":\"Success\"}]";
		}else {
			resultStr="[{\"result\":\"Failed\"}]";
		}
		return resultStr;
		
	}
	
	
}
