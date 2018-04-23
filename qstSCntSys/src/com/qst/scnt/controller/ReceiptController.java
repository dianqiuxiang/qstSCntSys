package com.qst.scnt.controller;

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
import com.qst.scnt.model.CustomerInfo;
import com.qst.scnt.model.OrderInfo;
import com.qst.scnt.model.ReceiptInfo;
import com.qst.scnt.service.ReceiptInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/receipt")
public class ReceiptController extends BaseController {
	
	@Resource
	private ReceiptInfoService receiptInfoServicce;
	
	/**
	 * 查询所有结果
	 * @return
	 */
	@RequestMapping(value="/getInfo.do")
	@ResponseBody
	public Object getInfo() {
		Gson gson = new Gson();
		List<ReceiptInfo> receiptInfoList=receiptInfoServicce.select();
		return gson.toJson(receiptInfoList);
	}

	/**
	 * 查询所有结果
	 * @return
	 */
	@RequestMapping(value="/selectByCodeAndRMemberAndDate.do")
	@ResponseBody
	public Object selectByCodeAndRMemberAndDate(String orderCode,String receiptMember,String startDate,String endDate,int page,int rows) {
		Gson gson = new Gson();
		Map<String, Object> queryDate = new HashMap<String, Object>();
		
		queryDate.put("orderCode",orderCode);
		queryDate.put("receiptMember",receiptMember);
		queryDate.put("startDate",startDate);
		queryDate.put("endDate",endDate);
		
		queryDate.put("salesDepartmentID",this.getCurrentUser().getSalesDepartmentID());
		EUDataGridResult<ReceiptInfo> receiptInfoList=receiptInfoServicce.selectByCodeAndRMemberAndDate(queryDate,page,rows);
		return gson.toJson(receiptInfoList);
	}
	
	/**
	 * 新增收款信息
	 * @return
	 */
	@RequestMapping(value="/addReceiptInfo.do")
	@ResponseBody
	public Object addReceiptInfo(@RequestBody  ReceiptInfo receiptInfo) {
		
		receiptInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
		receiptInfo.setIsDelete(0);
		int result=receiptInfoServicce.insert(receiptInfo);
		String resultStr="";
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

	/**
	 * 根据ID查询收款信息
	 * @return
	 */
	@RequestMapping(value="/selectByID.do")
	@ResponseBody
	public Object selectByID(int ID) {
		Gson gson = new Gson();
		ReceiptInfo receiptInfo=receiptInfoServicce.selectByID(ID);
		return gson.toJson(receiptInfo);
	}
	
	/**
	 * 根据ID修改收款信息
	 * @return
	 */
	@RequestMapping(value="/updateReceiptInfo.do")
	@ResponseBody
	public Object updateReceiptInfo(@RequestBody  ReceiptInfo receiptInfo) {
		int result=receiptInfoServicce.update(receiptInfo);
		String resultStr="";
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
	
	/**
	 * 根据ID删除收款信息
	 * @return
	 */
	@RequestMapping(value="/deleteReceiptInfo.do")
	@ResponseBody
	public Object deleteReceiptInfo(int ID) {
		ReceiptInfo receiptInfo=new ReceiptInfo();
		receiptInfo.setId(ID);		
		receiptInfo.setIsDelete(1);//"1"代表删除，"0"代表未删除
		int result=receiptInfoServicce.update(receiptInfo);
		String resultStr="";
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
