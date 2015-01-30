package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class CqnewsContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(CqnewsContentFilter.class);

	private String domain[] = new String[] { "t.cqnews.net", "sjb.cqnews.net",
			"house.cqnews.net", "finance.cqnews.net", "russian.cqnews.net",
			"help.cqnews.net", "food.cqnews.net", "baby.cqnews.net",
			"mglm.cqnews.net", "fashion.cqnews.net", "bbs.cqnews.net",
			"english.cqnews.net", "life.cqnews.net", "hljj.cqnews.net",
			"3g.cqnews.net", "picture.cqnews.net", "tour.cqnews.net",
			"korean.cqnews.net", "spanish.cqnews.net", "v.cqnews.net",
			"french.cqnews.net", "blog.cqnews.net", "japanese.cqnews.net",
			"sports.cqnews.net", "fl.cqnews.net", "map.cqnews.net",
			"cqwz2.cqnews.net", "www.cqnews.net", "education.cqnews.net",
			"say.cqnews.net", "3c.cqnews.net", "ent.cqnews.net",
			"dcyy.cqnews.net", "car.cqnews.net", "cqwz.cqnews.net",
			"cq.cqnews.net", "news.cqnews.net" };

	public CqnewsContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#main_text");
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
