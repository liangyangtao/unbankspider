package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComWinshangTitleListFilter extends BaseTitleListFilter {
	private String domain = "news.winshang.com";

	public ComWinshangTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		document.select("a.newsflds").remove();
		return document.select("div.ttit> a");
	}

}
