package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComEastmoneyTitleListFilter extends BaseTitleListFilter {
	private String[] domains = { "bank.eastmoney.com", "stock.eastmoney.com",
			"finance.eastmoney.com", "insurance.eastmoney.com",
			"gold.eastmoney.com", "forex.eastmoney.com", "roll.eastmoney.com",
			"money.eastmoney.com", "bond.eastmoney.com", "trust.eastmoney.com",
			"forex.eastmoney.com" };

	public ComEastmoneyTitleListFilter() {
		for (String domain : domains) {
			TitleListFilterLocator.getInstance().register(domain, this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		Element bodyElement = document.body();
		if (bodyElement == null) {
			return null;
		}
		Elements possibleListElements = bodyElement.select("div.list>ul>li>a,p.title>a");
		return possibleListElements;
	}

}
