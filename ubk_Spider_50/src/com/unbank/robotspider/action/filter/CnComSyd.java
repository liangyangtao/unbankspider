package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class CnComSyd extends BaseFilter {
	private String domain[] = new String[] { "auto.syd.com.cn",
			"caijing.syd.com.cn", "cate.syd.com.cn", "edu.syd.com.cn",
			"epaper.syd.com.cn", "finance.syd.com.cn", "financing.syd.com.cn",
			"fun.syd.com.cn", "health.syd.com.cn", "house.syd.com.cn",
			"jiaju.syd.com.cn", "lz.syd.com.cn", "msweibo.syd.com.cn",
			"news.syd.com.cn", "she.syd.com.cn", "sns.syd.com.cn",
			"sports.syd.com.cn", "syjjq.syd.com.cn", "syjs.syd.com.cn",
			"travel.syd.com.cn", "wenhua.syd.com.cn", "www.syd.com.cn" };

	public CnComSyd() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select(".new_content").first();
		String cssQuerys[] = new String[] { "script",
				"span[style=display:none]" };
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
