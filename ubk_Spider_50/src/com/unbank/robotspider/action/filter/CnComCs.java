package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class CnComCs extends BaseFilter {
	private String domain = "www.cs.com.cn";

	public CnComCs() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document.select("#ozoom1").first();
		Element e= element.getElementsByTag("p").first();
		if(e.text().contains("博文链接：")){
			e.remove();
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

		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

	public void removeAdvertiseText(Element maxTextElement) {

	}

}
