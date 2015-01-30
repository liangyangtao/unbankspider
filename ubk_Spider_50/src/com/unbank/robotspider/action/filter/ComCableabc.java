package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComCableabc extends BaseFilter {
	private String domain[] = new String[] { "news.cableabc.com",
			"material.cableabc.com" };

	public ComCableabc() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("#divcontent").first();
		String[] cssQuerys = new String[] { "div.clearfix", "div.editor1",
				"style" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}
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
		if (str.contains("本稿件所含文字")) {
			str = str.substring(0, str.indexOf("本稿件所含文字"));
		}
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
