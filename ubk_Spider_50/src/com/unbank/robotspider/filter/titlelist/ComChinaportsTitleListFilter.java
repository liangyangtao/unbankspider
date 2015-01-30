package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComChinaportsTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.chinaports.com";

	public ComChinaportsTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("div.news-more-liebiao>ul>li>a");
	}

}
