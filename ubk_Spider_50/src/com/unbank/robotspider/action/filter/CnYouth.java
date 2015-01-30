package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class CnYouth extends BaseFilter {
	private String domain[] = new String[] { "www.youth.cn", "news.youth.cn",
			"picture.youth.cn", "pinglun.youth.cn", "fun.youth.cn",
			"style.youth.cn", "finance.youth.cn", "mil.youth.cn",
			"sports.youth.cn", "career.youth.cn", "school.youth.cn",
			"health.youth.cn", "d.youth.cn", "v.youth.cn", "youxi.youth.cn",
			"auto.youth.cn", "qclz.youth.cn", "ad.youth.cn" };

	public CnYouth() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select(
				"#container > div:nth-child(1) > div:nth-child(1)").first();
		String[] cssQuerys = new String[] { "iframe" };
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
