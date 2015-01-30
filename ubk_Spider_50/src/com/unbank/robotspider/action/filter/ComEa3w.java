package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;
import com.unbank.robotspider.fetch.JsoupNetFetcher;

@Service
public class ComEa3w extends BaseFilter {
	private String domain = "tv.ea3w.com";

	public ComEa3w() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Elements elements = document.select(".read-all");
		if (elements.size() != 0) {
			String temp[] = url.split(".html");
			url = temp[0] + "_all.html";
			document = new JsoupNetFetcher().fetchText(url);
		}
		Element element = document.select("#main_content").first();
		String cssQuerys[] = new String[] { "#ad_incontent", ".page",
				".read-all", "#lr", "#respon_page" };
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
		return str;
	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
