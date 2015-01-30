package com.unbank.robotspider.action.filter;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;
import com.unbank.robotspider.fetch.Fetcher;
import com.unbank.robotspider.fetch.JsoupNetFetcher;

@Component
public class ComChina5e extends BaseFilter {
	private String domain = "www.china5e.com";

	public ComChina5e() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		if (document.select(".havepage").size() == 1) {
			document = changeDocument(url);
		}

		Element element = document.select("#showcontent").first();
		String cssQuerys[] = new String[] { ".authorInfo", ".nextArictle",
				".havepage", ".Pagination" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}
		return element;
	}

	private Document changeDocument(String url) {
		Document document;
		String newsNum = StringUtils.substringBetween(url, "news-", "-");
		String fullUrl = "http://www.china5e.com/index.php?m=content&c=index&a=show&catid=13&id="
				+ newsNum + "&page=10000";
		Fetcher fetcher = new JsoupNetFetcher();
		document = fetcher.fetchText(fullUrl);
		return document;
	}

	@Override
	public void formatElements(Element maxTextElement) {
		super.formatElements(maxTextElement);
	}

	@Override
	public void saveImage(Element maxTextElement, String url) {

		super.saveImage(maxTextElement, url);

	}

	@Override
	public String replaceStockCode(String content) {
		String str = super.replaceStockCode(content);
		str = str.replace("请点击“中国石油出口没什么不正常”：", "");
		str = str.replace("http://news.315.com.cn/20140716/100069910.html", "");
		return str;
	}

	@Override
	public String replaceSpechars(String content) {
		String str = super.replaceSpechars(content);

		String[] spechars = new String[] { "责任编辑", "更多热点" };
		for (String string : spechars) {
			int index = str.indexOf(string);
			if (index > 0) {
				str = str.substring(0, index);
			}
		}
		return str;
	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
