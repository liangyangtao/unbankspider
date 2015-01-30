package com.unbank.robotspider.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class BaseFilter implements Filter {

	@Override
	public Element assignPossibleElement(Document document, String cssQuery) {
		return document.select(cssQuery).first();
	}

	@Override
	public void removeNoNeedElement(Element element, String cssQuery) {
		if (element == null) {
			return;
		}
		Elements elements = element.select(cssQuery);
		for (Element element2 : elements) {
			element2.remove();
		}
	}

	@Override
	public void removeNoNeedTextElement(Element element, String textQuery) {
		Elements element2 = element.getElementsContainingOwnText(textQuery);
		for (Element element3 : element2) {
			element3.remove();
		}
	}

}
