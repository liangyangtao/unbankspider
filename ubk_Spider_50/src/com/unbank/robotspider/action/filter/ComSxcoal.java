package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;
import com.unbank.robotspider.fetch.ImageFilter;
import com.unbank.robotspider.fetch.ImageUrlAndSizeFilter;
import com.unbank.robotspider.fetch.JsoupNetFetcher;

@Service
public class ComSxcoal extends BaseFilter {
	private String domain = "www.sxcoal.com";

	public ComSxcoal() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document.select("#cont").first();
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
		String source = super.replaceStockCode(content);
		source = source.replaceAll("\\(\\d{4}, \\d{2}\\.\\d{2}, \\d{1,2}\\.\\d{2}%\\)", "");
		return source;
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
