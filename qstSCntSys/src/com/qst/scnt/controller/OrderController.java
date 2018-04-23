package com.qst.scnt.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qst.scnt.model.Cost;
import com.qst.scnt.model.OrderInfo;
import com.qst.scnt.model.OrderProductInfo;
import com.qst.scnt.model.ProductInfo;
import com.qst.scnt.service.OrderInfoService;
import com.qst.scnt.service.OrderProductInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/order")
public class OrderController extends BaseController {
	@Resource
	private OrderInfoService orderInfoService;
	
	@Resource
	private OrderProductInfoService orderProductInfoService;
	
	/**
	 * ��ѯ���н��
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
	 * ����ID��ѯ
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
	
	/**
	 * ���ݶ������ģ����ѯ�˲����¶����еĶ������
	 * @param ID
	 * @return
	 */
	@RequestMapping(value="/selectOrderCode.do")
	@ResponseBody
	public Object selectOrderCode(String orderCode,int page,int rows) {
		Gson gson=new Gson();
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setOrderCode(orderCode);
		orderInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
		
		EUDataGridResult<OrderInfo> list=orderInfoService.selectParamFlexible(orderInfo,page,rows);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	/**
	 * ���ݹ˿�����ģ����ѯ�˲����¶����еĹ˿���Ϣ
	 * @param ID
	 * @return
	 */
	@RequestMapping(value="/selectOrderCustomerName.do")
	@ResponseBody
	public Object selectOrderCustomerName(String customerName,int page,int rows) {
		Gson gson=new Gson();
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setCustomerName(customerName);
		orderInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
		
		EUDataGridResult<OrderInfo> list=orderInfoService.selectParamFlexible(orderInfo,page,rows);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	/**
	 * ���ݹ˿��������˿������ʼʱ��ģ����ѯ�˲����µĶ�����Ϣ
	 * @param customerName
	 * @param customerType
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/selectByCNameAndCTypeAndDate.do")
	@ResponseBody
	public Object selectByCNameAndCTypeAndDate(String customerName,String customerType,String startDate,String endDate,int page,int rows) {
		Gson gson = new Gson();		
		Map<String, Object> queryDate = new HashMap<String, Object>();
		
		queryDate.put("customerType",customerType);
		queryDate.put("customerName",customerName);
		queryDate.put("startDate",startDate);
		queryDate.put("endDate",endDate);
		
		queryDate.put("salesDepartmentID",this.getCurrentUser().getSalesDepartmentID());
				
		EUDataGridResult<Cost> list=orderInfoService.selectByCNameAndCTypeAndDate(queryDate,page,rows);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	/**
	 * ���ݹ˿��������˿������ʼʱ��ģ����ѯ�˲����µĶ�����Ϣ
	 * @param customerName
	 * @param customerType
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/selectOProductByOrderID.do")
	@ResponseBody
	public Object selectOProductByOrderID(int orderID) {
		Gson gson = new Gson();		
		OrderProductInfo orderProductInfo=new OrderProductInfo();
		orderProductInfo.setOrderID(orderID);
		
				
		List<OrderProductInfo> list=orderInfoService.selectOProductByOrderID(orderProductInfo);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	
	/**
	 * ����������һ��������Ϣ��������orderProductInfo����Ӵ˶����Ĳ�Ʒ��Ϣ�������������
	 * 
	 */	
	@RequestMapping(value="/insertOrderAndOProductInfo.do")
	@ResponseBody
	public Object insertOrderAndOProductInfo(@RequestBody  OrderInfo orderInfo) {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("orderCode",orderInfo.getOrderCode());//ָ����ѯ��Χ
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
		List<OrderInfo> list=orderInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0){
			
			orderInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
			orderInfo.setIsDelete(0);
			int result=orderInfoService.insertOrderAndOProductInfo(orderInfo);
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
	 *  ���ݶ������޸Ĵ˶�����Ϣ
	 * @param orderInfo
	 * @return
	 */
	@RequestMapping(value="/updateOrderAndOProductInfo.do")
	@ResponseBody
	public Object updateOrderAndOProductInfo(@RequestBody  OrderInfo orderInfo) {
		OrderInfo old_orderInfo=orderInfoService.selectPK(orderInfo.getId());
		Map<String,Object> whereMap=new HashMap<String, Object>();
		whereMap.put("orderCode", orderInfo.getOrderCode());
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
		List<OrderInfo> list=orderInfoService.selectParam(params);
		String resultStr="";
		//System.out.println(orderInfo.getProducts());
		System.out.println(orderInfo.getUpdateProducts());
		
		if(list.size()==0||old_orderInfo.getOrderCode().equals(orderInfo.getOrderCode())) {
			
			int result=orderInfoService.updateOrderAndOProductInfo(orderInfo);
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
	 * ����IDɾ��(ʵ�������޸�isDelete�ֶ�)
	 * @param ID
	 * @return
	 */
	@RequestMapping(value="/deleteOrderAndOProduct.do")
	@ResponseBody
	public Object deleteOrderAndOProduct(int ID) {
		String resultStr="";
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setId(ID);
		orderInfo.setIsDelete(1);//ɾ������
		
		int result=orderInfoService.deleteOrderAndOProduct(orderInfo);
		
		if(result>0) {
			resultStr="[{\"result\":\"Success\"}]";
		}else {
			resultStr="[{\"result\":\"Failed\"}]";
		}
		return resultStr;
		
	}
	
	
}
