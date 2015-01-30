package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class QQContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(QQContentFilter.class);

	private String domain[] = new String[] { "sports.qq.com", "edu.qq.com",
			"mail.qq.com", "astro.lady.qq.com", "ent.qq.com", "games.qq.com",
			"auto.qq.com", "kid.qq.com", "book.qq.com", "finance.qq.com",
			"t.qq.com", "qzone.qq.com", "news.qq.com", "house.qq.com",
			"gongyi.qq.com", "open.qq.com", "v.qq.com", "dy.qq.com",
			"cul.qq.com", "dajia.qq.com", "stockhtm.finance.qq.com",
			"service.qq.com", "fashion.qq.com", "tech.qq.com", "comic.qq.com",
			"digi.qq.com", "ilike.qq.com", "www.qq.com" };

	public QQContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document,
				"#Cnt-Main-Article-QQ");
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
		String cssQuerys[] = new String[] { "#tipsWBzf", ".titdd-Article",
				"#invideocon", "#focusHub", "#goRead", "#ch", "#backqqcom" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
