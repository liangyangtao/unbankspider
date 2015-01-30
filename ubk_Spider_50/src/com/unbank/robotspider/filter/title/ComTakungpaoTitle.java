package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class ComTakungpaoTitle extends TitleBaseFilter {

	private String domain[] = new String[] { "news.takungpao.com",
			"finance.takungpao.com" };

	public ComTakungpaoTitle() {
		for (int i = 0; i < domain.length; i++) {
			TitleFilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public String getTitle(Document document, String Alternativetitle) {
		return document.select("h1").first().text();
	}

}
