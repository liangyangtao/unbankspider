package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Component
public class ComEdai extends BaseFilter {
	private String domain[] = new String[] { "www.edai.com" };

	public ComEdai() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("div.test02:nth-child(3)").first();
		String cssQuerys[] = new String[] { "#pagecontent", "#page",
				".test01asp",
				"img[src=/edai2/Resource/images/news/icon-16.png]", ".page_txt" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}
		String textQuerys[] = new String[] { "缺钱帮你贷款", "有钱帮你赚钱", "作者：" };
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
