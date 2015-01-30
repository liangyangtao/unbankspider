package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class JseicTitleFilter extends TitleBaseFilter {

	private String domain = "www.jseic.gov.cn";

	public JseicTitleFilter() {
		TitleFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public String getTitle(Document document, String Alternativetitle) {
		return document.select(".un").first().text();
	}

}
