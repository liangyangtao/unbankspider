package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class TakungpaoContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(TakungpaoContentFilter.class);

	private String domain[] = new String[] { "rss.takungpao.com",
			"bodhi.takungpao.com", "sports.takungpao.com",
			"photo.takungpao.com", "ent.takungpao.com", "arts.takungpao.com",
			"hea.takungpao.com", "search.takungpao.com",
			"health.takungpao.com", "food.takungpao.com", "digi.takungpao.com",
			"edu.takungpao.com", "talk.takungpao.com", "www.takungpao.com",
			"finance.takungpao.com", "news.takungpao.com",
			"comments.takungpao.com", "auto.takungpao.com",
			"lady.takungpao.com" };

	public TakungpaoContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}
	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, ".text");
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
