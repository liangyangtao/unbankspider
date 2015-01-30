package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class BeijingGovContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(BeijingGovContentFilter.class);

	private String domain = "www.beijing.gov.cn";

	public BeijingGovContentFilter() {
		ContentFilterLocator.getInstance().register(domain, this);
	}

	public Element getContentNode(Document document) {
		String cssQuerys[] = new String[] { "#zoom", "#_Custom_Style_" };
		Element element = null;
		for (String string : cssQuerys) {
			if (string.equals("#_Custom_Style_")) {
				element = assignPossibleElement(document, string);
				if (element == null) {
					continue;
				}
				element = element.parent();
				removeNoNeedElement(element, "#_Custom_Style_");
				break;
			} else {
				element = assignPossibleElement(document, string);
				if (element != null) {
					break;
				}
			}
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
