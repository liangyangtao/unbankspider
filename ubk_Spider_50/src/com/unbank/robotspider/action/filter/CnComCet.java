package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class CnComCet extends BaseFilter {
	private String domain = "www.cet.com.cn";

	public CnComCet() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select(".article_content").first();
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
		return super.replaceSpechars(content);
	}

	public String removeNoNeedChars(String content) {
		String[] spechars = new String[] { "免责声明", "版权声明", "【重点推荐", "【推荐阅读",
				"【相关阅读", "【免责声明", "【版权声明", "【更多详情", "【相关专题" };
		for (String string : spechars) {
			int index = content.indexOf(string);
			if (index > 0) {
				content = content.substring(0, index);
			}
		}
		return content;
	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
