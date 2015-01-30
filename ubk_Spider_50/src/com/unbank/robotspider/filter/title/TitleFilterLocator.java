package com.unbank.robotspider.filter.title;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.unbank.robotspider.filter.title.dbfilter.TitleDBFilter;



public class TitleFilterLocator {

	private static TitleFilterLocator filterLocator=new TitleFilterLocator();
	
	HashMap<String, TitleFilter> filters = new HashMap<String, TitleFilter>();
	
	TitleBaseFilter baseFilter =new TitleBaseFilter();
	
	public TitleFilter getFilter(String url) {
		String host=getDomain(url);
		if(filters.containsKey(host)) {
			return filters.get(host);
		}else if(TitleDBFilter.getInstance().getFilters().containsKey(host)){
			return TitleDBFilter.getInstance().getFilters().get(host);
		}
		return baseFilter;
	}
	
	public void register(String host, TitleFilter filter) {
		filters.put(host, filter);
	}

	public void unregister(String host) {
		filters.remove(host);
	}
	
	private TitleFilterLocator(){}
	
	public static TitleFilterLocator getInstance(){
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
