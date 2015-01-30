package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;
import com.unbank.robotspider.fetch.JsoupNetFetcher;

@Service
public class CnOrgTreasurer extends BaseFilter {
	private String domain = "finance.treasurer.org.cn";

	public CnOrgTreasurer() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
//		http://finance.treasurer.org.cn/webinfosmains/index/show/134952.
		String temp [] =  url.split(".html");
		url =temp[0]+ "-1.html"; 
		document = new JsoupNetFetcher().fetchText(url);
		Element element = document.select("#show_contents").first();
		String cssQuerys[] = new String[] { "#show_contents_advesimgs",
				"#im_pages" };
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
