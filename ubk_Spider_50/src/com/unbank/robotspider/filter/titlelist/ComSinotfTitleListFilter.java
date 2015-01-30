package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComSinotfTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.sinotf.com";

	public ComSinotfTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("div.list24px>ul>li>a");
	}

}
