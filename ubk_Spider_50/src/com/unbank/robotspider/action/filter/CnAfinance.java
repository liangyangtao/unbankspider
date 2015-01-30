package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class CnAfinance extends BaseFilter {
	private String domain = "www.afinance.cn";

	public CnAfinance() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document.select("#zuo003").first();
		String cssQuerys[] = new String[] { "#hzh", "#nextpage", "#pages" };
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
		// 　　……
		str = str.replace("……", "");
		String[] spechars = new String[] { "相关专题" };
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
