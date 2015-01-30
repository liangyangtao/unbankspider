package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CaijingTitleListFilter extends BaseTitleListFilter {
	private String[] domains = {"economy.caijing.com.cn","finance.caijing.com.cn","industry.caijing.com.cn","estate.caijing.com.cn"};

	public CaijingTitleListFilter() {
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
		Elements possibleListElements = bodyElement.select("div.wzbt > a");
		return possibleListElements;
	}

}
