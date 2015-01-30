package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class P5wTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.p5w.net";

	public P5wTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return super.getPossibleListElement(document);
	}

}
