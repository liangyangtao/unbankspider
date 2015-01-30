package com.unbank.robotspider.action.model.normal;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class FilterLocator
{
	private static FilterLocator filterLocator=new FilterLocator();
	private HashMap<String, Filter> filters = new HashMap<String, Filter>();
	
	BaseFilter baseFilter =new BaseFilter();
	
	public Filter getFilter(String url) {
		String host=getDomain(url);
		if(filters.containsKey(host)) {
			return filters.get(host);
		}else if(DBFilter.getInstance().getFilters().containsKey(host)){
			return DBFilter.getInstance().getFilters().get(host);
		}
		return baseFilter;
	}
	
	public void register(String host, Filter filter) {
		filters.put(host, filter);
	}

	public void unregister(String host) {
		filters.remove(host);
	}
	
	private FilterLocator(){}
	
	public static FilterLocator getInstance(){
		return filterLocator;
	}
	
	private static String getDomain(String url) {
		String domain = "";
		try {
			URL u = new URL(url);
			domain = u.getHost();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return domain;
	}
}
