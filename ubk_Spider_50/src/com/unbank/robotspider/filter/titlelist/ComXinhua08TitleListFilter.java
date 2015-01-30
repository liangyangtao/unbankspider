package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComXinhua08TitleListFilter extends BaseTitleListFilter {
	private String domain = "futures.xinhua08.com";

	public ComXinhua08TitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		document.select("div.media-body>p").remove();
		document.select("div.newsinfo").first().remove();
		return document.select("div.newsinfo > a");
	}

}
