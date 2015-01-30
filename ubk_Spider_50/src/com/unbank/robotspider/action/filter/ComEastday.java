package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComEastday extends BaseFilter {
	private String domain = "finance.eastday.com";

	public ComEastday() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		return document.getElementById("zw");
	}

	@Override
	public void formatElements(Element maxTextElement) {
		Element element = maxTextElement.getElementById("pageindex");
		if (element != null) {
			element.remove();
		}
		super.formatElements(maxTextElement);
	}

	@Override
	public void saveImage(Element maxTextElement, String url) {
		super.saveImage(maxTextElement, url);
	}

	@Override
	public String replaceStockCode(String content) {
		return super.replaceStockCode(content);
	}

	@Override
	public String replaceSpechars(String content) {
		String str = super.replaceSpechars(content);
		str = str.replaceAll("<p>原标题:.*?</p>", "");
		int index = str.indexOf("今日财经热点资讯：");
		if (index > 0) {
			return str.substring(0, index);
		} else
			return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
