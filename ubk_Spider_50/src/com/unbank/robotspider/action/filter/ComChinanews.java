package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComChinanews extends BaseFilter {
	private String domain[] = new String[] { "finance.chinanews.com",
			"www.chinanews.com" };
	
	// 列表页给出的是第二页，导致第一页和第二页反了，暂不修改
	public ComChinanews() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document.select(".left_zw").first();
		String cssQuerys[] = new String[] { "#adhzh", "#function_code_page",
				"img[src=http://i5.chinanews.com/finance/hqlink.png]", "li" };
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
