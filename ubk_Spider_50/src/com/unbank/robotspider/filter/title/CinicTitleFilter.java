package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class CinicTitleFilter extends TitleBaseFilter {

	private String domain1 = "www.cinic.org.cn";

	public CinicTitleFilter() {
		TitleFilterLocator.getInstance().register(domain1, this);
	}

	@Override
	public String getTitle(Document document, String Alternativetitle) {
		return document.select(".end_ct2").first().text();
	}

}
