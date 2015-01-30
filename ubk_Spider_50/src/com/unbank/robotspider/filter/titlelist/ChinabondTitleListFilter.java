package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ChinabondTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.chinabond.com.cn";

	public ChinabondTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		Elements elements = getPossibleListElement(document, ".zabo_ri_nei");
		for (Element element : elements) {
			element.text(element.attr("title"));
		}
		return elements;
	}

}
