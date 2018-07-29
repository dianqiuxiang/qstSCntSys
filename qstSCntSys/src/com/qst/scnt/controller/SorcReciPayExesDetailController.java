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
		//System.out.println(returnJson);
		return returnJson;
	}
	/**
	 * ��ѯ������Ŀ��Ϣ
	 * @return
	 */
	@RequestMapping(value="/getExpenseItem.do")
	@ResponseBody
	public Object getExpenseItem() {		
		/*Gson gson = new Gson();

		List<ExpenseItem> list=expenseItemService.select();
		ExpenseItem model=new ExpenseItem();
		model.setId(-1);
		model.setExpenseItem("ѡ������");
		list.add(0, model); 
		//System.out.println(gson.toJson(list));
		return gson.toJson(list);*/
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("parentID",0);//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µķ�����Ŀ��Ϣ	 
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
		
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
			fieldMap.put("parentID",item.getId());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µķ�����Ŀ��Ϣ	 
			
			Map<String, Object> queryParams = new HashMap<String, Object>();  
			queryParams.put("where", fieldMap); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
			
			List<ExpenseItem> childrenList=expenseItemService.selectParam(queryParams);
			
			int j=0;
			for(ExpenseItem childNode:childrenList){
				j++;
				returnJson+="{";
				returnJson+="\"id\":"+ childNode.getId() +",";
				returnJson+="\"text\":\""+ childNode.getExpenseItem() +"\",";
				returnJson+="\"children\":[";
				
				Map<String, Object> fieldMap2 = new HashMap<String, Object>();
				fieldMap2.put("parentID",childNode.getId());//ָ����ѯ��Χ,�˴�Ĭ�ϲ�ѯ�������µķ�����Ŀ��Ϣ	 
				
				Map<String, Object> queryParams2 = new HashMap<String, Object>();  
				queryParams2.put("where", fieldMap2); //�ŵ�Map��ȥ��"where"��key,"whereMap"��value,����SQL���where���������
				
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
	 * ǩԼ��������Դ����
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
			//��ȥ
			param.put("yearmonth", "");//��ǰʱ��
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
	 * ǩԼ���Żؿ��������getProductNumInfo
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
			//��ȥ
			param.put("yearmonth", "");//��ǰʱ��
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
	 * ������excelNewSorcInfo
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
				//��ȥ
				param.put("yearmonth", "");//��ǰʱ��
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
			titles.add("�г���");
			titles.add("ǩԼ����");
			titles.add("����");
			titles.add("����");
			titles.add("����");
			
			dataMap.put("varList", varList);
			dataMap.put("titles", titles);
			dataMap.put("name", System.currentTimeMillis());
			dataMap.put("sheetName", "sheet1");
			
			ExpExcelUtil exutil = new ExpExcelUtil();
			exutil.getHSSFWorkbook(dataMap,request, response);
	}
	
	/*
	 * ������excelProductNumInfo
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
				//��ȥ
				param.put("yearmonth", "");//��ǰʱ��
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
			titles.add("�г���");
			titles.add("ǩԼ����");
//			titles.add("����");
			titles.add("����");
			titles.add("����");
			
			dataMap.put("varList", varList);
			dataMap.put("titles", titles);
			dataMap.put("name", System.currentTimeMillis());
			dataMap.put("sheetName", "sheet1");
			
			ExpExcelUtil exutil = new ExpExcelUtil();
			exutil.getHSSFWorkbook(dataMap,request, response);
	}
	
	/**
	 * �г���������ϸ��
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/getExesDetail.do")
	@ResponseBody
	public Object getExesDetail(String salesDepartmentID,String startDate,String endDate,String expenseItemId,Integer page,Integer rows) {
		Gson gson = new Gson();
		
		Map<String ,Object > param = new HashMap<String,Object>();
		//��ѯ����
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
		//��ҳ
		param.put("pageNum", page);
		param.put("pageSize", rows);
		
		EUDataGridResult<exesDetail> newResourList = sorcReciPayExesDetailService.exesDetailExcel(param);
		return gson.toJson(newResourList);
	}
	
	/*
	 * ����"�г���������ϸ��"
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
			for(int i=0;i<listSorcReci.size();i++){//ʮ���ֶ�
				Map vpd = new HashMap();
					vpd.put("var1", listSorcReci.get(i).getExpenseDate());	//����
					vpd.put("var2", listSorcReci.get(i).getDeptName());	//�г�����
					vpd.put("var3", listSorcReci.get(i).getpName());	//ǩԼ����
					vpd.put("var4", listSorcReci.get(i).getCertificateId());	//ƾ֤��
					vpd.put("var5", listSorcReci.get(i).getRemark());	//ժҪ
					vpd.put("var6", listSorcReci.get(i).getPettycash());	//���ý�
					vpd.put("var7", listSorcReci.get(i).getFfItemName());	//һ��������Ŀ
					vpd.put("var8", listSorcReci.get(i).getpItemName());	//����������Ŀ
					vpd.put("var9", listSorcReci.get(i).getExpenseItem());	//����������Ŀ
					vpd.put("var10", listSorcReci.get(i).getExpenseAmount());	//���ý��
					
				varList.add(vpd);
			}
			Map dataMap = new HashMap();
			List<String> titles = new LinkedList<String>();
			titles.add("����");
			titles.add("�г�����");
			titles.add("ǩԼ����");
			titles.add("ƾ֤��");
			titles.add("ժҪ");
			titles.add("���ý�");
			titles.add("һ��������Ŀ");
			titles.add("����������Ŀ");
			titles.add("����������Ŀ");
			titles.add("���ý��");
			
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
			//��ȥ
			param.put("yearmonth", "");//��ǰʱ��
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
	 * @param salesDepartmentID ����id
	 * @param yearmonth ʱ��
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
			//��ȥ
			param.put("yearmonth", "");//��ǰʱ��
		}else {
			param.put("yearmonth", yearmonth);
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Map> listSorcReci = sorcReciPayExesDetailService.countNewResour(param);
		
        exportExcel(listSorcReci,response);//��������
	}
	
	
	
	  public void exportExcel(List<Map> listSorcReci,HttpServletResponse response){
		 
		          try { 
		        	  Map<String, Object> dataMap = new HashMap<String, Object>();
		              dataMap.put("listSorcReci", listSorcReci);//����
		              dataMap.put("num", listSorcReci.size()+1);
		              

		              /** 3.����FreeMarker��Api����Excel */
		              Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		              // ���ü���ģ���ļ���λ��
		              cfg.setEncoding(Locale.CHINA, "utf-8");
		              
		              String nowpath; //��ǰtomcat��binĿ¼��·�� �� D:\java\software\apache-tomcat-6.0.14\bin
		              String tempdir = getClasspath();
		              
		  			  cfg.setDirectoryForTemplateLoading(new File(tempdir+"ftl/"));		//�趨ȥ�����ȡ��Ӧ��ftlģ���ļ�
		              
		              // ��ȡģ��
		              Template template = cfg.getTemplate("exportExcel.ftl");
		              // ��ֹ���룬�����±���
		              response.setCharacterEncoding("utf-8");
		  			  long sdate = System.currentTimeMillis();
		              response.setHeader("Content-Disposition", "attachment; filename="+ sdate +".xls");
		              // ����Excel
		              template.process(dataMap, response.getWriter());
		          } catch (Exception e) {
		              e.printStackTrace();
		          }
		      } 
	
	/*
	 * ��ȡclasspath1
	 */
	public static String getClasspath(){
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		return path;
	}

}
