package com.ibs.lteplatform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibs.lteplatform.service.EhcacheService;

@Controller
@RequestMapping(value="/ehcache")
public class EhcacheController {

	@Autowired
	private EhcacheService ehcacheService;
	
	@RequestMapping(value="/selectData.html")
	public void selectData(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("key", request.getParameter("key"));
		List<String> list = ehcacheService.selectData(request.getParameter("key"));
		for(String s:list){
			System.out.println(s);
		}
	}
	
	@RequestMapping(value="/delteData.html")
	public void delteData(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("key", request.getParameter("key"));
		ehcacheService.delteData(request.getParameter("key"));
	}
	
}
