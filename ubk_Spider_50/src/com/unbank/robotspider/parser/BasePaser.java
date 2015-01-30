package com.unbank.robotspider.parser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;

import com.unbank.robotspider.fetch.Fetcher;
import com.unbank.robotspider.fetch.JsoupNetFetcher;

public class BasePaser implements Paser {

	public static Log logger = LogFactory.getLog(BasePaser.class);

	public Document getDocumentByURL(String url) {
		Fetcher fetcher = new JsoupNetFetcher();
		Document document = fetcher.fetchText(url);
		return document;
	}

	public String removeSingleQuotes(String source) {
		if (source == null) {
			return null;
		}
		source = source.replace("'", "");
		return source;
	}
}
