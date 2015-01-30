package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class AfinanceTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.afinance.cn";

	public AfinanceTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		return getPossibleListElement(document, "div#zuo > div:nth-child(2)");
	}

}
