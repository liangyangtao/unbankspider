package com.unbank.robotspider.filter.nextPage;

import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CcidnetNextPageFilter extends NextPageBaseFilter {

	private String domain[] = new String[] { "miit.ccidnet.com",
			"news.ccidnet.com" };

	public CcidnetNextPageFilter() {
		for (int i = 0; i < domain.length; i++) {
			NextPageFilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Integer extractMaxPageNum(Document document) {
		Elements scriptElements = document.body().select("h5 > a.content01");
		Element element = scriptElements.last();
		if (element == null) {
			return 0;
		}
		String countPageString = element.text().trim();
		if (StringUtils.isNumeric(countPageString)) {
			return Integer.parseInt(countPageString);
		}
		return 0;

	}

	@Override
	public TreeMap<Integer, String> extractNextPageUrlMap(Document document,
			String baseUrl) {
		int maxPage = extractMaxPageNum(document);
		// http://miit.ccidnet.com/art/32559/20140825/5585921_1.html
		String temp[] = baseUrl.split("1.html");
		String preUrl = temp[0];
		TreeMap<Integer, String> nextPageUrlMap = new TreeMap<Integer, String>();
		for (int i = 2; i <= maxPage; i++) {
			nextPageUrlMap.put(i, preUrl + i + ".html");
		}
		return nextPageUrlMap;
	}

}
