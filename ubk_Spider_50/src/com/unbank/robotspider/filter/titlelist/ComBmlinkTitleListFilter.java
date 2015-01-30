package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComBmlinkTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.bmlink.com";

	public ComBmlinkTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("div.news_list2 > ul> li> strong > a");
	}

}
