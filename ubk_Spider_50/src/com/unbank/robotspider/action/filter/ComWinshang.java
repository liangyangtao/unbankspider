package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComWinshang extends BaseFilter {
	private String domain = "news.winshang.com";

	public ComWinshang() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("#newsdiv").first();
		String[] cssQuerys = new String[] { "#newsdiv", ".newsad" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}
		String[] textQuerys = new String[] { "凡本网注明稿件来自" };
		for (String string : textQuerys) {
			removeNoNeedTextElement(element, string);
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
		String textQuerys[] = new String[] { "(专题阅读)", "(拓展选址信息)" };
		for (String string : textQuerys) {
			str = str.replace(string, "");
		}
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
