package com.unbank.robotspider.filter.nextPage;

import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

public interface NextPageFilter {
	public Logger logger = Logger.getLogger(NextPageFilter.class);

	public Integer extractMaxPageNum(Document document);

	public TreeMap<Integer, String> extractNextPageUrlMap(Document document,
			String baseUrl);

}
