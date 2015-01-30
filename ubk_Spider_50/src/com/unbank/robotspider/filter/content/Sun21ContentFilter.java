package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class Sun21ContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(Sun21ContentFilter.class);

	private String domain[] = new String[] { "ytdxyy.part.21-sun.com",
			"rent.21-sun.com", "ad.21-sun.com", "www.21-sun.com",
			"news.21-sun.com", "job.21-sun.com", "photo.21-sun.com",
			"member.21-sun.com", "tower.21-sun.com", "spec.21-sun.com",
			"used.21-sun.com", "weixiu.21-sun.com", "top.21-sun.com",
			"video.21-sun.com", "ytdoushan.part.21-sun.com",
			"gzhfybyz.part.21-sun.com", "koubei.21-sun.com",
			"jining.news.21-sun.com", "product.21-sun.com",
			"peitao.21-sun.com", "market.21-sun.com", "part.21-sun.com",
			"sowa.21-sun.com", "cio.21-sun.com", "sany.815.21-sun.com" };

	public Sun21ContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#mainDetail");
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
		String cssQuerys[] = new String[] { ".listfoot01" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
