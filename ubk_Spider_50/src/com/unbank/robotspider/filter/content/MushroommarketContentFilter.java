package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class MushroommarketContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(MushroommarketContentFilter.class);

	private String domain[] = new String[] { "know.mushroommarket.net",
			"caipu.mushroommarket.net", "jishu.mushroommarket.net",
			"www.mushroommarket.net", "video.mushroommarket.net",
			"zixun.mushroommarket.net" };

	public MushroommarketContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, ".n_show_nr");
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
//		String cssQuerys[] = new String[] { "#hzh", "#nextpage", "#pages" };
//		for (String cssQuery : cssQuerys) {
//			removeNoNeedElement(contentElement, cssQuery);
//		}
	}

}
