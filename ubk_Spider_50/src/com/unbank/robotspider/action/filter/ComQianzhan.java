package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComQianzhan extends BaseFilter {
	private String domain[] = new String[] { "www.qianzhan.com" };

	public ComQianzhan() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("#div_content").first();

		String cssQuerys[] = new String[] { ".bdr",
				"a[href=http://www.qianzhan.com/]" };
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
		str = str.replace("本文来源前瞻网，未经前瞻网书面授权，禁止转载，违者将被追究法律责任！", "");
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
