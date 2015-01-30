package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComChinahighwayTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.chinahighway.com";

	public ComChinahighwayTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("ul.homeleftlio>li>a");
	}

}
