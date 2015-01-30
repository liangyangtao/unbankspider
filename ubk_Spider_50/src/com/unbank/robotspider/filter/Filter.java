package com.unbank.robotspider.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public interface Filter {

	public Element assignPossibleElement(Document document, String cssQuery);

	public void removeNoNeedElement(Element element, String cssQuery);

	public void removeNoNeedTextElement(Element element, String textQuery);

}
