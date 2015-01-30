package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class CnIresearch extends BaseFilter {

	String[] domain = new String[] { "column.iresearch.cn" };

	public CnIresearch() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document.select("div.content_Article").first();
		element.select("div.review").remove();
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
		String[] subStr={"订阅我的RSS"};
		for(String  s:subStr){
			if(str.contains(s)){
				str=str.substring(0, str.indexOf(s));
			}
		}
		return str;
	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
