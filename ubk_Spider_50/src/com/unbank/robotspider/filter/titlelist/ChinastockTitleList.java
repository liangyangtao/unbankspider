package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ChinastockTitleList extends BaseTitleListFilter {
	private String domain = "www.chinastock.com.cn";

	public ChinastockTitleList() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		return getPossibleListElement(document, "div#ca01");
	}

}
