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
import com.qst.scnt.model.CustomerInfo;
import com.qst.scnt.model.Menu;
import com.qst.scnt.model.OrderInfo;
import com.qst.scnt.model.OrderProductInfo;
import com.qst.scnt.model.ProductInfo;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.service.CustomerInfoService;
import com.qst.scnt.service.OrderInfoService;
import com.qst.scnt.service.OrderProductInfoService;
import com.qst.scnt.service.ProductInfoService;
import com.qst.scnt.service.SalesDepartmentInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/order")
public class OrderController extends BaseController {
	@Resource
	private OrderInfoService orderInfoService;
	
	@Resource
	private OrderProductInfoService orderProductInfoService;
	
	@Resource
	private CustomerInfoService customerInfoService;
	
	@Resource
	private ProductInfoService productInfoService;
	
	@Resource
	private SalesDepartmentInfoService salesDepartmentInfoService;
	
	/**
	 * ��ѯ���в�����Ϣ
	 * @return
	 */
	@RequestMapping(value="/getSalesDept.do")
	@ResponseBody
	public Object getSalesDept(){
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("parentID",0);//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
		
		List<SalesDepartmentInfo> list=salesDepartmentInfoService.selectParam(params);
		
		String returnJson="[";
		int i=0;
		for(SalesDepartmentInfo item:list){
			i++;
			returnJson+="{";
			returnJson+="\"id\":"+ item.getId() +",";
			returnJson+="\"text\":\""+ item.getSalesDepartmentName() +"\",";
			returnJson+="\"children\":[";
			Map<String, Object> fieldMap = new HashMap<String, Object>();
			fieldMap.put("parentID",item.getId());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
			
			Map<String, Object> queryParams = new HashMap<String, Object>();  
			queryParams.put("where", fieldMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
			
			List<SalesDepartmentInfo> childrenList=salesDepartmentInfoService.selectParam(queryParams);
			
			int j=0;
			for(SalesDepartmentInfo childNode:childrenList){
				j++;
				returnJson+="{";
				returnJson+="\"id\":"+ childNode.getId() +",";
				returnJson+="\"text\":\""+ childNode.getSalesDepartmentName() +"\",";
				returnJson+="\"children\":[";
				
				Map<String, Object> fieldMap2 = new HashMap<String, Object>();
				fieldMap2.put("parentID",childNode.getId());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
				
				Map<String, Object> queryParams2 = new HashMap<String, Object>();  
				queryParams2.put("where", fieldMap2); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
				
				List<SalesDepartmentInfo> childrenList2=salesDepartmentInfoService.selectParam(queryParams2);
				
				int k=0;
				for(SalesDepartmentInfo childNode2:childrenList2){
					k++;
					returnJson+="{";
					returnJson+="\"id\":"+ childNode2.getId() +",";
					returnJson+="\"text\":\""+ childNode2.getSalesDepartmentName() +"\"";
					
					
					if(k==childrenList2.size()){
						returnJson+="}";
					}
					else{
						returnJson+="},";
					}
				}
				returnJson+="]";
				
				if(j==childrenList.size()){
					returnJson+="}";
				}
				else{
					returnJson+="},";
				}
			}
			returnJson+="]";
			
			if(i==list.size()){
				returnJson+="}";
			}
			else{
				returnJson+="},";
			}
		}
		returnJson+="]";
		System.out.println(returnJson);
		return returnJson;
	}
	
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
	public Object selectByID(int id) {
		Gson gson=new Gson();
		OrderInfo orderInfo=orderInfoService.selectPK(id);
		
		OrderProductInfo orderProductInfo=new OrderProductInfo();
		if(orderInfo!=null)
		{
			orderProductInfo.setOrderID(orderInfo.getId());
			orderInfo.setProducts(orderProductInfoService.selectOProductByOrderID(orderProductInfo));
		}
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
	 * ���ݲ�Ʒ����ģ����ѯ��Ʒ��Ϣ
	 * @param ID
	 * @return
	 */
	@RequestMapping(value="/selectByPName.do")
	@ResponseBody
	public Object selectByPName(String productName,int page,int rows) {
		
		Gson gson = new Gson();		
		ProductInfo productInfo=new ProductInfo();
		
		if(productName==null||productName.equals(""))
		{
			productInfo.setProductName(null);
		}
		else
		{ 
			productInfo.setProductName(productName);
		}
		
		//productInfo.setProductName(productName);
		
		EUDataGridResult<ProductInfo> list=productInfoService.selectParamFlexible(productInfo,page,rows);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	/**
	 * ��ѯ��Ʒ��Ϣ
	 * @param ID
	 * @return
	 */
	@RequestMapping(value="/selectProduct.do")
	@ResponseBody
	public Object selectProduct() {
		
		Gson gson = new Gson();		
//		ProductInfo productInfo=new ProductInfo();
//		productInfo.setProductName(productName);
		
		//EUDataGridResult<ProductInfo> list=
		List<ProductInfo> list=productInfoService.select();
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
	@RequestMapping(value="/selectByOCodeandCNameAndDate.do")
	@ResponseBody
	public Object selectByOCodeandCNameAndDate(String customerName,String orderCode,Integer salesDepartmentID,String startDate,String endDate,int page,int rows) {
		Gson gson = new Gson();		
		Map<String, Object> queryDate = new HashMap<String, Object>();
		
		//queryDate.put("orderCode",orderCode);
		
		if(orderCode==null||orderCode.equals(""))
		{
			queryDate.put("orderCode",null);
		}
		else
		{ 
			queryDate.put("orderCode",orderCode);
		}
		
		//queryDate.put("customerName",customerName);
		
		if(customerName==null||customerName.equals(""))
		{
			queryDate.put("customerName",null);
		}
		else
		{ 
			queryDate.put("customerName",customerName);
		}
		
		if(startDate==null||startDate.equals(""))
		{
			queryDate.put("startDate",null);
		}
		else
		{ 
			queryDate.put("startDate",startDate);
		}
		
		if(endDate==null||endDate.equals(""))
		{
			queryDate.put("endDate",null);
		}
		else
		{ 
			queryDate.put("endDate",endDate);
		}
		
		queryDate.put("salesDepartmentID",salesDepartmentID);
				
		EUDataGridResult<OrderInfo> list=orderInfoService.selectByOCodeandCNameAndDate(queryDate,page,rows);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	/**
	 * ���ݶ���ID��ѯ�˶����Ĳ�Ʒ��Ϣ
	 * @param orderID
	 * @return
	 */
	@RequestMapping(value="/selectOProductByOrderID.do")
	@ResponseBody
	public Object selectOProductByOrderID(int orderID) {
		Gson gson = new Gson();		
		OrderProductInfo orderProductInfo=new OrderProductInfo();
		orderProductInfo.setOrderID(orderID);		
				
		List<OrderProductInfo> list=orderProductInfoService.selectOProductByOrderID(orderProductInfo);
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setProducts(list);
		System.out.println(gson.toJson(orderInfo));
		return gson.toJson(orderInfo);
	}
	
	/**
	 * ���ݹ˿�����ģ����ѯ�˲����¶����еĹ˿���Ϣ
	 * @param ID
	 * @return
	 */
	@RequestMapping(value="/selectCustomerInfo.do")
	@ResponseBody
	public Object selectCustomerInfo(Integer salesDepartmentID,String customerName,int page,int rows) {
		Gson gson=new Gson();
		CustomerInfo customerInfo=new CustomerInfo();
		
		if(customerName==null||customerName.equals(""))
		{
			customerInfo.setCustomerName(null);
		}
		else
		{ 
			customerInfo.setCustomerName(customerName);
		}
		
		//customerInfo.setCustomerName(customerName);
		customerInfo.setSalesDepartmentID(salesDepartmentID);
		
		EUDataGridResult<CustomerInfo> list=customerInfoService.selectParamFlexible(customerInfo,page,rows);
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
			
			//orderInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
			orderInfo.setIsDelete(0);
			int result=orderInfoService.insertOrderAndOProductInfo(orderInfo);
			if(result>0)
			{
				resultStr="{\"result\":\"Success\"}";
			}
			else
			{
				resultStr="{\"result\":\"Failed\"}";
			}
		}
		else
		{
			resultStr="{\"result\":\"isExist\"}";
		}
		return resultStr;
	}
	
	
	
	/**
	 *  ���ݶ���ID�޸Ĵ˶�����Ϣ
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
				resultStr="{\"result\":\"Success\"}";
			}
			else
			{
				resultStr="{\"result\":\"Failed\"}";
			}			
		}else
		{
			resultStr="{\"result\":\"isExist\"}";
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
	public Object deleteOrderAndOProduct(int id) {
		String resultStr="";
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setId(id);
		orderInfo.setIsDelete(1);//ɾ������
		
		int result=orderInfoService.deleteOrderAndOProduct(orderInfo);
		
		if(result>0) {
			resultStr="{\"result\":\"Success\"}";
		}else {
			resultStr="{\"result\":\"Failed\"}";
		}
		return resultStr;
		
	}
	
	
}
