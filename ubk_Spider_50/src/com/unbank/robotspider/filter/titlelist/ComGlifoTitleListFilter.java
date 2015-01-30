package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComGlifoTitleListFilter extends BaseTitleListFilter {
	private String domain = "info.glinfo.com";

	public ComGlifoTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("ul.nlist>li>a");
	}

}
