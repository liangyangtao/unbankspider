package com.unbank.robotspider.filter.nextPage;

import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CsNextPageFilter extends NextPageBaseFilter {

	private String domain = "www.cs.com.cn";

	public CsNextPageFilter() {
		NextPageFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Integer extractMaxPageNum(Document document) {
		Elements scriptElements = document.body().select("script");
		String countPageString = null;
		for (Element element : scriptElements) {
			if (element.toString().contains("countPage")) {
				countPageString = StringUtils.substringBetween(
						element.toString(), "var countPage =", "//共多少页");

			}
		}
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
		// http://www.cs.com.cn/xwzx/cj/201407/t20140707_4439084.html
		String temp[] = baseUrl.split(".html");
		String preUrl = temp[0];
		TreeMap<Integer, String> nextPageUrlMap = new TreeMap<Integer, String>();
		for (int i = 1; i < maxPage; i++) {
			nextPageUrlMap.put(i, preUrl + "_" + i + ".html");
		}
		return nextPageUrlMap;
	}

}
