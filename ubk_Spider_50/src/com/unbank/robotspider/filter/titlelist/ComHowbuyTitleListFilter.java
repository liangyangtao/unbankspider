package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComHowbuyTitleListFilter extends BaseTitleListFilter {
	private String[] domains = {"www.howbuy.com"};

	public ComHowbuyTitleListFilter() {
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
		Elements possibleListElements = bodyElement.select("div.body>table>tbody>tr>td> a");
		return possibleListElements;
	}

}
