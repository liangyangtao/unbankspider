package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnSteelcnTitleListFilter extends BaseTitleListFilter {
	private String domain = "news.steelcn.cn";

	public CnSteelcnTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("div.list>ul>li>a");
	}

}
