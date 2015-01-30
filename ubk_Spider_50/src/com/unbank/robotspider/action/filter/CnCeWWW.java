package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Component
public class CnCeWWW extends BaseFilter {
	private String domain[] = new String[] { "www.ce.cn"};

	public CnCeWWW() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("div#articleText>div.TRS_Editor").first();
		String textQuerys[] = new String[] { "原标题", "相关:" };
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
		str = str.replace("【推荐阅读】", "");
		if (str.contains("此稿件为延展阅读内容")) {
			int index = str.indexOf("此稿件为延展阅读内容");
			str = str.substring(0, index);
		}
		return str;
	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
