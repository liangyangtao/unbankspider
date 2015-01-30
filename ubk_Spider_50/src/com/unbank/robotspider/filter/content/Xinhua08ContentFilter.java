package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class Xinhua08ContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(Xinhua08ContentFilter.class);

	private String domain[] = new String[] { "space.xinhua08.com",
			"world.xinhua08.com", "bank.xinhua08.com", "photo.xinhua08.com",
			"jucai.xinhua08.com", "bond.xinhua08.com", "my.xinhua08.com",
			"dict.xinhua08.com", "futures.xinhua08.com", "stock.xinhua08.com",
			"www.xinhua08.com", "dc.xinhua08.com", "opinion.xinhua08.com",
			"news.xinhua08.com", "company.xinhua08.com", "forex.xinhua08.com",
			"cfc.xinhua08.com", "app.xinhua08.com", "video.xinhua08.com",
			"life.xinhua08.com", "rmb.xinhua08.com" };

	public Xinhua08ContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#ctrlfscont");
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
		String cssQuerys[] = new String[] { ".alogo" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
