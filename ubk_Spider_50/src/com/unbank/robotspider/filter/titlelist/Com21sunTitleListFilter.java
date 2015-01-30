package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class Com21sunTitleListFilter extends BaseTitleListFilter {
	private String domain = "news.21-sun.com";

	public Com21sunTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("ul.ln_left>li>a");
	}

}
