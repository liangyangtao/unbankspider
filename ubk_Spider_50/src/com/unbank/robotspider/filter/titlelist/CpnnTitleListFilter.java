package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CpnnTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.cpnn.com.cn";

	public CpnnTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		return getPossibleListElement(document, "div.newlist11");
	}

}
