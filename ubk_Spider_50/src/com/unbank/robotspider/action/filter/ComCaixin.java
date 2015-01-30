package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComCaixin extends BaseFilter {
	private String domain[] = new String[] { "finance.caixin.com",
			"opinion.caixin.com", "economy.caixin.com" ,"estate.caijing.com.cn"};

	public ComCaixin() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("#the_content").first();
		String cssQuerys[] = new String[] { "#conTit", "#subhead", "#pageNext",
				".content-tag", ".moreReport", ".idetor", ".pip",
				".media_pic > dd", "div.function01" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}
		return element;

	}

	@Override
	public void formatElements(Element maxTextElement) {
		super.formatElements(maxTextElement);
		Elements elements = maxTextElement.getElementsByTag("strong");
		for (int i = 0; i < elements.size(); i++) {
			String str = elements.get(i).text().trim();
			if (str.contains("|文")) {
				elements.get(i).remove();
			}
		}

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

		String[] spechars = new String[] { "更多高品质深度报道", "特别提示", "■" };
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
