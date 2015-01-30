package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnComChinaTitleListFilter2 extends BaseTitleListFilter {
	private String domain = "house.china.com.cn";

	public CnComChinaTitleListFilter2() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("div.list_spxw_left> a");
	}

}
