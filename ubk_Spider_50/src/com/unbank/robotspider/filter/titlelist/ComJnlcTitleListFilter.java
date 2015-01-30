package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComJnlcTitleListFilter extends BaseTitleListFilter {
	private String domain[] = new String[] { "www.jnlc.com"};

	public ComJnlcTitleListFilter() {
		for (int i = 0; i < domain.length; i++) {
			TitleListFilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Elements getPossibleListElement(Document document) {

		Element bodyElement = document.body();
		if (bodyElement == null) {
			return null;
		}
		bodyElement.getElementById("tab-cont-1").remove();
		Elements possibleListElements = bodyElement.select(" dl.ov > dd> ul > li> a");
		return possibleListElements;
	}

}
