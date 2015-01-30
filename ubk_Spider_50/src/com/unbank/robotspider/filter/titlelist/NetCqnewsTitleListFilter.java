package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class NetCqnewsTitleListFilter extends BaseTitleListFilter {
	private String domain = "cq.cqnews.net";

	public NetCqnewsTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("div.Article.news > ul > li > a");
	}

}
