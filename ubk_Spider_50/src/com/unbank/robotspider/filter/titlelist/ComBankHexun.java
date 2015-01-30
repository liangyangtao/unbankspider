package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComBankHexun extends BaseTitleListFilter {
	private String domain[] = new String[] { "bank.hexun.com",
			"house.hexun.com", "news.hexun.com",
			"funds.hexun.com","futures.hexun.com" ,
			"gold.hexun.com"};

	public ComBankHexun() {
		for (int i = 0; i < domain.length; i++) {
			TitleListFilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		Element bodyElement = document.body();
		if (bodyElement == null) {
			return null;
		}
		bodyElement.select(" div.temp01 > ul> li> span > a").remove();
		Elements possibleListElements = bodyElement.select("div.temp01")
				.first().select("a");
		return possibleListElements;
	}

}
