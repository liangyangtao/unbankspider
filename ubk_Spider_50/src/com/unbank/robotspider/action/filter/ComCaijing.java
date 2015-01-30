package com.unbank.robotspider.action.filter;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComCaijing extends BaseFilter {
	
	String[] domains={"economy.caijing.com.cn","finance.caijing.com.cn","industry.caijing.com.cn","estate.caijing.com.cn","www.caijing.com.cn"};

	public ComCaijing() {
		for(String s:domains){
			FilterLocator.getInstance().register(s, this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document.select("#the_content").first();

		String cssQuerys[] = new String[] { ".ar_writer", ".ar_keywords",
				"#the_content > div.addon-survey", ".article-page" ,"div#pageNext","select"};
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

		String[] spechars = new String[] { "(资料来源", "(来源" };
		for (String string : spechars) {
			int index = str.indexOf(string);
			if (index > 0) {
				str = str.substring(0, index);
			}
		}
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
