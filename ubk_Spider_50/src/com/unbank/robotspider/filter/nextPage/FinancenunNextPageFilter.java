package com.unbank.robotspider.filter.nextPage;

import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class FinancenunNextPageFilter extends NextPageBaseFilter {

	private String domain[] = new String[] { "www.financeun.com" };

	public FinancenunNextPageFilter() {
		for (int i = 0; i < domain.length; i++) {
			NextPageFilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Integer extractMaxPageNum(Document document) {
		Elements scriptElements = document.body().select("#Content > div > a");
		if (scriptElements.size() < 2) {
			return 0;
		}

		Element element = scriptElements.get(scriptElements.size() - 2);
		if (element == null) {
			return 0;
		}
		String countPageString = StringUtils.substringBetween(element.text()
				.trim(), "[", "]");
		if (StringUtils.isNumeric(countPageString)) {
			return Integer.parseInt(countPageString);
		}
		return 0;

	}

	@Override
	public TreeMap<Integer, String> extractNextPageUrlMap(Document document,
			String baseUrl) {
		int maxPage = extractMaxPageNum(document);
		// http://www.financeun.com/News/20141019/2013cfn/75226382901.shtml
		String temp[] = baseUrl.split("0.shtml");
		String preUrl = temp[0];
		TreeMap<Integer, String> nextPageUrlMap = new TreeMap<Integer, String>();
		for (int i = 1; i < maxPage; i++) {
			nextPageUrlMap.put(i, preUrl + i + ".shtml");
		}
		return nextPageUrlMap;
	}

}
