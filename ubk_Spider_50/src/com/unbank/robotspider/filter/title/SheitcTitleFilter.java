package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class SheitcTitleFilter extends TitleBaseFilter {

	private String domain = "www.sheitc.gov.cn";

	public SheitcTitleFilter() {
		TitleFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public String getTitle(Document document, String Alternativetitle) {
		return document.select("#ivs_title").first().text();
	}

}
