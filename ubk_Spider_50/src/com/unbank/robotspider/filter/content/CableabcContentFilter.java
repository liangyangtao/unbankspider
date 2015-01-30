package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class CableabcContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(CableabcContentFilter.class);

	private String domain[] = new String[] { "material.cableabc.com",
			"special.cableabc.com", "expo.cableabc.com", "q.cableabc.com",
			"jiance.cableabc.com", "train.cableabc.com",
			"zhaopin.cableabc.com", "www.cableabc.com", "futures.cableabc.com",
			"news.cableabc.com", "market.cableabc.com", "rese.cableabc.com",
			"figure.cableabc.com", "zhidao.cableabc.com" };

	public CableabcContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#divcontent");
		if (element == null) {
			return null;
		}
		return element;
	}

	public void removeNoNeedElementsByText(Element contentElement) {
		// String textQuerys[] = new String[] { "" };
		// for (String textQuery : textQuerys) {
		// removeNoNeedTextElement(contentElement, textQuery);
		// }
	}

	public void removeNoNeedElementsByCssQuery(Element contentElement) {
		// String cssQuerys[] = new String[] { "#hzh", "#nextpage", "#pages" };
		// for (String cssQuery : cssQuerys) {
		// removeNoNeedElement(contentElement, cssQuery);
		// }
	}

}
