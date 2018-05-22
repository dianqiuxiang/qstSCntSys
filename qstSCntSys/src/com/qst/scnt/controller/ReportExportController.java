package com.qst.scnt.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qst.scnt.model.ParameterInfo;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.model.UserInfo;
import com.qst.scnt.service.CostService;
import com.qst.scnt.service.ParameterInfoService;
import com.qst.scnt.service.ReceiptInfoService;
import com.qst.scnt.service.SalesDepartmentInfoService;
import com.qst.scnt.service.UserInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/reportExport")
public class ReportExportController extends BaseController {

	@Resource
	private ParameterInfoService parameterInfoService;	
	@Resource
	private CostService costService;	
	@Resource
	private ReceiptInfoService receiptInfoService;	
	@Resource
	private UserInfoService userInfoService;	
	@Resource
	private SalesDepartmentInfoService salesDepartmentInfoService;

	
	private BigDecimal getProfitParam()
	{
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("ParameterName", "profitParam");		
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<ParameterInfo> list=parameterInfoService.selectParam(params);
		if(list.size()!=0)
		{
			return new BigDecimal(list.get(0).getValue());
		}
		else
		{
			return null;
		}		
	}
	
	@RequestMapping(value="/getRMangerReport.do")
	@ResponseBody
	public Object getRMangerReport(String rManagerRportMonth) {
		
		
		Gson gson = new Gson();		
		UserInfo userInfo=this.getCurrentUser();
		//userInfo.setUserName(userName);
		String resultStr="";
		if(rManagerRportMonth==null||rManagerRportMonth.equals(""))
		{
			resultStr="{\"result\":\"NoMonthTime\"}";
		}
		else
		{ 
			
		}
		
		
		//System.out.println(gson.toJson(list));
		return null;//gson.toJson(list);
	}
	
	public List<UserInfo> getLastLevelUser(UserInfo userInfo)
	{
		SalesDepartmentInfo sdept=salesDepartmentInfoService.selectPK(userInfo.getSalesDepartmentID());
		if(sdept.getLevel()==1)
		{
			
		}
		
		return null;
	}
}
