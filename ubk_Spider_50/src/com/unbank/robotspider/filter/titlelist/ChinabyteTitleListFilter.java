package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ChinabyteTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.chinabyte.com";

	public ChinabyteTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return getPossibleListElement(document, "ul.artiList");
	}

}
