package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class Ok17TitleListFilter extends BaseTitleListFilter {
	private String[] domains = { "finance.17ok.com", "stock.17ok.com",
			"money.17ok.com" };

	public Ok17TitleListFilter() {
		for (String domain : domains) {
			TitleListFilterLocator.getInstance().register(domain, this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return getPossibleListElement(document, "div.lbt");
	}

}
