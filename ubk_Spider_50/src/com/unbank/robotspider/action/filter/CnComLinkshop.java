package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class CnComLinkshop extends BaseFilter {
	private String domain = "www.linkshop.com.cn";

	public CnComLinkshop() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Elements a_elements= document.getElementsByTag("a");
		
		for(Element e:a_elements){
			if("报告下载".equals(e.text())){
				e.remove();
			}
		}
		return document
				.select("body > div.content > div.contentLeft > div.articleBlock > div.articleMain > div.articleContent")
				.first();
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
		str = str.replace("报告全文下载", "");
		if (str.contains("(版权归")) {
			int index = str.indexOf("(版权归");
			str = str.substring(0, index);
		}
		if (str.contains("(联商网特约评论员")) {
			int index = str.indexOf("(联商网特约评论员");
			str = str.substring(0, index);
		}
		return str;
	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
