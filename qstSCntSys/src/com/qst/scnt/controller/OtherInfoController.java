package com.qst.scnt.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qst.scnt.model.EveryMonthOtherInfo;
import com.qst.scnt.service.EveryMonthOtherInfoService;
import com.qst.scnt.utils.EUDataGridResult;

@Controller
@RequestMapping(value="/otherInfo")
public class OtherInfoController extends BaseController {
	
	@Resource
	private EveryMonthOtherInfoService everyMonthOtherInfoService;
	
	@RequestMapping(value="/selectByStartAndEndDate.do")
	@ResponseBody
	public Object selectByStartAndEndDate(String startDate,String endDate,int page,int rows) {
		
		Gson gson = new Gson();	
		startDate=startDate+"-01";
		endDate=endDate+"-01";
		Map<String, Object> queryDate = new HashMap<String, Object>();
		queryDate.put("salesDepartmentID",this.getCurrentUser().getSalesDepartmentID());
		queryDate.put("startDate",startDate);
		queryDate.put("endDate",endDate);
		
		EUDataGridResult<EveryMonthOtherInfo> list=everyMonthOtherInfoService.selectByStartAndEndDate(queryDate,page,rows);
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/addEveryMonthOtherInfo.do")
	@ResponseBody
	public Object addEveryMonthOtherInfo(BigDecimal manageCost,Integer earlyNumber,Integer finalNumber,
										BigDecimal singleExcessAmount,BigDecimal overallExcessAmount,String infoDate) {

		Map<String, Object> queryDate = new HashMap<String, Object>();
		queryDate.put("salesDepartmentID",this.getCurrentUser().getSalesDepartmentID());
		queryDate.put("infoDate",infoDate);
		List<EveryMonthOtherInfo> list=everyMonthOtherInfoService.selectByDate(queryDate);
		String resultStr="";
		if(list.size()==0){
			EveryMonthOtherInfo everyMonthOtherInfo=new EveryMonthOtherInfo();
			
			if(manageCost!=null){
				everyMonthOtherInfo.setManageCost(manageCost);
			}
			else{
				everyMonthOtherInfo.setManageCost(new BigDecimal(0));
			}
			
			if(earlyNumber!=null){
				everyMonthOtherInfo.setEarlyNumber(earlyNumber);
			}
			else{
				everyMonthOtherInfo.setEarlyNumber(0);
			}
			
			if(finalNumber!=null){
				everyMonthOtherInfo.setFinalNumber(finalNumber);
			}
			else{
				everyMonthOtherInfo.setFinalNumber(0);
			}
			
			if(singleExcessAmount!=null){
				everyMonthOtherInfo.setSingleExcessAmount(singleExcessAmount);
			}
			else{
				everyMonthOtherInfo.setSingleExcessAmount(new BigDecimal(0));
			}
			
			if(overallExcessAmount!=null){
				everyMonthOtherInfo.setOverallExcessAmount(overallExcessAmount);
			}
			else{
				everyMonthOtherInfo.setOverallExcessAmount(new BigDecimal(0));
			}
			
			infoDate=infoDate+"-01";
//			java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		 
//			Date date=null;
//			try {
//				date = formatter.parse(infoDate);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			everyMonthOtherInfo.setInfoDate(infoDate);

			everyMonthOtherInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
			everyMonthOtherInfo.setIsDelete(0);
			int result=everyMonthOtherInfoService.insert(everyMonthOtherInfo);
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
		EveryMonthOtherInfo everyMonthOtherInfo=everyMonthOtherInfoService.selectPK(ID);
		return gson.toJson(everyMonthOtherInfo);
	}
	
	@RequestMapping(value="/updateEveryMonthOtherInfo.do")
	@ResponseBody
	public Object updateEveryMonthOtherInfo(int ID,BigDecimal manageCost,Integer earlyNumber,Integer finalNumber,
										BigDecimal singleExcessAmount,BigDecimal overallExcessAmount) {
		
		EveryMonthOtherInfo everyMonthOtherInfo=new EveryMonthOtherInfo();
		everyMonthOtherInfo.setId(ID);
		
		if(manageCost!=null){
			everyMonthOtherInfo.setManageCost(manageCost);
		}
		else{
			everyMonthOtherInfo.setManageCost(new BigDecimal(0));
		}
		
		if(earlyNumber!=null){
			everyMonthOtherInfo.setEarlyNumber(earlyNumber);
		}
		else{
			everyMonthOtherInfo.setEarlyNumber(0);
		}
		
		if(finalNumber!=null){
			everyMonthOtherInfo.setFinalNumber(finalNumber);
		}
		else{
			everyMonthOtherInfo.setFinalNumber(0);
		}
		
		if(singleExcessAmount!=null){
			everyMonthOtherInfo.setSingleExcessAmount(singleExcessAmount);
		}
		else{
			everyMonthOtherInfo.setSingleExcessAmount(new BigDecimal(0));
		}
		
		if(overallExcessAmount!=null){
			everyMonthOtherInfo.setOverallExcessAmount(overallExcessAmount);
		}
		else{
			everyMonthOtherInfo.setOverallExcessAmount(new BigDecimal(0));
		}
		
		int result=everyMonthOtherInfoService.update(everyMonthOtherInfo);
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
	
	@RequestMapping(value="/deleteEveryMonthOtherInfo.do")
	@ResponseBody
	public Object deleteEveryMonthOtherInfo(int ID) {
		
		EveryMonthOtherInfo everyMonthOtherInfo=new EveryMonthOtherInfo();
		everyMonthOtherInfo.setId(ID);
		everyMonthOtherInfo.setIsDelete(1);//"1"代表删除，"0"代表未删除			
		int result=everyMonthOtherInfoService.update(everyMonthOtherInfo);
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
