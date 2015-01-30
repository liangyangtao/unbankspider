package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Component
public class ComDzwww extends BaseFilter {
	private String domain1 = "finance.dzwww.com";
	private String domain2 = "sd.dzwww.com";

	public ComDzwww() {
		FilterLocator.getInstance().register(domain1, this);
		FilterLocator.getInstance().register(domain2, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("div.TRS_Editor").first();
		
		removeNoNeedTextElement(element, "原标题");
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
		if(str.contains("本稿件所含文字")){
			str=str.substring(0, str.indexOf("本稿件所含文字"));
		}
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
