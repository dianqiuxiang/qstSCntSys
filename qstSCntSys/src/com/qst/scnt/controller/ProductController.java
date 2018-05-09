package com.qst.scnt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qst.scnt.model.*;
import com.qst.scnt.service.ProductInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/product")
public class ProductController extends BaseController {

	@Resource
	private ProductInfoService productInfoService;
	
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
		
		
		
		EUDataGridResult<ProductInfo> list=productInfoService.selectParamFlexible(productInfo,page,rows);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/addProductInfo.do")
	@ResponseBody
	public Object addProductInfo(@RequestBody  ProductInfo productInfo) {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("productName",productInfo.getProductName());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
		List<ProductInfo> list=productInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0){

			productInfo.setIsDelete(0);
			
			int result=productInfoService.insert(productInfo);
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
	
	@RequestMapping(value="/selectByID.do")
	@ResponseBody
	public Object selectByID(int id) {
		
		Gson gson = new Gson();		
		ProductInfo productInfo=productInfoService.selectPK(id);
		System.out.println(gson.toJson(productInfo));
		return gson.toJson(productInfo);
	}
	
	@RequestMapping(value="/updateProductInfo.do")
	@ResponseBody
	public Object updateProductInfo(@RequestBody  ProductInfo productInfo) {
		
		ProductInfo old_productInfo=productInfoService.selectPK(productInfo.getId());
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("productName",productInfo.getProductName());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µĹ˿���Ϣ	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
		List<ProductInfo> list=productInfoService.selectParam(params);
		
		String resultStr="";
		if(list.size()==0||old_productInfo.getProductName().equals(productInfo.getProductName())){
			
			int result=productInfoService.update(productInfo);
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
	
	@RequestMapping(value="/deleteProductInfo.do")
	@ResponseBody
	public Object deleteProductInfo(int id) {

		String resultStr="";
		ProductInfo productInfo=new ProductInfo();
		productInfo.setId(id);		
		productInfo.setIsDelete(1);//"1"����ɾ����"0"����δɾ��
		int result=productInfoService.update(productInfo);
		if(result>0)
		{
			resultStr="{\"result\":\"Success\"}";
		}
		else
		{
			resultStr="{\"result\":\"Failed\"}";
		}
		
		return resultStr;
	}
}
