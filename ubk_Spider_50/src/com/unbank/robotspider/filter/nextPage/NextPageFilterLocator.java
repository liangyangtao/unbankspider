package com.unbank.robotspider.filter.nextPage;

import java.util.HashMap;

import com.unbank.robotspider.filter.FilterLocator;

public class NextPageFilterLocator extends FilterLocator {
	private static NextPageFilterLocator nextPageFilterLocator = new NextPageFilterLocator();
	HashMap<String, NextPageFilter> filters = new HashMap<String, NextPageFilter>();

	NextPageBaseFilter nextPageBaseFilter = new NextPageBaseFilter();

	public NextPageFilter getFilter(String url) {
		String host = getDomain(url);
		if (filters.containsKey(host)) {
			return filters.get(host);
		}
		return nextPageBaseFilter;
	}

	public void register(String host, NextPageFilter filter) {
		filters.put(host, filter);
	}

	public void unregister(String host) {
		filters.remove(host);
	}

	private NextPageFilterLocator() {
	}

	public static NextPageFilterLocator getInstance() {
		return nextPageFilterLocator;
	}

}
