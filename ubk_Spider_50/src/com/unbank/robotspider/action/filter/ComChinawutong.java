package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Component
public class ComChinawutong extends BaseFilter {
	private String domain[] = new String[] { "www.chinawutong.com",
			"m.chinawutong.com", "vip.chinawutong.com", "help.chinawutong.com",
			"news.chinawutong.com", "bbs.chinawutong.com",
			"news.chinawutong.comhttp", "jm.chinawutong.com" };

	public ComChinawutong() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select(".newsdetails").first();
		String cssQuerys[] = new String[] { "div.cl", ".fy" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}
		String textQuerys[] = new String[] { "原标题", "相关:", "作者：" };
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
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
