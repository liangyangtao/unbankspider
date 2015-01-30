package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class NenContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(NenContentFilter.class);

	private String domain[] = new String[] { "www.nen.com.cn",
			"news.nen.com.cn", "liaoning.nen.com.cn", "society.nen.com.cn",
			"amuse.nen.com.cn", "sports.nen.com.cn", "in.nen.com.cn",
			"jiankang.nen.com.cn", "finance.nen.com.cn", "house.nen.com.cn",
			"edu.nen.com.cn", "auto.nen.com.cn", "travel.nen.com.cn",
			"t.nen.com.cn", "bbs.nen.com.cn", "shequ.nen.com.cn",
			"marketing.nen.com.cn", "web.nen.com.cn" };

	public NenContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, ".contentcon");
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
		String cssQuerys[] = new String[] { "div.fromwhere", "iframe" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
