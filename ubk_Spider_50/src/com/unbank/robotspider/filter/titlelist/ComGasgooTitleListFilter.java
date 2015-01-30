package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComGasgooTitleListFilter extends BaseTitleListFilter {
	private String[] domains = {"auto.gasgoo.com"};

	public ComGasgooTitleListFilter() {
		for(String domain:domains){
			TitleListFilterLocator.getInstance().register(domain, this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		Element bodyElement = document.body();
		if (bodyElement == null) {
			return null;
		}
		Elements possibleListElements = bodyElement.select("div.nebox>ul>li>a");
		return possibleListElements;
	}

}
