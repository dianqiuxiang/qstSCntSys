package com.qst.scnt.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.google.gson.Gson;
import com.qst.scnt.model.ExpenseItem;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.model.exesDetail;
import com.qst.scnt.service.ExpenseItemService;
import com.qst.scnt.service.SalesDepartmentInfoService;
import com.qst.scnt.service.SorcReciPayExesDetailService;
import com.qst.scnt.utils.EUDataGridResult;
import com.qst.scnt.utils.ExpExcelUtil;
import com.qst.scnt.utils.ObjectExcelView;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Controller
@RequestMapping(value="/sorcReciPayExesDetail")
public class SorcReciPayExesDetailController  extends BaseController {
	
	@Resource
	private SorcReciPayExesDetailService sorcReciPayExesDetailService;
	
	@Resource
	private ExpenseItemService expenseItemService;
	
	@Resource
	private SalesDepartmentInfoService salesDepartmentInfoService;
	
	/**
	 * 查询所有部门信息
	 * @return
	 */
	@RequestMapping(value="/getSalesDept.do")
	@ResponseBody
	public Object getSalesDept(){
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("parentID",0);//指定查询范围,此处默认查询本部门下的顾客信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		
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
			fieldMap.put("parentID",item.getId());//指定查询范围,此处默认查询本部门下的顾客信息	 
			
			Map<String, Object> queryParams = new HashMap<String, Object>();  
			queryParams.put("where", fieldMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
			
			List<SalesDepartmentInfo> childrenList=salesDepartmentInfoService.selectParam(queryParams);
			
			int j=0;
			for(SalesDepartmentInfo childNode:childrenList){
				j++;
				returnJson+="{";
				returnJson+="\"id\":"+ childNode.getId() +",";
				returnJson+="\"text\":\""+ childNode.getSalesDepartmentName() +"\",";
				returnJson+="\"children\":[";
				
				Map<String, Object> fieldMap2 = new HashMap<String, Object>();
				fieldMap2.put("parentID",childNode.getId());//指定查询范围,此处默认查询本部门下的顾客信息	 
				
				Map<String, Object> queryParams2 = new HashMap<String, Object>();  
				queryParams2.put("where", fieldMap2); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
				
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
		//System.out.println(returnJson);
		return returnJson;
	}
	/**
	 * 查询所有项目信息
	 * @return
	 */
	@RequestMapping(value="/getExpenseItem.do")
	@ResponseBody
	public Object getExpenseItem() {		
		/*Gson gson = new Gson();

		List<ExpenseItem> list=expenseItemService.select();
		ExpenseItem model=new ExpenseItem();
		model.setId(-1);
		model.setExpenseItem("选择所有");
		list.add(0, model); 
		//System.out.println(gson.toJson(list));
		return gson.toJson(list);*/
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("parentID",0);//指定查询范围,此处默认查询本部门下的费用项目信息	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		
		List<ExpenseItem> list=expenseItemService.selectParam(params);
		
		String returnJson="[";
		int i=0;
		for(ExpenseItem item:list){
			i++;
			returnJson+="{";
			returnJson+="\"id\":"+ item.getId() +",";
			returnJson+="\"text\":\""+ item.getExpenseItem() +"\",";
			returnJson+="\"children\":[";
			Map<String, Object> fieldMap = new HashMap<String, Object>();
			fieldMap.put("parentID",item.getId());//指定查询范围,此处默认查询本部门下的费用项目信息	 
			
			Map<String, Object> queryParams = new HashMap<String, Object>();  
			queryParams.put("where", fieldMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
			
			List<ExpenseItem> childrenList=expenseItemService.selectParam(queryParams);
			
			int j=0;
			for(ExpenseItem childNode:childrenList){
				j++;
				returnJson+="{";
				returnJson+="\"id\":"+ childNode.getId() +",";
				returnJson+="\"text\":\""+ childNode.getExpenseItem() +"\",";
				returnJson+="\"children\":[";
				
				Map<String, Object> fieldMap2 = new HashMap<String, Object>();
				fieldMap2.put("parentID",childNode.getId());//指定查询范围,此处默认查询本部门下的费用项目信息	 
				
				Map<String, Object> queryParams2 = new HashMap<String, Object>();  
				queryParams2.put("where", fieldMap2); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
				
				List<ExpenseItem> childrenList2=expenseItemService.selectParam(queryParams2);
				
				int k=0;
				for(ExpenseItem childNode2:childrenList2){
					k++;
					returnJson+="{";
					returnJson+="\"id\":"+ childNode2.getId() +",";
					returnJson+="\"text\":\""+ childNode2.getExpenseItem() +"\"";
					
					
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
		//System.out.println(returnJson);
		return returnJson;
	}
	/**
	 * 签约部门新资源排名
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/getNewSorcInfo.do")
	@ResponseBody
	public Object getNewSorcInfo(String salesDepartmentID,String yearmonth,Integer page,Integer rows) {
		Gson gson = new Gson();
		
		Map<String ,Object > param = new HashMap<String,Object>();
		if(yearmonth==null) {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
			String sdate = sf.format(date);
			//略去
			param.put("yearmonth", "");//当前时间
		}else {
			param.put("yearmonth", yearmonth);
		}
		if(salesDepartmentID==null||salesDepartmentID.equals("")) {
			param.put("salesDepartmentID", null);
		}else {
			param.put("salesDepartmentID", salesDepartmentID);
		}
		param.put("pageNum", page);
		param.put("pageSize", rows);
		
		EUDataGridResult<Map> newResourList = sorcReciPayExesDetailService.countNewResourec(param);
		return gson.toJson(newResourList);
	}
	
	/**
	 * 签约部门回款盒数排名getProductNumInfo
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/getProductNumInfo.do")
	@ResponseBody
	public Object getProductNumInfo(String salesDepartmentID,String yearmonth,Integer page,Integer rows) {
		Gson gson = new Gson();
		
		Map<String ,Object > param = new HashMap<String,Object>();
		if(yearmonth==null) {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
			String sdate = sf.format(date);
			//略去
			param.put("yearmonth", "");//当前时间
		}else {
			param.put("yearmonth", yearmonth);
		}
		if(salesDepartmentID==null||salesDepartmentID.equals("")) {
			param.put("salesDepartmentID", null);
		}else {
			param.put("salesDepartmentID", salesDepartmentID);
		}
		param.put("pageNum", page);
		param.put("pageSize", rows);
		
		EUDataGridResult<Map> newResourList = sorcReciPayExesDetailService.countProductNum(param);
		return gson.toJson(newResourList);
	}
	
	/*
	 * 导出到excelNewSorcInfo
	 * @return
	 */
	@RequestMapping(value="/excelNewSorcInfo")
	public void excelNewSorcInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
			Map<String ,Object > param = new HashMap<String,Object>();
			String yearmonth = request.getParameter("yearmonth").toString();
			String salesDepartmentID = request.getParameter("salesDepartmentID").toString();
			if(yearmonth==null) {
				Date date = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
				String sdate = sf.format(date);
				//略去
				param.put("yearmonth", "");//当前时间
			}else {
				param.put("yearmonth", yearmonth);
			}
			if(salesDepartmentID==null||salesDepartmentID.equals("")) {
				param.put("salesDepartmentID", null);
			}else {
				param.put("salesDepartmentID", salesDepartmentID);
			}
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<Map> listSorcReci = sorcReciPayExesDetailService.countNewResour(param);
			List<Map> varList = new ArrayList<Map>();
			for(int i=0;i<listSorcReci.size();i++){
				Map vpd = new HashMap();
					vpd.put("var1", listSorcReci.get(i).get("pname"));	//1
					vpd.put("var2", listSorcReci.get(i).get("salesDepartmentName"));	//2
					vpd.put("var3", listSorcReci.get(i).get("number"));	//3
					vpd.put("var4", listSorcReci.get(i).get("package"));	//4
					vpd.put("var5", listSorcReci.get(i).get("No"));	//5
					
				varList.add(vpd);
			}
			Map dataMap = new HashMap();
			List<String> titles = new LinkedList<String>();
			titles.add("市场部");
			titles.add("签约部门");
			titles.add("户数");
			titles.add("盒数");
			titles.add("排名");
			
			dataMap.put("varList", varList);
			dataMap.put("titles", titles);
			dataMap.put("name", System.currentTimeMillis());
			dataMap.put("sheetName", "sheet1");
			
			ExpExcelUtil exutil = new ExpExcelUtil();
			exutil.getHSSFWorkbook(dataMap,request, response);
	}
	
	/*
	 * 导出到excelProductNumInfo
	 * @return
	 */
	@RequestMapping(value="/excelProductNumInfo")
	public void excelProductNumInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
			Map<String ,Object > param = new HashMap<String,Object>();
			String yearmonth = request.getParameter("yearmonth").toString();
			String salesDepartmentID = request.getParameter("salesDepartmentID").toString();
			if(yearmonth==null) {
				Date date = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
				String sdate = sf.format(date);
				//略去
				param.put("yearmonth", "");//当前时间
			}else {
				param.put("yearmonth", yearmonth);
			}
			if(salesDepartmentID==null||salesDepartmentID.equals("")) {
				param.put("salesDepartmentID", null);
			}else {
				param.put("salesDepartmentID", salesDepartmentID);
			}
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<Map> listSorcReci = sorcReciPayExesDetailService.countProductNumexcel(param);
			List<Map> varList = new ArrayList<Map>();
			for(int i=0;i<listSorcReci.size();i++){
				Map vpd = new HashMap();
					vpd.put("var1", listSorcReci.get(i).get("pname"));	//1
					vpd.put("var2", listSorcReci.get(i).get("salesDepartmentName"));	//2
//					vpd.put("var3", listSorcReci.get(i).get("number"));	//3
					vpd.put("var4", listSorcReci.get(i).get("package"));	//4
					vpd.put("var5", listSorcReci.get(i).get("No"));	//5
					
				varList.add(vpd);
			}
			Map dataMap = new HashMap();
			List<String> titles = new LinkedList<String>();
			titles.add("市场部");
			titles.add("签约部门");
//			titles.add("户数");
			titles.add("盒数");
			titles.add("排名");
			
			dataMap.put("varList", varList);
			dataMap.put("titles", titles);
			dataMap.put("name", System.currentTimeMillis());
			dataMap.put("sheetName", "sheet1");
			
			ExpExcelUtil exutil = new ExpExcelUtil();
			exutil.getHSSFWorkbook(dataMap,request, response);
	}
	
	/**
	 * 市场部费用明细表
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/getExesDetail.do")
	@ResponseBody
	public Object getExesDetail(String salesDepartmentID,String startDate,String endDate,String expenseItemId,Integer page,Integer rows) {
		Gson gson = new Gson();
		
		Map<String ,Object > param = new HashMap<String,Object>();
		//查询参数
		if(startDate==null||startDate.equals(""))
		{
			param.put("startDate",null);
		}
		else
		{ 
			param.put("startDate",startDate);
		}
		
		if(endDate==null||endDate.equals(""))
		{
			param.put("endDate",null);
		}
		else
		{ 
			param.put("endDate",endDate);
		}
//		param.put("startDate", startDate);
//		param.put("endDate", endDate);
		param.put("salesDepartmentID", salesDepartmentID);
		param.put("expenseItemId", expenseItemId);
		//分页
		param.put("pageNum", page);
		param.put("pageSize", rows);
		
		EUDataGridResult<exesDetail> newResourList = sorcReciPayExesDetailService.exesDetailExcel(param);
		return gson.toJson(newResourList);
	}
	
	/*
	 * 导出"市场部费用明细表"
	 * @return
	 */
	@RequestMapping(value="/exesDetailExcel")
	public void exesDetailExcel(HttpServletRequest request,HttpServletResponse response) throws IOException{
			Map<String ,Object > param = new HashMap<String,Object>();
			
			String startDate = request.getParameter("startDate").toString();
			String endDate = request.getParameter("endDate").toString();
			String expenseItemId = request.getParameter("expenseItemId").toString();
			String salesDepartmentID = request.getParameter("salesDepartmentID").toString();
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("salesDepartmentID", salesDepartmentID);
			param.put("expenseItemId", expenseItemId);
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<exesDetail> listSorcReci = sorcReciPayExesDetailService.exesDetailExcelAll(param);
			List<Map> varList = new ArrayList<Map>();
			for(int i=0;i<listSorcReci.size();i++){//十个字段
				Map vpd = new HashMap();
					vpd.put("var1", listSorcReci.get(i).getExpenseDate());	//日期
					vpd.put("var2", listSorcReci.get(i).getDeptName());	//市场部门
					vpd.put("var3", listSorcReci.get(i).getpName());	//签约部门
					vpd.put("var4", listSorcReci.get(i).getCertificateId());	//凭证号
					vpd.put("var5", listSorcReci.get(i).getRemark());	//摘要
					vpd.put("var6", listSorcReci.get(i).getPettycash());	//备用金
					vpd.put("var7", listSorcReci.get(i).getFfItemName());	//一级费用项目
					vpd.put("var8", listSorcReci.get(i).getpItemName());	//二级费用项目
					vpd.put("var9", listSorcReci.get(i).getExpenseItem());	//三级费用项目
					vpd.put("var10", listSorcReci.get(i).getExpenseAmount());	//费用金额
					
				varList.add(vpd);
			}
			Map dataMap = new HashMap();
			List<String> titles = new LinkedList<String>();
			titles.add("日期");
			titles.add("市场部门");
			titles.add("签约部门");
			titles.add("凭证号");
			titles.add("摘要");
			titles.add("备用金");
			titles.add("一级费用项目");
			titles.add("二级费用项目");
			titles.add("三级费用项目");
			titles.add("费用金额");
			
			dataMap.put("varList", varList);
			dataMap.put("titles", titles);
			dataMap.put("name", System.currentTimeMillis());
			dataMap.put("sheetName", "sheet1");
			
			ExpExcelUtil exutil = new ExpExcelUtil();
			exutil.getHSSFWorkbook(dataMap,request, response);
	}
	
	@RequestMapping(value="/dowloadResultByFtl")
	public void dowloadResultByFtl(String yearmonth,Integer page,Integer rows,HttpServletResponse response) throws Exception{
		Map<String ,Object > param = new HashMap<String,Object>();
		if(yearmonth==null) {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
			String sdate = sf.format(date);
			//略去
			param.put("yearmonth", "");//当前时间
		}else {
			param.put("yearmonth", yearmonth);
		}
		
		param.put("pageNum", page);
		param.put("pageSize", rows);
		List<Map> listSorcReci = sorcReciPayExesDetailService.countNewResour(param);
		Map<String,Object> root = new HashMap<String,Object>();	
		root.put("listSorcReci", listSorcReci);
		
		
		
	}
	/**
	 * 
	 * @param salesDepartmentID 部门id
	 * @param yearmonth 时间
	 * @param response
	 */
	@RequestMapping(value="/exportExcel.do")
	public void getDataForExport(HttpServletRequest request,HttpServletResponse response) {
		Map<String ,Object > param = new HashMap<String,Object>();
		String yearmonth = request.getParameter("yearmonth").toString();
		
		if(yearmonth==null) {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
			String sdate = sf.format(date);
			//略去
			param.put("yearmonth", "");//当前时间
		}else {
			param.put("yearmonth", yearmonth);
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Map> listSorcReci = sorcReciPayExesDetailService.countNewResour(param);
		
        exportExcel(listSorcReci,response);//导出数据
	}
	
	
	
	  public void exportExcel(List<Map> listSorcReci,HttpServletResponse response){
		 
		          try { 
		        	  Map<String, Object> dataMap = new HashMap<String, Object>();
		              dataMap.put("listSorcReci", listSorcReci);//内容
		              dataMap.put("num", listSorcReci.size()+1);
		              

		              /** 3.调用FreeMarker的Api生成Excel */
		              Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		              // 设置加载模板文件的位置
		              cfg.setEncoding(Locale.CHINA, "utf-8");
		              
		              String nowpath; //当前tomcat的bin目录的路径 如 D:\java\software\apache-tomcat-6.0.14\bin
		              String tempdir = getClasspath();
		              
		  			  cfg.setDirectoryForTemplateLoading(new File(tempdir+"ftl/"));		//设定去哪里读取相应的ftl模板文件
		              
		              // 获取模板
		              Template template = cfg.getTemplate("exportExcel.ftl");
		              // 防止乱码，设置下编码
		              response.setCharacterEncoding("utf-8");
		  			  long sdate = System.currentTimeMillis();
		              response.setHeader("Content-Disposition", "attachment; filename="+ sdate +".xls");
		              // 生成Excel
		              template.process(dataMap, response.getWriter());
		          } catch (Exception e) {
		              e.printStackTrace();
		          }
		      } 
	
	/*
	 * 获取classpath1
	 */
	public static String getClasspath(){
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		return path;
	}

}
