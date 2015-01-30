package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class SmeTitleListFilter extends BaseTitleListFilter {
	private String[] domains = { "www.sme.gov.cn" };

	public SmeTitleListFilter() {
		for (String domain : domains) {
			TitleListFilterLocator.getInstance().register(domain, this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return getPossibleListElement(document, "ul.list");
	}

}
