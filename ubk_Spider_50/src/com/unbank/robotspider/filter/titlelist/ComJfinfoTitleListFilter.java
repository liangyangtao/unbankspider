package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComJfinfoTitleListFilter extends BaseTitleListFilter {
	private String[] domains = {"finance.jfinfo.com"};

	public ComJfinfoTitleListFilter() {
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
		Elements possibleListElements = bodyElement.select("ul.jf_content_list>li>h2>a");
		return possibleListElements;
	}

}
