package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComSun21 extends BaseFilter {
	private String domain = "news.21-sun.com";

	public ComSun21() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("#mainDetail").first();
		removeNoNeedElement(element, "div.listfoot01");
		removeNoNeedElement(element, ".detail_news_keywords");
		removeNoNeedElement(element, "#ckepop");
		removeNoNeedElement(element, "style");
		removeNoNeedElement(element, "script");
		removeNoNeedTextElement(element, "详情");
		return element;

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
		return super.replaceStockCode(content);
	}

	@Override
	public String replaceSpechars(String content) {

		String str = super.replaceSpechars(content);

		String[] spechars = new String[] { "(本文来自", "返回首页",
				"(<strong>点击进入</strong>" };
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
