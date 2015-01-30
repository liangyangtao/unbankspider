package com.unbank.robotspider.filter.nextPage;

import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class YuanlinNextPageFilter extends NextPageBaseFilter {

	private String domain = "news.yuanlin.com";

	public YuanlinNextPageFilter() {
		NextPageFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Integer extractMaxPageNum(Document document) {
		Elements scriptElements = document.body().select(".pagebox_num");
		String countPageString = null;
		for (Element element : scriptElements) {
			countPageString = element.text();
		}
		if (StringUtils.isNumeric(countPageString)) {
			return Integer.parseInt(countPageString);
		}
		return 0;

	}

	@Override
	public TreeMap<Integer, String> extractNextPageUrlMap(Document document,
			String baseUrl) {
		int maxPage = extractMaxPageNum(document);
		// http://news.yuanlin.com/detail/201485/191237.htm
		// http://news.yuanlin.com/View/191237/1.htm
		String preUrl = baseUrl.replace("detail", "View");
		preUrl = preUrl.split("View/")[0];
		String temp[] = baseUrl.split("/");
		String detailID = temp[temp.length - 1].split("\\.")[0];
		TreeMap<Integer, String> nextPageUrlMap = new TreeMap<Integer, String>();
		for (int i = 1; i < maxPage; i++) {
			nextPageUrlMap.put(i, preUrl + "View/" + detailID + "/" + i
					+ ".htm");
		}
		return nextPageUrlMap;
	}
}
