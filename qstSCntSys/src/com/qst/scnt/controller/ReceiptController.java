package com.qst.scnt.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.PageData;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.qst.scnt.model.CustomerInfo;
import com.qst.scnt.model.OrderInfo;
import com.qst.scnt.model.ReceiptInfo;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.service.OrderInfoService;
import com.qst.scnt.service.ReceiptInfoService;
import com.qst.scnt.service.SalesDepartmentInfoService;
import com.qst.scnt.utils.EUDataGridResult;
import com.qst.scnt.utils.ExcelUtil;

@Controller
@RequestMapping(value="/receipt")
public class ReceiptController extends BaseController {
	
	@Resource
	private ReceiptInfoService receiptInfoServicce;
	
	@Resource
	private OrderInfoService orderInfoService;
	
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
	 * ��ѯ���н��
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
	 * ��ѯ���н��
	 * @return
	 */
	@RequestMapping(value="/selectByCodeAndRMemberAndDate.do")
	@ResponseBody
	public Object selectByCodeAndRMemberAndDate(String orderCode,Integer salesDepartmentID, String receiptMember,String startDate,String endDate,int page,int rows) {
		Gson gson = new Gson();
		Map<String, Object> queryDate = new HashMap<String, Object>();
		
		//queryDate.put("orderCode",orderCode);
		//queryDate.put("receiptMember",receiptMember);
		
		if(orderCode==null||orderCode.equals(""))
		{
			queryDate.put("orderCode",null);
		}
		else
		{ 
			queryDate.put("orderCode",receiptMember);
		}
		
		
		if(receiptMember==null||receiptMember.equals(""))
		{
			queryDate.put("receiptMember",null);
		}
		else
		{ 
			queryDate.put("receiptMember",receiptMember);
		}
		
		if(startDate==null||startDate.equals(""))
		{
			queryDate.put("startDate",null);
		}
		else
		{ 
			queryDate.put("startDate",startDate);
		}
		
		if(endDate==null||endDate.equals(""))
		{
			queryDate.put("endDate",null);
		}
		else
		{ 
			queryDate.put("endDate",endDate);
		}
		
		//queryDate.put("startDate",startDate);
		//queryDate.put("endDate",endDate);
		
		queryDate.put("salesDepartmentID",salesDepartmentID);
		EUDataGridResult<ReceiptInfo> receiptInfoList=receiptInfoServicce.selectByCodeAndRMemberAndDate(queryDate,page,rows);
		return gson.toJson(receiptInfoList);
	}
	
	/**
	 * ��ѯ���н��
	 * @return
	 */
	@RequestMapping(value="/selectByCodeAndRMemberAndDateNoPageSize.do")
	@ResponseBody
	public Object selectByCodeAndRMemberAndDate(String orderCode,Integer salesDepartmentID, String receiptMember,String startDate,String endDate) {
		Gson gson = new Gson();
		Map<String, Object> queryDate = new HashMap<String, Object>();
		
		//queryDate.put("orderCode",orderCode);
		//queryDate.put("receiptMember",receiptMember);
		
		if(orderCode==null||orderCode.equals(""))
		{
			queryDate.put("orderCode",null);
		}
		else
		{ 
			queryDate.put("orderCode",receiptMember);
		}
		
		
		if(receiptMember==null||receiptMember.equals(""))
		{
			queryDate.put("receiptMember",null);
		}
		else
		{ 
			queryDate.put("receiptMember",receiptMember);
		}
		
		if(startDate==null||startDate.equals(""))
		{
			queryDate.put("startDate",null);
		}
		else
		{ 
			queryDate.put("startDate",startDate);
		}
		
		if(endDate==null||endDate.equals(""))
		{
			queryDate.put("endDate",null);
		}
		else
		{ 
			queryDate.put("endDate",endDate);
		}
		
		//queryDate.put("startDate",startDate);
		//queryDate.put("endDate",endDate);
		
		queryDate.put("salesDepartmentID",salesDepartmentID);
		List<ReceiptInfo> receiptInfoList=receiptInfoServicce.selectByCodeAndRMemberAndDate(queryDate);
		return gson.toJson(receiptInfoList);
	}
	
	/**
	 * ���ݶ������ģ����ѯ�˲����¶����еĶ������
	 * @param ID
	 * @return
	 */
	@RequestMapping(value="/selectOrderCode.do")
	@ResponseBody
	public Object selectOrderCode(String orderCode,Integer salesDepartmentID,int page,int rows) {
		Gson gson=new Gson();
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setOrderCode(orderCode);
		orderInfo.setSalesDepartmentID(salesDepartmentID);
		
		EUDataGridResult<OrderInfo> list=orderInfoService.selectParamFlexible(orderInfo,page,rows);
		//System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	/**
	 * �����տ���Ϣ
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
			resultStr="{\"result\":\"Success\"}";
		}
		else
		{
			resultStr="{\"result\":\"Failed\"}";
		}
		return resultStr;
	}

	/**
	 * ����ID��ѯ�տ���Ϣ
	 * @return
	 */
	@RequestMapping(value="/selectByID.do")
	@ResponseBody
	public Object selectByID(int id) {
		Gson gson = new Gson();
		ReceiptInfo receiptInfo=receiptInfoServicce.selectByID(id);
		return gson.toJson(receiptInfo);
	}
	
	/**
	 * ����ID�޸��տ���Ϣ
	 * @return
	 */
	@RequestMapping(value="/updateReceiptInfo.do")
	@ResponseBody
	public Object updateReceiptInfo(@RequestBody  ReceiptInfo receiptInfo) {
		int result=receiptInfoServicce.update(receiptInfo);
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
	
	/**
	 * ����IDɾ���տ���Ϣ
	 * @return
	 */
	@RequestMapping(value="/deleteReceiptInfo.do")
	@ResponseBody
	public Object deleteReceiptInfo(int id) {
		ReceiptInfo receiptInfo=new ReceiptInfo();
		receiptInfo.setId(id);		
		receiptInfo.setIsDelete(1);//"1"����ɾ����"0"����δɾ��
		int result=receiptInfoServicce.update(receiptInfo);
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
	
	/**
     * ��������
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletRequest request,HttpServletResponse response,String orderCode,Integer salesDepartmentID, String receiptMember,String startDate,String endDate) throws Exception {
//           //��ȡ����
//Map<String, Object> queryDate = new HashMap<String, Object>();
//		
//		//queryDate.put("orderCode",orderCode);
//		//queryDate.put("receiptMember",receiptMember);
//		
//		if(orderCode==null||orderCode.equals(""))
//		{
//			queryDate.put("orderCode",null);
//		}
//		else
//		{ 
//			queryDate.put("orderCode",receiptMember);
//		}
//		
//		
//		if(receiptMember==null||receiptMember.equals(""))
//		{
//			queryDate.put("receiptMember",null);
//		}
//		else
//		{ 
//			queryDate.put("receiptMember",receiptMember);
//		}
//		
//		if(startDate==null||startDate.equals(""))
//		{
//			queryDate.put("startDate",null);
//		}
//		else
//		{ 
//			queryDate.put("startDate",startDate);
//		}
//		
//		if(endDate==null||endDate.equals(""))
//		{
//			queryDate.put("endDate",null);
//		}
//		else
//		{ 
//			queryDate.put("endDate",endDate);
//		}
//		
//		//queryDate.put("startDate",startDate);
//		//queryDate.put("endDate",endDate);
//		
//		queryDate.put("salesDepartmentID",salesDepartmentID);
//		List<ReceiptInfo> receiptInfoList=receiptInfoServicce.selectByCodeAndRMemberAndDate(queryDate);
//
//           //excel����
//        String[] title = {"����","�Ա�","����","ѧУ","�༶"};
//
//        //excel�ļ���
//        String fileName = "ѧ����Ϣ��"+System.currentTimeMillis()+".xls";
//
//		//sheet��
//		String sheetName = "ѧ����Ϣ��";

//		for (int i = 0; i < receiptInfoList.size(); i++) {
//            content[i] = new String[title.length];
//            PageData obj = receiptInfoList.get(i);
//            content[i][0] = obj.get("stuName").tostring();
//            content[i][1] = obj.get("stuSex").tostring();
//            content[i][2] = obj.get("stuAge").tostring();
//            content[i][3] = obj.get("stuSchoolName").tostring();
//            content[i][4] = obj.get("stuClassName").tostring();
//}

////����HSSFWorkbook 
//HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
//
////��Ӧ���ͻ���
//try {
//this.setResponseHeader(response, fileName);
//OutputStream os = response.getOutputStream();
//wb.write(os);
//os.flush();
//os.close();
//} catch (Exception e) {
//e.printStackTrace();}
}
//
//
//    //������Ӧ������
//    public void setResponseHeader(HttpServletResponse response, String fileName) {
//        try {
//            try {
//                fileName = new String(fileName.getBytes(),"ISO8859-1");
//            } catch (UnsupportedEncodingException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            response.setContentType("application/octet-stream;charset=ISO8859-1");
//            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
//            response.addHeader("Pargam", "no-cache");
//            response.addHeader("Cache-Control", "no-cache");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

}
