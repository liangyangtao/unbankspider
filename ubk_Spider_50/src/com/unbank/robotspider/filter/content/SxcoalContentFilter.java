package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class SxcoalContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(SxcoalContentFilter.class);

	private String domain[] = new String[] { "coallaw.sxcoal.com",
			"resdata.sxcoal.com", "dbprice.sxcoal.com", "en.sxcoal.com",
			"dbcoal.sxcoal.com", "dbzc.sxcoal.com", "ccs.sxcoal.com",
			"db.sxcoal.com", "www.sxcoal.com" };

	public SxcoalContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#cont");
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
