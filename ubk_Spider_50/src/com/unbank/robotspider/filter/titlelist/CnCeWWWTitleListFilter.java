package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnCeWWWTitleListFilter extends BaseTitleListFilter {
	private String domain[] = new String[] { "www.ce.cn" };

	public CnCeWWWTitleListFilter() {
		for (int i = 0; i < domain.length; i++) {
			TitleListFilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Elements getPossibleListElement(Document document) {
		Elements elements = null;
		try {
			elements = document
					.select("#cen > div.neirong > table:nth-child(2)").first()
					.select("a");
		} catch (Exception e) {
			return null;
		}
		return elements;
	}
}
