package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComJschina extends BaseFilter {
	private String domain[] = new String[] { "economy.jschina.com.cn",
			"jsnews.jschina.com.cn" };

	public ComJschina() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		return super.removeNoNeedHtmlElement(url, document, title);
	}

	@Override
	public void formatElements(Element maxTextElement) {
		maxTextElement.select("div#news_more_page_div_id").remove();
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
		int index = str.indexOf("原标题");
		if (index > 0) {
			return str.substring(0, index);
		} else
			return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

	// 得到某元素下孩子节点长度最大的Element
	public Element getMaxTextElement(String url, String title,
			Element parentElement) {
		if (parentElement == null) {
			return null;
		}
		Element temp = parentElement.select("div#content").first();
		return temp;
	}

}
