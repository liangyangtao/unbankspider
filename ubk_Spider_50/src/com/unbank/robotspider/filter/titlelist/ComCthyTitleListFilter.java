package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComCthyTitleListFilter extends BaseTitleListFilter {
	private String domain = "news.cthy.com";

	public ComCthyTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		document.select("a.ls").remove();
		return document.select("div.mslist > ul > li> a");
	}

}
