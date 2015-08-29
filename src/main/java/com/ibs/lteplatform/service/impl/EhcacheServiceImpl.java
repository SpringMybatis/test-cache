package com.ibs.lteplatform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ibs.lteplatform.service.EhcacheService;

@Service
public class EhcacheServiceImpl implements EhcacheService {

/*	@Autowired
    private CacheManager cacheManager;
	
	@Autowired
	private EhCacheFactoryBean ehCacheFactoryBean;*/
	
	private static List<String> list1 = new ArrayList<String>();
	private static List<String> list2 = new ArrayList<String>();
	private static Map<String, List<String>> map = new HashMap<String, List<String>>();

	static {
		list1.add("1");
		list1.add("3");
		list1.add("5");
		list1.add("6");
		list2.add("234");
		list2.add("5");
		list2.add("6");
		list2.add("7");
		list2.add("8");
		map.put("1", list1);
		map.put("2", list2);
	}

	
	@CacheEvict(value = "myCache", key = "#key")
	public void delteData(String key) {
		
		System.out.println("shanchu....");
		
		/*Element e = ehCacheFactoryBean.getObject().get("test");
		
		System.out.println(e.getValue());
		
		Cache cache = cacheManager.getCache("OptionsCache");
		
		boolean test = cache.remove("selectData.1");
		
		cacheManager.getCache("OptionsCache").remove("test");*/
	}

	@Cacheable(value = "myCache", key = "#key")
	public List<String> selectData(String key) {
		System.out.println("进入缓存......");
		return map.get(key);
	}

}
