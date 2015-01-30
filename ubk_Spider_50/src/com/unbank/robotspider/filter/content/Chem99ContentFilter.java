package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class Chem99ContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(Chem99ContentFilter.class);

	private String domain[] = new String[] { "ca.chem99.com",
			"rubb.chem99.com", "coal.chem99.com", "www.chem99.com",
			"oleochem.chem99.com", "plas.chem99.com", "pu.chem99.com",
			"fert.chem99.com", "coalchem.chem99.com", "fiber.chem99.com",
			"oil.chem99.com", "chem.chem99.com" };

	public Chem99ContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#zoom");
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
