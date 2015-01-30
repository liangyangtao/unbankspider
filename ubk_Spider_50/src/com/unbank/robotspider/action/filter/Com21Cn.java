package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Component
public class Com21Cn extends BaseFilter {

	private String domain[] = new String[] { "finance.21cn.com" };

	public Com21Cn() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element elements = document.select("div#article_text").first();
		return elements;

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
		str=str.replace("[ 查看全文 ]", "");
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
