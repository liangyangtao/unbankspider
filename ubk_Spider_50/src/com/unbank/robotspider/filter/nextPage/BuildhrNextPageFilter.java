package com.unbank.robotspider.filter.nextPage;

import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class BuildhrNextPageFilter extends NextPageBaseFilter {

	private String domain = "news.buildhr.com";

	public BuildhrNextPageFilter() {
		NextPageFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Integer extractMaxPageNum(Document document) {

		Elements scriptElements = document.body().select(".pagecode");

		if (scriptElements.size() == 0) {
			return 0;
		}

		String countPageString = null;
		Elements elements = scriptElements.first().select("a");
		if (elements.size() == 0) {
			return 0;
		}
		countPageString = elements.get(elements.size() - 2).text();

		countPageString = StringUtils.strip(countPageString);
		if (StringUtils.isNumeric(countPageString)) {
			return Integer.parseInt(countPageString);
		}
		return 0;

	}

	@Override
	public TreeMap<Integer, String> extractNextPageUrlMap(Document document,
			String baseUrl) {
		int maxPage = extractMaxPageNum(document);
		String temp[] = baseUrl.split("0.html");
		String preUrl = temp[0];
		TreeMap<Integer, String> nextPageUrlMap = new TreeMap<Integer, String>();
		for (int i = 1; i < maxPage; i++) {
			nextPageUrlMap.put(i, preUrl + i + ".html");
		}
		return nextPageUrlMap;
	}
}
