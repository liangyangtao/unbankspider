package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class SmeGovTitle extends TitleBaseFilter {

	private String domain = "www.sme.gov.cn";

	public SmeGovTitle() {
		TitleFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public String getTitle(Document document, String Alternativetitle) {

		Element element = document.select("#article").first();
		Element titleElement = element.select(".placeholder").first();
		return titleElement.text();
	}

}
