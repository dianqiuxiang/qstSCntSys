package com.qst.scnt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qst.scnt.model.CustomerInfo;
import com.qst.scnt.service.CustomerInfoService;

@Controller
@RequestMapping(value="/customer")
public class CustomerAction extends BaseAction {
	
	@Resource
	private CustomerInfoService customerInfoService;
	
	
	@RequestMapping(value="/select.do")
	@ResponseBody
	public Object select(String cName,String cPhone) {
		
		Gson gson = new Gson();
		Map<String, Object> whereMap = new HashMap();
		if(cName!=null&&cPhone!=null)
		{
			whereMap.put("customerName", "%"+cName+"%");	 
		    whereMap.put("customerPhone", "%"+cPhone+"%");
		}
		else
		{
			whereMap.put("customerName", "%%");	 
		    whereMap.put("customerPhone", "%%");
		}
		Map<String, Object> params = new HashMap();  
		params.put("where", whereMap);
		List<CustomerInfo> list=customerInfoService.select();
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}

}
