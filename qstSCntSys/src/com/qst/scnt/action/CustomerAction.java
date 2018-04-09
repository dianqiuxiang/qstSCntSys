package com.qst.scnt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qst.scnt.model.CustomerInfo;
import com.qst.scnt.service.CustomerInfoService;

@Controller
@RequestMapping(value="/customer")
public class CustomerAction extends BaseAction {
	
	@Resource
	private CustomerInfoService customerInfoService;
	
	@RequestMapping(value="/getInfo.do")
	@ResponseBody
	public Object getInfo() {		
		Gson gson = new Gson();

		List<CustomerInfo> list=customerInfoService.select();
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/selectByCNameAndCPhone.do")
	@ResponseBody
	public Object selectByCNameAndCPhone(String customerName,String customerPhone) {
		
		Gson gson = new Gson();		
		CustomerInfo customerInfo=new CustomerInfo();
		customerInfo.setCustomerName(customerName);
		customerInfo.setCustomerPhone(customerPhone);
		customerInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
		List<CustomerInfo> list=customerInfoService.selectParamFlexible(customerInfo);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/addCustomerInfo.do")
	@ResponseBody
	public Object addCustomerInfo(String customerName,String customerPhone,String customerAddress) {

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("customerPhone",customerPhone);//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
		List<CustomerInfo> list=customerInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0){
			CustomerInfo customerInfo=new CustomerInfo();
			customerInfo.setCustomerName(customerName);
			customerInfo.setCustomerPhone(customerPhone);
			customerInfo.setCustomerAddress(customerAddress);
			customerInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
			customerInfo.setIsDelete(0);
			int result=customerInfoService.insert(customerInfo);
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
	
	@RequestMapping(value="/selectByID.do")
	@ResponseBody
	public Object selectByID(int ID) {

		Gson gson = new Gson();
		CustomerInfo customerInfo=customerInfoService.selectPK(ID);
		return gson.toJson(customerInfo);
	}
	
	@RequestMapping(value="/updateCustomerInfo.do")
	@ResponseBody
	public Object updateCustomerInfo(int ID,String customerName,String customerPhone,String customerAddress) {

		CustomerInfo old_customerInfo=customerInfoService.selectPK(ID);
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("customerPhone",customerPhone);//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
		List<CustomerInfo> list=customerInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0||old_customerInfo.getCustomerPhone().equals(customerPhone)){
			CustomerInfo customerInfo=new CustomerInfo();
			customerInfo.setId(ID);
			customerInfo.setCustomerName(customerName);
			customerInfo.setCustomerPhone(customerPhone);
			customerInfo.setCustomerAddress(customerAddress);
			customerInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
			customerInfo.setIsDelete(0);
			int result=customerInfoService.update(customerInfo);
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
	
	@RequestMapping(value="/deleteCustomerInfo.do")
	@ResponseBody
	public Object deleteCustomerInfo(int ID) {

		String resultStr="";
		CustomerInfo customerInfo=new CustomerInfo();
		customerInfo.setId(ID);		
		customerInfo.setIsDelete(1);//"1"����ɾ����"0"����δɾ��
		int result=customerInfoService.update(customerInfo);
		if(result>0)
		{
			resultStr="[{\"result\":\"Success\"}]";
		}
		else
		{
			resultStr="[{\"result\":\"Failed\"}]";
		}
		
		return resultStr;
	}
}
