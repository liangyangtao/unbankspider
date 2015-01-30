package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class MwrGovTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.mwr.gov.cn";

	public MwrGovTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		return getPossibleListElement(document,
				"div#maintable > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)");
	}

}
