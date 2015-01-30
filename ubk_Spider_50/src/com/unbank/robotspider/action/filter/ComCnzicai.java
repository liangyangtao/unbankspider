package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComCnzicai extends BaseFilter {
	private String domain = "www.cnzicai.com";

	public ComCnzicai() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		return super.removeNoNeedHtmlElement(url, document, title);
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
		int index = str.indexOf("<p>推荐阅读：</v></p>");
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
