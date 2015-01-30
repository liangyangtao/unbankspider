package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class Com86chemnetTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.86chemnet.com";

	public Com86chemnetTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("div.n_list_box > ul > li> a:nth-child(1)");
	}

}
