package com.unbank.robotspider.filter.titlelist;

import java.util.HashMap;

import com.unbank.robotspider.filter.FilterLocator;
import com.unbank.robotspider.filter.titlelist.dbfilter.TitleListDBFilter;

public class TitleListFilterLocator extends FilterLocator {

	private static TitleListFilterLocator filterLocator = new TitleListFilterLocator();

	HashMap<String, TitleListFilter> filters = new HashMap<String, TitleListFilter>();

	BaseTitleListFilter baseFilter = new BaseTitleListFilter();

	public TitleListFilter getFilter(String url) {
		String host = getDomain(url);
		if (filters.containsKey(host)) {
			return filters.get(host);
		}else if(TitleListDBFilter.getInstance().getFilters().containsKey(host)){
			return TitleListDBFilter.getInstance().getFilters().get(host);
		}
		return baseFilter;
	}

	public void register(String host, TitleListFilter filter) {
		filters.put(host, filter);
	}

	public void unregister(String host) {
		filters.remove(host);
	}

	private TitleListFilterLocator() {
	}

	public static TitleListFilterLocator getInstance() {
		return filterLocator;
	}

}
