package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnComOcnTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.ocn.com.cn";

	public CnComOcnTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		document.select("li.page").remove();
		return document.select(" ul.newsContent.blue > li > a");
	}

}
