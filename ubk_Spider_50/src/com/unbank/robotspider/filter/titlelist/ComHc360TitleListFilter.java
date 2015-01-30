package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComHc360TitleListFilter extends BaseTitleListFilter {
	private String domain[] = new String[] { "info.pharmacy.hc360.com","info.food.hc360.com"};

	public ComHc360TitleListFilter() {
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
		//#wezi141 > table:nth-child(1) > tbody > tr:nth-child(4) > td > a
		Elements possibleListElements = bodyElement.select("#wezi141 > table> tbody > tr > td > a");
		return possibleListElements;
	}

}
