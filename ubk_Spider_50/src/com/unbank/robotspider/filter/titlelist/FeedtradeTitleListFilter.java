package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class FeedtradeTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.feedtrade.com.cn";

	public FeedtradeTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return getPossibleListElement(document, "#context");
	}

}
