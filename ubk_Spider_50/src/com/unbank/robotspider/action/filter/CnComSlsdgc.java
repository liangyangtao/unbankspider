package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class CnComSlsdgc extends BaseFilter {
	private String domain = "www.slsdgc.com.cn";

	public CnComSlsdgc() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		return document.select("#endtext").first();
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
		String[] spechars = new String[] { "原标题", "网址链接" };
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
