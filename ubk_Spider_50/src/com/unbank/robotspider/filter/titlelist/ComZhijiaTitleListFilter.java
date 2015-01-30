package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComZhijiaTitleListFilter extends BaseTitleListFilter {
	private String domain = "news.zhijia.com";

	public ComZhijiaTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		document.select("div.ul_dot> a>p").remove();
		return document.select("div.ul_dot> a");
	}

}
