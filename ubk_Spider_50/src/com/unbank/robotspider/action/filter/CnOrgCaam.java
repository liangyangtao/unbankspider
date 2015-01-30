package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class CnOrgCaam extends BaseFilter {
	private String domain = "www.caam.org.cn";

	public CnOrgCaam() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document.select(".newstext").first();
		String cssQuerys[] = new String[] { ".newstext > h3", ".timecont",
				"div.clear", ".align_r" };
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
		content = content.replace("提要：", "");
		return super.replaceStockCode(content);
	}

	@Override
	public String replaceSpechars(String content) {
		String str = super.replaceSpechars(content);
		return str;
	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
