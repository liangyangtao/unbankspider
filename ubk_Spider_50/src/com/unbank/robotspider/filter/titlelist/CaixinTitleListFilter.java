package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CaixinTitleListFilter extends BaseTitleListFilter {
	private String[] domains = { "economy.caixin.com", "opinion.caixin.com",
			"finance.caijing.com.cn", "estate.caijing.com.cn" };

	public CaixinTitleListFilter() {
		for (String domain : domains) {
			TitleListFilterLocator.getInstance().register(domain, this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		Elements elements = getPossibleListElement(document, ".conlf");
		if (elements == null) {
			elements = getPossibleListElement(document, ".main");
		}

		return elements;
	}

}
