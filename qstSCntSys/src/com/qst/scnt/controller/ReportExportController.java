package com.qst.scnt.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qst.scnt.model.EmployeeInfo;
import com.qst.scnt.model.ExpenseItem;
import com.qst.scnt.model.OrderInfo;
import com.qst.scnt.model.OrderProductInfo;
import com.qst.scnt.model.ParameterInfo;
import com.qst.scnt.model.ProductInfo;
import com.qst.scnt.model.ReceiptInfo;
import com.qst.scnt.model.ResultReportModel;
import com.qst.scnt.model.SalesDepartmentInfo;
import com.qst.scnt.model.UserInfo;
import com.qst.scnt.service.CostService;
import com.qst.scnt.service.EmployeeInfoService;
import com.qst.scnt.service.EveryMonthOtherInfoService;
import com.qst.scnt.service.ExpenseItemService;
import com.qst.scnt.service.OrderInfoService;
import com.qst.scnt.service.OrderProductInfoService;
import com.qst.scnt.service.ParameterInfoService;
import com.qst.scnt.service.ProductInfoService;
import com.qst.scnt.service.ReceiptInfoService;
import com.qst.scnt.service.SalesDepartmentInfoService;
import com.qst.scnt.service.UserInfoService;
import com.qst.scnt.utils.EUDataGridResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@Controller
@RequestMapping(value="/reportExport")
public class ReportExportController extends BaseController {

	@Resource
	private ParameterInfoService parameterInfoService;
	@Resource
	private ProductInfoService productInfoService;
	@Resource
	private CostService costService;	
	@Resource
	private ReceiptInfoService receiptInfoService;	
	@Resource
	private UserInfoService userInfoService;	
	@Resource
	private SalesDepartmentInfoService salesDepartmentInfoService;
	@Resource
	private ExpenseItemService expenseItemService;	
	@Resource
	private EveryMonthOtherInfoService everyMonthOtherInfoService;
	@Resource
	private EmployeeInfoService employeeInfoService;
	@Resource
	private OrderInfoService orderInfoService;
	@Resource
	private OrderProductInfoService orderProductInfoService;
	
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
		////System.out.println(gson.toJson(list));
		return null;//gson.toJson(list);
	}
	
	@RequestMapping(value="/getSalesReport.do")
	@ResponseBody
	public Object getSalesReport(String startDate,String endDate) {
		
		Gson gson = new Gson();		
		Map<String, Object> oParams = new HashMap<String, Object>();		
		if(startDate==null||startDate.equals(""))
		{
			oParams.put("startDate",null);
		}
		else
		{ 
			oParams.put("startDate",startDate);
		}
		
		if(endDate==null||endDate.equals(""))
		{
			oParams.put("endDate",null);
		}
		else
		{ 
			oParams.put("endDate",endDate);
		}
		List<OrderInfo> oList=orderInfoService.selectByDate(oParams);

		List<OrderProductInfo> opList=new ArrayList();
		for(OrderInfo item:oList){
			OrderProductInfo orderProductInfo=new OrderProductInfo();
			orderProductInfo.setOrderID(item.getId());
			opList.addAll(orderProductInfoService.selectOProductByOrderID(orderProductInfo));
		}
		
		Map<String, Object> orParams = new HashMap<String, Object>();	
		orParams.put("orderInfoList",oList);
		List<ReceiptInfo> rList=receiptInfoService.selectByOrderID(orParams);
		////System.out.println(gson.toJson(list));
		JSONArray jsonArrray = new JSONArray();
		JSONObject jsonObjectItem=new JSONObject();
		jsonObjectItem.put("oList", oList);	
		jsonObjectItem.put("opList", opList);	
		jsonObjectItem.put("rList", rList);	
		jsonArrray.add(jsonObjectItem);
		return jsonArrray;//gson.toJson(list);
	}
	
	
	@RequestMapping(value="/getSMinisterReport.do")
	@ResponseBody
	public Object getSMinisterReport(Integer salesDepartmentID,String salesYear) {
		
		String resultStr="";	
		
		BigDecimal profit= getProfitParam();
		if(profit==null||profit.equals(""))
		{
			resultStr="{\"result\":\"NoProfit\"}";
			return resultStr;
		}
		BigDecimal milkPrice= getMilkPrice();
		if(profit==null||profit.equals(""))
		{
			resultStr="{\"result\":\"NoMilkPrice\"}";
			return resultStr;
		}
		JSONArray jsonArrray = new JSONArray();
		JSONObject jsonObjectItem=new JSONObject();
		jsonObjectItem.put("id", 0);		
		
		
		List<ExpenseItem> expenseItemList=getExpenseItem();
		List<ResultReportModel> ItemList=getResultReportItem(expenseItemList,profit);
		jsonObjectItem.put("content", ItemList);
		
		jsonArrray.add(jsonObjectItem);
		
		//System.out.println(jsonArrray);		
		
		for(int i=1;i<=12;i++)
		{
			JSONObject jsonObjectContent=new JSONObject();
			jsonObjectContent.put("id", i);
			List<ResultReportModel> resultList=new ArrayList();;
			
			Map<String, Object> params = new HashMap<String, Object>();
			if(i<10){
				params.put("salesDate",salesYear+"-0"+i+"-01");	 
			}
			else{
				params.put("salesDate",salesYear+"-"+i+"-01");
			}
			params.put("salesDepartmentID",salesDepartmentID);			
			
			HashMap sumReceipt_ResultMap=receiptInfoService.selectBySDeptIdAndYM(params);
			ResultReportModel model1=new ResultReportModel();
			ResultReportModel model2=new ResultReportModel();
			ResultReportModel model3=new ResultReportModel();
			model1.setId(1);
			model1.setName1("回款折合牛初乳数量");
			model2.setId(2);
			model2.setName1("回款金额");
			model3.setId(3);
			model3.setName1("费用(销售额的"+profit.multiply(new BigDecimal(100))+"%)");
			BigDecimal receiptCount=new BigDecimal(0);
			BigDecimal numCount=new BigDecimal(0);
			BigDecimal cost_Profit_Count=new BigDecimal(0);
			if(sumReceipt_ResultMap==null)
			{
				model1.setName2("-");
				model2.setName2("-");
				model3.setName2("-");
			}
			else{
				receiptCount=new BigDecimal(sumReceipt_ResultMap.get("sumReceiptAmount").toString());
				numCount=receiptCount.divide(milkPrice,0, BigDecimal.ROUND_HALF_UP);
				model1.setName2(numCount.toString());
				model2.setName2(receiptCount.toString());
				cost_Profit_Count=receiptCount.multiply(profit).setScale(2,BigDecimal.ROUND_HALF_UP);
				model3.setName2(cost_Profit_Count.toString());
			}
			resultList.add(model1);
			resultList.add(model2);
			resultList.add(model3);
			
			BigDecimal costCount=new BigDecimal(0);
			int j=0;
			for(j=0;j<expenseItemList.size();j++){
				params.put("expenseItem",expenseItemList.get(j).getExpenseItem());	
				HashMap sumCost_ResultMap=costService.selectBySDeptIdAndYM(params);
				ResultReportModel model_Item=new ResultReportModel();
				model_Item.setId(3+j+1);
				model_Item.setName1(expenseItemList.get(j).getExpenseItem());
				if(sumCost_ResultMap==null)
				{
					model_Item.setName2("-");
				}
				else{
					model_Item.setName2(sumCost_ResultMap.get("sumCostAmount").toString());
					costCount=costCount.add(new BigDecimal(sumCost_ResultMap.get("sumCostAmount").toString()));
				}
				resultList.add(model_Item);
			}
			
			ResultReportModel model4=new ResultReportModel();
			model4.setId(4+j+1);
			model4.setName1("费用合计");
			if(costCount.equals(new BigDecimal(0)))
			{
				model4.setName2("-");
			}
			else{
				model4.setName2(costCount.toString());
			}
			resultList.add(model4);
			
			ResultReportModel model5=new ResultReportModel();
			model5.setId(5+j+1);
			model5.setName1("费销比");
			if(receiptCount.equals(new BigDecimal(0)))
			{
				model5.setName2("-");
			}
			else{
				model5.setName2((costCount.divide(receiptCount,2, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100))+"%");
			}
			resultList.add(model5);
			
			ResultReportModel model6=new ResultReportModel();
			model6.setId(6+j+1);
			model6.setName1("当月计提利润");
			BigDecimal monthProfit=cost_Profit_Count.subtract(costCount);
			model6.setName2(monthProfit.toString());
			resultList.add(model6);
			
			HashMap monthInfo_ResultMap=everyMonthOtherInfoService.selectBySDeptIdAndYM(params);
			
			ResultReportModel model7=new ResultReportModel();
			model7.setId(7+j+1);
			model7.setName1("管理费用（300盒以下）");
			
			ResultReportModel model8=new ResultReportModel();
			model8.setId(8+j+1);
			model8.setName1("实际利润");
			
			ResultReportModel model9=new ResultReportModel();
			model9.setId(9+j+1);
			model9.setName1("期初人数");
			
			ResultReportModel model10=new ResultReportModel();
			model10.setId(10+j+1);
			model10.setName1("期末人数");
			
			ResultReportModel model11=new ResultReportModel();
			model11.setId(11+j+1);
			model11.setName1("月平均人数:（期初+期末）/2");
			
			ResultReportModel model12=new ResultReportModel();
			model12.setId(12+j+1);
			model12.setName1("单笔累计赠送超标金额");
			
			ResultReportModel model13=new ResultReportModel();
			model13.setId(13+j+1);
			model13.setName1("整体考量赠送超标金额");
			if(monthInfo_ResultMap==null)
			{
				model7.setName2("-");
				model8.setName2(monthProfit.toString());
				model9.setName2("-");
				model10.setName2("-");
				model11.setName2("-");
				model12.setName2("-");
				model13.setName2("-");				
			}
			else{
				if(monthInfo_ResultMap.get("manageCost").equals("0")){
					model7.setName2("-");
					model8.setName2((monthProfit.subtract(new BigDecimal(monthInfo_ResultMap.get("manageCost").toString()))).toString());
				}
				else{
					model7.setName2(monthInfo_ResultMap.get("manageCost").toString());
					model8.setName2((monthProfit.subtract(new BigDecimal(monthInfo_ResultMap.get("manageCost").toString()))).toString());
				}
				if(monthInfo_ResultMap.get("earlyNumber").equals("0")){
					model9.setName2("-");
				}
				else{
					model9.setName2(monthInfo_ResultMap.get("earlyNumber").toString());
				}
				if(monthInfo_ResultMap.get("finalNumber").equals("0")){
					model10.setName2("-");
				}
				else{
					
				}
				if(monthInfo_ResultMap.get("earlyNumber").equals("0")&&monthInfo_ResultMap.get("finalNumber").equals("0")){
					model11.setName2("-");
				}
				else{
					BigDecimal a1=new BigDecimal(monthInfo_ResultMap.get("earlyNumber").toString());
					BigDecimal a2=new BigDecimal(monthInfo_ResultMap.get("finalNumber").toString());
					BigDecimal a3=a1.add(a2);
					model11.setName2(a3.divide(new BigDecimal(2),2, BigDecimal.ROUND_HALF_UP).toString());
				}
				if(monthInfo_ResultMap.get("singleExcessAmount").equals("0")){
					model12.setName2("-");
				}
				else{
					model12.setName2(monthInfo_ResultMap.get("singleExcessAmount").toString());
				}
				if(monthInfo_ResultMap.get("overallExcessAmount").equals("0")){
					model13.setName2("-");
				}
				else{
					model13.setName2(monthInfo_ResultMap.get("overallExcessAmount").toString());
				}
			}			
			resultList.add(model7);
			resultList.add(model8);
			resultList.add(model9);
			resultList.add(model10);
			resultList.add(model11);
			resultList.add(model12);
			resultList.add(model13);

			Map<String, Object> getMinisterParams = new HashMap<String, Object>();			
			getMinisterParams.put("salesDepartmentID",salesDepartmentID);
			getMinisterParams.put("role",3);			
			Map<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("where",getMinisterParams);			
			List<EmployeeInfo> ministerList=employeeInfoService.selectParam(whereMap);
			if(ministerList.size()==0){
				params.put("ministerList",null);
			}
			else{
				params.put("ministerList",ministerList);
			}
			
			HashMap salesVolumeCount_ResultMap=receiptInfoService.selectSalesVolume(params);			
			ResultReportModel model14=new ResultReportModel();
			model14.setId(14+j+1);
			model14.setName1("签约部长个人销量（盒）");
			BigDecimal s1=new BigDecimal(0);
			BigDecimal s2=new BigDecimal(0);
			if(salesVolumeCount_ResultMap==null){
				model14.setName2("-");
			}
			else{
				if(salesVolumeCount_ResultMap.get("sumReceiptAmount").equals("0")){
					model14.setName2("-");
				}
				else{
					s1=new BigDecimal(salesVolumeCount_ResultMap.get("sumReceiptAmount").toString());
					s2=s1.divide(milkPrice,0, BigDecimal.ROUND_HALF_UP);
					model14.setName2(s2.toString());
				}
			}
			resultList.add(model14);
			
			
			ResultReportModel model15=new ResultReportModel();
			model15.setId(15+j+1);
			model15.setName1("签约部长个人占比%");
			if(s2.toString().equals("0"))
			{
				model15.setName2("-");
			}
			else{
				model15.setName2((s2.divide(numCount)).multiply(new BigDecimal(100))+"%");
			}
			resultList.add(model15);
			
			HashMap newCustomerCount_ResultMap=orderInfoService.selectNewCustomer(params);			
			ResultReportModel model16=new ResultReportModel();
			model16.setId(16+j+1);
			model16.setName1("本月新资源户数");
			if(newCustomerCount_ResultMap==null||newCustomerCount_ResultMap.get("newCustomer").equals("0")){
				model16.setName2("-");
			}
			else{
				model16.setName2(newCustomerCount_ResultMap.get("newCustomer").toString());
			}
			resultList.add(model16);
			
			HashMap newCustomerSalesVolume_ResultMap=receiptInfoService.selectNewCustomerSalesVolume(params);
			
			ResultReportModel model17=new ResultReportModel();
			model17.setId(17+j+1);
			model17.setName1("本月新资源盒数");
			if(newCustomerSalesVolume_ResultMap==null||newCustomerSalesVolume_ResultMap.get("newCustomerSalesVolume").equals(0)){
				model17.setName2("-");
			}
			else{
				BigDecimal n1=new BigDecimal(newCustomerSalesVolume_ResultMap.get("newCustomerSalesVolume").toString());
				BigDecimal n2=n1.divide(milkPrice,0, BigDecimal.ROUND_HALF_UP);
				model17.setName2(n2.toString());
			}
			
			resultList.add(model17);
			
			jsonObjectContent.put("content", resultList);
			
			jsonArrray.add(jsonObjectContent);
			
		}
		
		int i=13;
		JSONObject jsonObjectContent1=new JSONObject();
		jsonObjectContent1.put("id", i);
		List<ResultReportModel> aresultList=new ArrayList();;
		
		Map<String, Object> params1 = new HashMap<String, Object>();
		if(i<10){
			params1.put("salesDate",salesYear+"-01-01");	 
		}
		else{
			params1.put("salesDate",salesYear+"-01-01");
		}
		params1.put("salesDepartmentID",salesDepartmentID);			
		
		HashMap sumReceipt_ResultMap=receiptInfoService.selectBySDeptIdAndY(params1);
		ResultReportModel amodel1=new ResultReportModel();
		ResultReportModel amodel2=new ResultReportModel();
		ResultReportModel amodel3=new ResultReportModel();
		amodel1.setId(1);
		amodel1.setName1("回款折合牛初乳数量");
		amodel2.setId(2);
		amodel2.setName1("回款金额");
		amodel3.setId(3);
		amodel3.setName1("费用(销售额的"+profit.multiply(new BigDecimal(100))+"%)");
		BigDecimal receiptCount=new BigDecimal(0);
		BigDecimal numCount=new BigDecimal(0);
		BigDecimal cost_Profit_Count=new BigDecimal(0);
		if(sumReceipt_ResultMap==null)
		{
			amodel1.setName2("-");
			amodel2.setName2("-");
			amodel3.setName2("-");
		}
		else{
			receiptCount=new BigDecimal(sumReceipt_ResultMap.get("sumReceiptAmount").toString());
			numCount=receiptCount.divide(milkPrice,0, BigDecimal.ROUND_HALF_UP);
			amodel1.setName2(numCount.toString());
			amodel2.setName2(receiptCount.toString());
			cost_Profit_Count=receiptCount.multiply(profit);
			amodel3.setName2(cost_Profit_Count.toString());
		}
		aresultList.add(amodel1);
		aresultList.add(amodel2);
		aresultList.add(amodel3);
		
		BigDecimal costCount=new BigDecimal(0);
		int j=0;
		for(j=0;j<expenseItemList.size();j++){
			params1.put("expenseItem",expenseItemList.get(j).getExpenseItem());	
			HashMap sumCost_ResultMap=costService.selectBySDeptIdAndY(params1);
			ResultReportModel amodel_Item=new ResultReportModel();
			amodel_Item.setId(3+j+1);
			amodel_Item.setName1(expenseItemList.get(j).getExpenseItem());
			if(sumCost_ResultMap==null)
			{
				amodel_Item.setName2("-");
			}
			else{
				amodel_Item.setName2(sumCost_ResultMap.get("sumCostAmount").toString());
				costCount=costCount.add(new BigDecimal(sumCost_ResultMap.get("sumCostAmount").toString()));
			}
			aresultList.add(amodel_Item);
		}
		
		ResultReportModel amodel4=new ResultReportModel();
		amodel4.setId(4+j+1);
		amodel4.setName1("费用合计");
		if(costCount.equals(new BigDecimal(0)))
		{
			amodel4.setName2("-");
		}
		else{
			amodel4.setName2(costCount.toString());
		}
		aresultList.add(amodel4);
		
		ResultReportModel amodel5=new ResultReportModel();
		amodel5.setId(5+j+1);
		amodel5.setName1("费销比");
		if(receiptCount.equals(new BigDecimal(0)))
		{
			amodel5.setName2("-");
		}
		else{
			amodel5.setName2((costCount.divide(receiptCount,2, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100))+"%");
		}
		aresultList.add(amodel5);
		
		ResultReportModel amodel6=new ResultReportModel();
		amodel6.setId(6+j+1);
		amodel6.setName1("当月计提利润");
		BigDecimal monthProfit=cost_Profit_Count.subtract(costCount);
		amodel6.setName2(monthProfit.toString());
		aresultList.add(amodel6);
		
		HashMap monthInfo_ResultMap1=everyMonthOtherInfoService.selectBySDeptIdAndY(params1);
		
		ResultReportModel amodel7=new ResultReportModel();
		amodel7.setId(7+j+1);
		amodel7.setName1("管理费用（300盒以下）");
		
		ResultReportModel amodel8=new ResultReportModel();
		amodel8.setId(8+j+1);
		amodel8.setName1("实际利润");
		
		ResultReportModel amodel9=new ResultReportModel();
		amodel9.setId(9+j+1);
		amodel9.setName1("期初人数");
		
		ResultReportModel amodel10=new ResultReportModel();
		amodel10.setId(10+j+1);
		amodel10.setName1("期末人数");
		
		ResultReportModel amodel11=new ResultReportModel();
		amodel11.setId(11+j+1);
		amodel11.setName1("月平均人数:（期初+期末）/2");
		
		ResultReportModel amodel12=new ResultReportModel();
		amodel12.setId(12+j+1);
		amodel12.setName1("单笔累计赠送超标金额");
		
		ResultReportModel amodel13=new ResultReportModel();
		amodel13.setId(13+j+1);
		amodel13.setName1("整体考量赠送超标金额");
		if(monthInfo_ResultMap1==null)
		{
			amodel7.setName2("-");
			amodel8.setName2(monthProfit.toString());
			amodel9.setName2("-");
			amodel10.setName2("-");
			amodel11.setName2("-");
			amodel12.setName2("-");
			amodel13.setName2("-");				
		}
		else{
			if(monthInfo_ResultMap1.get("manageCost").equals("0")){
				amodel7.setName2("-");
				amodel8.setName2((monthProfit.subtract(new BigDecimal(monthInfo_ResultMap1.get("manageCost").toString()))).toString());
			}
			else{
				amodel7.setName2(monthInfo_ResultMap1.get("manageCost").toString());
				amodel8.setName2((monthProfit.subtract(new BigDecimal(monthInfo_ResultMap1.get("manageCost").toString()))).toString());
			}
			if(monthInfo_ResultMap1.get("earlyNumber").equals("0")){
				amodel9.setName2("-");
			}
			else{
				amodel9.setName2(monthInfo_ResultMap1.get("earlyNumber").toString());
			}
			if(monthInfo_ResultMap1.get("finalNumber").equals("0")){
				amodel10.setName2("-");
			}
			else{
				
			}
			if(monthInfo_ResultMap1.get("earlyNumber").equals("0")&&monthInfo_ResultMap1.get("finalNumber").equals("0")){
				amodel11.setName2("-");
			}
			else{
				BigDecimal a1=new BigDecimal(monthInfo_ResultMap1.get("earlyNumber").toString());
				BigDecimal a2=new BigDecimal(monthInfo_ResultMap1.get("finalNumber").toString());
				BigDecimal a3=a1.add(a2);
				amodel11.setName2(a3.divide(new BigDecimal(2),2, BigDecimal.ROUND_HALF_UP).toString());
			}
			if(monthInfo_ResultMap1.get("singleExcessAmount").equals("0")){
				amodel12.setName2("-");
			}
			else{
				amodel12.setName2(monthInfo_ResultMap1.get("singleExcessAmount").toString());
			}
			if(monthInfo_ResultMap1.get("overallExcessAmount").equals("0")){
				amodel13.setName2("-");
			}
			else{
				amodel13.setName2(monthInfo_ResultMap1.get("overallExcessAmount").toString());
			}
		}			
		aresultList.add(amodel7);
		aresultList.add(amodel8);
		aresultList.add(amodel9);
		aresultList.add(amodel10);
		aresultList.add(amodel11);
		aresultList.add(amodel12);
		aresultList.add(amodel13);

		Map<String, Object> getMinisterParams = new HashMap<String, Object>();			
		getMinisterParams.put("salesDepartmentID",salesDepartmentID);
		getMinisterParams.put("role",3);			
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("where",getMinisterParams);			
		List<EmployeeInfo> ministerList=employeeInfoService.selectParam(whereMap);
		if(ministerList.size()==0){
			params1.put("ministerList",null);
		}
		else{
			params1.put("ministerList",ministerList);
		}
		HashMap salesVolumeCount_ResultMap=receiptInfoService.selectSalesVolume1(params1);			
		ResultReportModel amodel14=new ResultReportModel();
		amodel14.setId(14+j+1);
		amodel14.setName1("签约部长个人销量（盒）");
		BigDecimal s1=new BigDecimal(0);
		BigDecimal s2=new BigDecimal(0);
		if(salesVolumeCount_ResultMap==null){
			amodel14.setName2("-");
		}
		else{
			if(salesVolumeCount_ResultMap.get("sumReceiptAmount").equals("0")){
				amodel14.setName2("-");
			}
			else{
				s1=new BigDecimal(salesVolumeCount_ResultMap.get("sumReceiptAmount").toString());
				s2=s1.divide(milkPrice,0, BigDecimal.ROUND_HALF_UP);
				amodel14.setName2(s2.toString());
			}
		}
		aresultList.add(amodel14);
		
		
		ResultReportModel amodel15=new ResultReportModel();
		amodel15.setId(15+j+1);
		amodel15.setName1("签约部长个人占比%");
		if(s2.toString().equals("0"))
		{
			amodel15.setName2("-");
		}
		else{
			amodel15.setName2((s2.divide(numCount)).multiply(new BigDecimal(100))+"%");
		}
		aresultList.add(amodel15);
		
		HashMap newCustomerCount_ResultMap=orderInfoService.selectNewCustomer1(params1);			
		ResultReportModel amodel16=new ResultReportModel();
		amodel16.setId(16+j+1);
		amodel16.setName1("本月新资源户数");
		if(newCustomerCount_ResultMap==null||newCustomerCount_ResultMap.get("newCustomer").equals("0")){
			amodel16.setName2("-");
		}
		else{
			amodel16.setName2(newCustomerCount_ResultMap.get("newCustomer").toString());
		}
		aresultList.add(amodel16);
		
		HashMap newCustomerSalesVolume_ResultMap=receiptInfoService.selectNewCustomerSalesVolume1(params1);
		
		ResultReportModel amodel17=new ResultReportModel();
		amodel17.setId(17+j+1);
		amodel17.setName1("本月新资源盒数");
		if(newCustomerSalesVolume_ResultMap==null||newCustomerSalesVolume_ResultMap.get("newCustomerSalesVolume").equals(0)){
			amodel17.setName2("-");
		}
		else{
			BigDecimal n1=new BigDecimal(newCustomerSalesVolume_ResultMap.get("newCustomerSalesVolume").toString());
			BigDecimal n2=n1.divide(milkPrice,0, BigDecimal.ROUND_HALF_UP);
			amodel17.setName2(n2.toString());
		}
		
		aresultList.add(amodel17);		
		jsonObjectContent1.put("content", aresultList);
		
		jsonArrray.add(jsonObjectContent1);
		
		//System.out.println(jsonArrray);
		return jsonArrray;//gson.toJson(list);
	}
	
	public List<UserInfo> getLastLevelUser(UserInfo userInfo)
	{
		SalesDepartmentInfo sdept=salesDepartmentInfoService.selectPK(userInfo.getSalesDepartmentID());
		if(sdept.getLevel()==1)
		{
			
		}
		
		return null;
	}
	

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
	
	private BigDecimal getMilkPrice()
	{
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("productName", "牛初乳");		
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap); //放到Map中去，"where"是key,"whereMap"是value,代表SQL语句where后面的条件
		List<ProductInfo> list=productInfoService.selectParam(params);
		if(list.size()!=0)
		{
			return list.get(0).getProductPrice();
		}
		else
		{
			return null;
		}		
	} 
	
	private List<ExpenseItem> getExpenseItem(){
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("level", 2);		
		
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("where", whereMap);
		return expenseItemService.selectParam(params);
	}
	
	private List<ResultReportModel> getResultReportItem(List<ExpenseItem> expenseItemList,BigDecimal profit){
		List<ResultReportModel> ItemList=new ArrayList<ResultReportModel>();
		ResultReportModel model1=new ResultReportModel();
		model1.setId(1);
		model1.setName1("回款折合牛初乳数量");
		model1.setName2("回款折合牛初乳数量");
		ItemList.add(model1);
		
		ResultReportModel model2=new ResultReportModel();
		model2.setId(2);
		model2.setName1("回款金额");
		model2.setName2("回款金额");
		ItemList.add(model2);
		
		ResultReportModel model3=new ResultReportModel();
		model3.setId(3);
		model3.setName1("费用(销售额的"+profit.multiply(new BigDecimal(100))+"%)");
		model3.setName2("费用(销售额的"+profit.multiply(new BigDecimal(100))+"%)");
		ItemList.add(model3);
		
		for(int i=0;i<expenseItemList.size();i++){
			ResultReportModel model_Item=new ResultReportModel();
			model_Item.setId(3+i+1);
			model_Item.setName1(expenseItemList.get(i).getExpenseItem());
			model_Item.setName2(expenseItemList.get(i).getExpenseItem());
			ItemList.add(model_Item);
		}
		
		ResultReportModel model4=new ResultReportModel();
		model4.setId(1);
		model4.setName1("费用合计");
		model4.setName2("费用合计");
		ItemList.add(model4);
		
		ResultReportModel model5=new ResultReportModel();
		model5.setId(1);
		model5.setName1("费销比");
		model5.setName2("费销比");
		ItemList.add(model5);
		
		ResultReportModel model6=new ResultReportModel();
		model6.setId(1);
		model6.setName1("当月计提利润");
		model6.setName2("当月计提利润");
		ItemList.add(model6);
		
		ResultReportModel model7=new ResultReportModel();
		model7.setId(1);
		model7.setName1("管理费用（300盒以下）");
		model7.setName2("管理费用（300盒以下）");
		ItemList.add(model7);
		
		ResultReportModel model8=new ResultReportModel();
		model8.setId(1);
		model8.setName1("实际利润");
		model8.setName2("实际利润");
		ItemList.add(model8);
		
		ResultReportModel model9=new ResultReportModel();
		model9.setId(1);
		model9.setName1("期初人数");
		model9.setName2("期初人数");
		ItemList.add(model9);
		
		ResultReportModel model10=new ResultReportModel();
		model10.setId(1);
		model10.setName1("期末人数");
		model10.setName2("期末人数");
		ItemList.add(model10);
		
		ResultReportModel model11=new ResultReportModel();
		model11.setId(1);
		model11.setName1("月平均人数:（期初+期末）/2");
		model11.setName2("月平均人数:（期初+期末）/2");
		ItemList.add(model11);
		
		ResultReportModel model12=new ResultReportModel();
		model12.setId(1);
		model12.setName1("单笔累计赠送超标金额");
		model12.setName2("单笔累计赠送超标金额");
		ItemList.add(model12);
		
		ResultReportModel model13=new ResultReportModel();
		model13.setId(1);
		model13.setName1("整体考量赠送超标金额");
		model13.setName2("整体考量赠送超标金额");
		ItemList.add(model13);
		
		ResultReportModel model14=new ResultReportModel();
		model14.setId(1);
		model14.setName1("签约部长个人销量（盒）");
		model14.setName2("签约部长个人销量（盒）");
		ItemList.add(model14);
		
		ResultReportModel model15=new ResultReportModel();
		model15.setId(1);
		model15.setName1("签约部长个人占比%");
		model15.setName2("签约部长个人占比%");
		ItemList.add(model15);
		
		ResultReportModel model16=new ResultReportModel();
		model16.setId(1);
		model16.setName1("本月新资源户数");
		model16.setName2("本月新资源户数");
		ItemList.add(model16);
		
		ResultReportModel model17=new ResultReportModel();
		model17.setId(1);
		model17.setName1("本月新资源盒数");
		model17.setName2("本月新资源盒数");
		ItemList.add(model17);
		
		
		return ItemList;
	}
}
