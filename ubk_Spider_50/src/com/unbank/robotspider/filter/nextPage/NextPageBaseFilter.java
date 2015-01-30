package com.unbank.robotspider.filter.nextPage;

import java.util.TreeMap;

import org.jsoup.nodes.Document;

public class NextPageBaseFilter implements NextPageFilter {

	@Override
	public Integer extractMaxPageNum(Document document) {

		return null;
	}

	@Override
	public TreeMap<Integer, String> extractNextPageUrlMap(Document document,
			String baseUrl) {

		return new TreeMap<Integer, String>();
	}

}
