package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class CnPedaily extends BaseFilter {
	private String domain[] = new String[] { "www.pedaily.cn", "pe.pedaily.cn",
			"news.pedaily.cn", "newseed.pedaily.cn", "dc.pedaily.cn",
			"research.pedaily.cn", "zdb.pedaily.cn", "people.pedaily.cn",
			"tag.pedaily.cn", "app.pedaily.cn" };

	public CnPedaily() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("#divcontent").first();
		String[] paths = { "div.content_toshare", "div.content_tag",
				"div.content_moreshare", "ul.relate_company", "div.page",
				"div.m_t_10" };
		for (int i = 0; i < paths.length; i++) {
			removeElementByCssPath(element, paths[i]);
		}

		String textQuerys[] = new String[] { "转载请注明" };
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

	private void removeElementByCssPath(Element element, String cssPath) {
		if (element == null) {
			return;
		}
		Elements elements = element.select(cssPath);
		if (elements != null) {
			element.select(cssPath).remove();
		}
	}

}
