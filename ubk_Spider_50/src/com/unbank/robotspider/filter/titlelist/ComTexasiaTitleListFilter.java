package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComTexasiaTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.tex-asia.com";

	public ComTexasiaTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("div.listnews>ul>li> a");
	}

}
