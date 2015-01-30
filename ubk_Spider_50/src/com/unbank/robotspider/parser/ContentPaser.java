package com.unbank.robotspider.parser;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import org.jsoup.nodes.Document;

import com.unbank.robotspider.action.model.normal.Filter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

public class ContentPaser extends BasePaser {

	public String doParse(String url, Document document, String title) {
		if (document == null) {
			return null;
		}
		Filter filter = FilterLocator.getInstance().getFilter(url);
		String content = filter.getContent(url, document, title);
		if (content == null) {
			return null;
		}
		content = removeSingleQuotes(content);
		return content;
	}

	public String extractNewsContent(String url, Document document, String title) {
		NextPageUrlPaser npuf = new NextPageUrlPaser(document, url);
		if (npuf.hasNextPage()) {
			StringBuffer sb = new StringBuffer();
			String content = doParse(url, document, title);
			if (content == null) {
				return null;
			}
			sb.append(content);
			TreeMap<Integer, String> urls = npuf.getNextPageUrlMap();
			Set<Integer> set = urls.keySet();
			Iterator<Integer> iterator = set.iterator();
			while (iterator.hasNext()) {
				Integer key = iterator.next();
				String u = urls.get(key);
				Document doc = getDocumentByURL(u);
				if (doc == null) {
					return null;
				} else {
					String content2 = doParse(u, doc, title);
					if (content2 == null) {
						return null;
					}
					sb.append(content2);
				}
			}
			return sb.toString();
		} else {
			return doParse(url, document, title);
		}
	}

}
