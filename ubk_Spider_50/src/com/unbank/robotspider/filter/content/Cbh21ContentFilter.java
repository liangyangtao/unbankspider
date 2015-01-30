package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class Cbh21ContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(Cbh21ContentFilter.class);

	private String domain[] = new String[] { "pinglun.21cbh.com",
			"mkt.21cbh.com", "comment.21cbh.com", "www.21cbh.com",
			"golf.21cbh.com", "money.21cbh.com", "zhuanti.21cbh.com",
			"auto.21cbh.com", "tousu.21cbh.com", "jigou.21cbh.com",
			"jingji.21cbh.com", "finance.21cbh.com", "news.21cbh.com",
			"fenxishi.21cbh.com", "img.21cbh.com", "win.21cbh.com",
			"epaper.21cbh.com", "tech.21cbh.com", "fangchan.21cbh.com",
			"special.21cbh.com", "lady.21cbh.com", "advert.21cbh.com",
			"ent.21cbh.com" };

	public Cbh21ContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}
	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#Article");
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
		String cssQuerys[] = new String[] { "#ad", ".goindex" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
