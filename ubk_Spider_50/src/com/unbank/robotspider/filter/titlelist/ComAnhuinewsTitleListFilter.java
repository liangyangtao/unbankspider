package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComAnhuinewsTitleListFilter extends BaseTitleListFilter {
	private String domain = "ah.anhuinews.com";

	public ComAnhuinewsTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("dl.work_dt_1>dt>a");
	}

}
