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
import org.springframework.web.bind.annotation.RequestBody;
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
	
	/**
	 * 根据起始时间查询此部门下月报信息
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/selectByStartAndEndDate.do")
	@ResponseBody
	public Object selectByStartAndEndDate(String startDate,String endDate,int page,int rows) {
		
		Gson gson = new Gson();	
		Map<String, Object> queryDate = new HashMap<String, Object>();
		
		if(startDate==null||startDate.equals(""))
		{
			queryDate.put("startDate",null);
		}
		else
		{ 
			queryDate.put("endDate",startDate+"-01");
		}
		
		if(endDate==null||endDate.equals(""))
		{
			queryDate.put("endDate",null);
		}
		else
		{ 
			queryDate.put("endDate",endDate+"-01");
		}
		
		//startDate=;
		//endDate=;

		queryDate.put("salesDepartmentID",this.getCurrentUser().getSalesDepartmentID());
		//queryDate.put("startDate",startDate);
		//queryDate.put("endDate",endDate);
		
		EUDataGridResult<EveryMonthOtherInfo> list=everyMonthOtherInfoService.selectByStartAndEndDate(queryDate,page,rows);		

		for(EveryMonthOtherInfo item : list.getRows()){
            item.setInfoDate(item.getInfoDate().substring(0, 7));
			System.out.println(item.getInfoDate());
		}
		
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	/**
	 * 新增月报其他信息
	 * @param everyMonthOtherInfo
	 * @return
	 */
	@RequestMapping(value="/addEveryMonthOtherInfo.do")
	@ResponseBody
	public Object addEveryMonthOtherInfo(@RequestBody EveryMonthOtherInfo everyMonthOtherInfo) {

		Map<String, Object> queryDate = new HashMap<String, Object>();
		queryDate.put("salesDepartmentID",this.getCurrentUser().getSalesDepartmentID());
		queryDate.put("infoDate",everyMonthOtherInfo.getInfoDate()+"-01");
		List<EveryMonthOtherInfo> list=everyMonthOtherInfoService.selectByDate(queryDate);
		String resultStr="";
		if(list.size()==0){
			//EveryMonthOtherInfo everyMonthOtherInfo=new EveryMonthOtherInfo();
			if(everyMonthOtherInfo.getManageCost()==null)
			{
				everyMonthOtherInfo.setManageCost(new BigDecimal(0));
			}
			if(everyMonthOtherInfo.getEarlyNumber()==null)
			{
				everyMonthOtherInfo.setEarlyNumber(0);
			}
			if(everyMonthOtherInfo.getFinalNumber()==null)
			{
				everyMonthOtherInfo.setFinalNumber(0);
			}
			if(everyMonthOtherInfo.getSingleExcessAmount()==null)
			{
				everyMonthOtherInfo.setSingleExcessAmount(new BigDecimal(0));
			}
			if(everyMonthOtherInfo.getOverallExcessAmount()==null)
			{
				everyMonthOtherInfo.setOverallExcessAmount(new BigDecimal(0));
			}
			
			everyMonthOtherInfo.setInfoDate(everyMonthOtherInfo.getInfoDate()+"-01");
//			java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		 
//			Date date=null;
//			try {
//				date = formatter.parse(infoDate);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			everyMonthOtherInfo.setSalesDepartmentID(this.getCurrentUser().getSalesDepartmentID());
			everyMonthOtherInfo.setIsDelete(0);
			int result=everyMonthOtherInfoService.insert(everyMonthOtherInfo);
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
		EveryMonthOtherInfo everyMonthOtherInfo=everyMonthOtherInfoService.selectPK(id);		
		everyMonthOtherInfo.setInfoDate(everyMonthOtherInfo.getInfoDate().substring(0, 7));		
		return gson.toJson(everyMonthOtherInfo);
	}
	
	@RequestMapping(value="/updateEveryMonthOtherInfo.do")
	@ResponseBody
	public Object updateEveryMonthOtherInfo(@RequestBody EveryMonthOtherInfo everyMonthOtherInfo) {
		
		Map<String, Object> queryDate = new HashMap<String, Object>();
		queryDate.put("salesDepartmentID",this.getCurrentUser().getSalesDepartmentID());
		queryDate.put("infoDate",everyMonthOtherInfo.getInfoDate()+"-01");
		List<EveryMonthOtherInfo> list=everyMonthOtherInfoService.selectByDate(queryDate);
		String resultStr="";
		if(list.size()==0){
		
			if(everyMonthOtherInfo.getManageCost()==null)
			{
				everyMonthOtherInfo.setManageCost(new BigDecimal(0));
			}
			if(everyMonthOtherInfo.getEarlyNumber()==null)
			{
				everyMonthOtherInfo.setEarlyNumber(0);
			}
			if(everyMonthOtherInfo.getFinalNumber()==null)
			{
				everyMonthOtherInfo.setFinalNumber(0);
			}
			if(everyMonthOtherInfo.getSingleExcessAmount()==null)
			{
				everyMonthOtherInfo.setSingleExcessAmount(new BigDecimal(0));
			}
			if(everyMonthOtherInfo.getOverallExcessAmount()==null)
			{
				everyMonthOtherInfo.setOverallExcessAmount(new BigDecimal(0));
			}
			everyMonthOtherInfo.setInfoDate(everyMonthOtherInfo.getInfoDate()+"-01");
			int result=everyMonthOtherInfoService.update(everyMonthOtherInfo);
			
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
	
	@RequestMapping(value="/deleteEveryMonthOtherInfo.do")
	@ResponseBody
	public Object deleteEveryMonthOtherInfo(int id) {
		
		EveryMonthOtherInfo everyMonthOtherInfo=new EveryMonthOtherInfo();
		everyMonthOtherInfo.setId(id);
		everyMonthOtherInfo.setIsDelete(1);//"1"代表删除，"0"代表未删除			
		int result=everyMonthOtherInfoService.update(everyMonthOtherInfo);
		String resultStr="";
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
