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
	
	/**
	 * ǩԼ��������Դ����
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/getNewSorcInfo.do")
	@ResponseBody
	public Object getNewSorcInfo(String yearmonth,Integer page,Integer rows) {
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
	public Object getProductNumInfo(String yearmonth,Integer page,Integer rows) {
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
			//String salesDepartmentID = request.getParameter("salesDepartmentID").toString();
			if(yearmonth==null) {
				Date date = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
				String sdate = sf.format(date);
				//��ȥ
				param.put("yearmonth", "");//��ǰʱ��
			}else {
				param.put("yearmonth", yearmonth);
			}
			//param.put("salesDepartmentID", salesDepartmentID);
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
			//String salesDepartmentID = request.getParameter("salesDepartmentID").toString();
			if(yearmonth==null) {
				Date date = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
				String sdate = sf.format(date);
				//��ȥ
				param.put("yearmonth", "");//��ǰʱ��
			}else {
				param.put("yearmonth", yearmonth);
			}
			//param.put("salesDepartmentID", salesDepartmentID);
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
