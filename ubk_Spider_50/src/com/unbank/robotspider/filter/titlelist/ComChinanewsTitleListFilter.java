package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComChinanewsTitleListFilter extends BaseTitleListFilter {
	private String domain = "finance.chinanews.com";

	public ComChinanewsTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("td.color065590>a");
	}

}
