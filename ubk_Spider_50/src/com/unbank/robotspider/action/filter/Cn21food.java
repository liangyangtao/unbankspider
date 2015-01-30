package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class Cn21food extends BaseFilter {
	private String domain = "www.21food.cn";

	public Cn21food() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document
				.select(".read-content > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1)")
				.first();

		String cssQuerys[] = new String[] { ".inset-bdad", ".hot-ewm" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}
		String textQuerys[] = new String[] { "本文仅代表作者个人观点", "原标题：", "来源:",
				"原标题:", "来源：" };
		for (String string : textQuerys) {
			removeNoNeedTextElement(element, string);
		}
		return element;
	}

	@Override
	public void formatElements(Element maxTextElement) {
		super.formatElements(maxTextElement);
		Elements elements = maxTextElement.getElementsByTag("table");
		for (int i = 0; i < elements.size(); i++) {
			String str = elements.get(i).text().trim();
			if ("".equals(str)) {

				if (elements.get(i) != null) {
					elements.get(i).remove();
				}
			}
		}
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
		int index2 = str.indexOf("推荐阅读");
		if (index2 > 0) {
			str = str.substring(0, index2);
		}

		int index = str.indexOf("<p>如果觉得本文不错，请：微信 更多</p>");
		if (index > 0) {
			return str.substring(0, index);
		} else {
			return str;
		}
	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
