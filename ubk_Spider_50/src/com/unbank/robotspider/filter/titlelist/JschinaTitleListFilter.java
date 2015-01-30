package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class JschinaTitleListFilter extends BaseTitleListFilter {
	private String domain = "economy.jschina.com.cn";

	public JschinaTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		return getPossibleListElement(document,
				"div.NewsList > table:nth-child(1) > tbody:nth-child(1)");
	}

}
