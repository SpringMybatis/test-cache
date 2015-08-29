package com.ibs.lteplatform.service;

import java.util.List;

public interface EhcacheService {

	public List<String> selectData(String key);
	
	
	public void delteData(String key);
	
}
