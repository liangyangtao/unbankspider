package com.unbank.robotspider.filter.nextPage;

import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class FlleasingNextPageFilter extends NextPageBaseFilter {

	private String domain[] = new String[] { "www.flleasing.com" };

	public FlleasingNextPageFilter() {
		for (int i = 0; i < domain.length; i++) {
			NextPageFilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Integer extractMaxPageNum(Document document) {
		Elements scriptElements = document.body().select(
				"#BodyLabel > p:nth-child(2)");
		Element element = scriptElements.last();
		if (element == null) {
			return 0;
		}
		Elements elements = element.select("a");
		String countPageString = elements.get(elements.size() - 2).text()
				.trim();
		countPageString = StringUtils.substringBetween(countPageString, "[",
				"]");

		if (StringUtils.isNumeric(countPageString)) {
			return Integer.parseInt(countPageString);
		}
		return 0;

	}

	@Override
	public TreeMap<Integer, String> extractNextPageUrlMap(Document document,
			String baseUrl) {
		int maxPage = extractMaxPageNum(document);
		// http://www.flleasing.com/onews.asp?id=9644

		// http://www.flleasing.com/onews.asp?id=9644&Page=3
		TreeMap<Integer, String> nextPageUrlMap = new TreeMap<Integer, String>();
		for (int i = 2; i <= maxPage; i++) {
			nextPageUrlMap.put(i, baseUrl + "&Page=" + i);
		}
		return nextPageUrlMap;
	}

}
