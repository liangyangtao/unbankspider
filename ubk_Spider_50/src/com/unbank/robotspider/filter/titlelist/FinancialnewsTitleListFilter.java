package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class FinancialnewsTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.financialnews.com.cn";

	public FinancialnewsTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		Element bodyElement = document.body();
		if (bodyElement == null) {
			return null;
		}
		Elements possibleListElements = bodyElement
				.select("body > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1)")
				.first().select("a");
		return possibleListElements;
	}

}
