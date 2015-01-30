package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CinicTitleListFilter extends BaseTitleListFilter {
	private String[] domains = { "shanxi.cinic.org.cn" };

	public CinicTitleListFilter() {
		for (int i = 0; i < domains.length; i++) {
			TitleListFilterLocator.getInstance().register(domains[i], this);
		}

	}

	@Override
	public Elements getPossibleListElement(Document document) {

		return getPossibleListElement(document, ".NewsList");
	}

}
