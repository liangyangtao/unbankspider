package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnPolymerTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.polymer.cn";

	public CnPolymerTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select(" div.InstrumentMid>ul>li > a");
	}

}
