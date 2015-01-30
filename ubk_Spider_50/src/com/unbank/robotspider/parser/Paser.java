package com.unbank.robotspider.parser;

import org.jsoup.nodes.Document;

public interface Paser {

	public Document getDocumentByURL(String url);
}
