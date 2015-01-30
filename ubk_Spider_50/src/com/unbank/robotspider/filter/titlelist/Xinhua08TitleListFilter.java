package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class Xinhua08TitleListFilter extends BaseTitleListFilter {
	private String domain[] = new String[] { "bank.xinhua08.com",
			"news.xinhua08.com" };

	public Xinhua08TitleListFilter() {
		for (int i = 0; i < domain.length; i++) {
			TitleListFilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		return getPossibleListElement(document, "div.headlines");
	}

}
