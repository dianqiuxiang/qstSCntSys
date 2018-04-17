package com.qst.scnt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qst.scnt.service.CostService;
import com.qst.scnt.service.ParameterInfoService;
import com.qst.scnt.service.ReceiptInfoService;

@Controller
@RequestMapping(value="/reportExport")
public class ReportExportController extends BaseController {

	@Resource
	private ParameterInfoService parameterInfoService;
	
	@Resource
	private CostService costService;
	
	@Resource
	private ReceiptInfoService receiptInfoService;
	
//	public 
//	
//	Map<String, Object> whereMap = new HashMap<String, Object>();
//	whereMap.put("ParameterName", "profitParam");
//	
//	
//	Map<String, Object> params = new HashMap<String, Object>();  
//	params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
//	List<ParameterInfo> list=parameterInfoService.selectParam(params);
	
}
