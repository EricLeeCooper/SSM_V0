package com.panda.realm;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

public class MyFilterChains extends ShiroFilterFactoryBean{
	@Override
	public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {
		Map<String,String> filter = new HashMap<String, String>();
		filter.put("/index", "anon");
		filter.put("/system/**", "roles[管理员]");
		filter.put("/*", "authc");
		super.setFilterChainDefinitionMap(filter);
	}
}
