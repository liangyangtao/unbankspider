package com.unbank.robotspider.filter.content;

import java.util.HashMap;

import com.unbank.robotspider.filter.FilterLocator;

public class ContentFilterLocator extends FilterLocator {

	private static ContentFilterLocator filterLocator = new ContentFilterLocator();

	public static ContentFilterLocator getInstance() {
		return filterLocator;
	}

	HashMap<String, ContentFilter> filters = new HashMap<String, ContentFilter>();

	ContentBaseFilter contentBaseFilter = new ContentBaseFilter();

	public ContentFilter getFilter(String url) {
		String host = getDomain(url);
		if (filters.containsKey(host)) {
			return filters.get(host);
		}
		return contentBaseFilter;
	}

	public void register(String host, ContentFilter filter) {
		filters.put(host, filter);
	}

	public void unregister(String host) {
		filters.remove(host);
	}

}
