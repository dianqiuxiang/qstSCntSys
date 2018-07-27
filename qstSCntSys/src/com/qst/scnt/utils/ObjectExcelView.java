package com.qst.scnt.utils;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

public class ObjectExcelView extends AbstractXlsView{

	@Override
    protected void buildExcelDocument(Map<String, Object> map,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        String excelName = map.get("name").toString() + ".xls";
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(excelName,"utf-8"));
        response.setContentType("application/ms-excel; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        List<Map> varList = (List<Map>) map.get("varList");//表正文里面的字段值
        List<String> titles = (List<String>) map.get("titles");//标题里面的字段值
        
        Sheet sheet = workbook.createSheet("User Detail");
        sheet.setDefaultColumnWidth(30);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern((short) 1);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        
        Row header = sheet.createRow(0);
        
        for(int j=0;j<titles.size();j++) {
            header.createCell(j).setCellValue(titles.get(j));
            header.getCell(j).setCellStyle(style);
        }
        
        /*
         * 构造表中row内容
         */
        int rowCount = 1;
        for (Map mapRow : varList) {
            Row userRow = sheet.createRow(rowCount++);
            for(int i=0;i<mapRow.size();i++) {
            	String varstr = mapRow.get("var"+(i+1)) != null ? mapRow.get("var"+(i+1)).toString() : "";
            	userRow.createCell(i).setCellValue(varstr);
            }
            
        }
        //构造excel表里内容
    }

}
