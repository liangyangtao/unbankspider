package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComJnlc extends BaseFilter {
	private String domain = "www.jnlc.com";

	public ComJnlc() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("#ctrlfscont").first();
		String textQuerys[] = new String[] { "更多详情", "更多内容请访问", "相关阅读" };
		for (String string : textQuerys) {
			removeNoNeedTextElement(element, string);
		}
		return element;
	}

	@Override
	public void formatElements(Element maxTextElement) {

		Elements elements = maxTextElement.getElementsByTag("a");
		for (Element e : elements) {
			String href = e.attr("href");
			if (href != null && href.endsWith(".pdf")) {
				e.remove();
			}
		}
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
