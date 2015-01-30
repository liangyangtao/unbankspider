package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


@Service
public class DzwwwTitleListFilter extends BaseTitleListFilter {
	private String domain = "finance.dzwww.com";

	public DzwwwTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		return getPossibleListElement(document, "div.f14px");

	}

}
