package com.qst.scnt.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExpExcelUtil {
	/**
	 * 
	 * @param map 格式：（1）titles：各列的标题；（2）name：文件名（3）sheetName：表tab名称
	 * 					（4）varList：列表，里面是map,map内的字段按照var1、var2、var3……对应
	 * 					titiles与varList内容对应
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void getHSSFWorkbook(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(map.get("sheetName").toString());
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCell cell = null;
        String excelName = map.get("name").toString();
        List<Map> varList = (List<Map>) map.get("varList");
        List<String> titles = (List<String>) map.get("titles");
        String sFontname = "宋体";
        HSSFFont font = workbook.createFont();
        font.setFontName(sFontname);
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern((short) 1);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        HSSFRow header = sheet.createRow(0);
        for(short j=0;j<titles.size();j++) {
            header.createCell(j).setCellValue(titles.get(j));
            header.getCell(j).setCellStyle(style);
        }
        short rowCount = 1;
        for (Map mapRow : varList) {
        	HSSFRow userRow = sheet.createRow(rowCount++);
            for(short i=0;i<mapRow.size();i++) {
            	sheet.setColumnWidth(i, 5000);//表格宽度
            	String varstr = mapRow.get("var"+(i+1)) != null ? mapRow.get("var"+(i+1)).toString() : "";
            	userRow.createCell(i).setCellValue(varstr);
            }
            
        }
        response.setContentType("application/x-download");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+new String(excelName.getBytes("gbk"), "iso8859-1")+".xls");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/ms-excel; charset=UTF-8");
        OutputStream os = response.getOutputStream();
        workbook.write(os);
        os.flush();
        os.close();
	}

}
