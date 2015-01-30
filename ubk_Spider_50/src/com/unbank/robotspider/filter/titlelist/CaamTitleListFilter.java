package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CaamTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.caam.org.cn";

	public CaamTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		Element bodyElement = document.body();
		if (bodyElement == null) {
			return null;
		}

		Element temp = bodyElement.select("div.xwzxlist").first();
		Elements possibleListElements = null;
		if (temp != null) {
			possibleListElements = temp.select("a");
		} else {
			logger.info("获取失败");
		}

		return possibleListElements;
	}

}
