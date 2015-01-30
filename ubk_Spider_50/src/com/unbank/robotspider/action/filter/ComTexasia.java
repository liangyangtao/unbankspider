package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComTexasia extends BaseFilter {
	private String domain = "www.tex-asia.com";

	public ComTexasia() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		return super.removeNoNeedHtmlElement(url, document, title);
	}

	@Override
	public void formatElements(Element maxTextElement) {
		super.formatElements(maxTextElement);
//		Elements elements = maxTextElement.getElementsByTag("table");
//		for (int i = 0; i < elements.size(); i++) {
//			String str = elements.get(i).text().trim();
//			if ("".equals(str)) {
//				elements.get(i).remove();
//			}
//		}

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
		str = str.replace("纺织科技网--专业的纺织技术论坛", "");
		str = str.replace("纺织科技网", "");
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
