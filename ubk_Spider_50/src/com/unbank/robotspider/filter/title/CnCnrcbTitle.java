package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class CnCnrcbTitle extends TitleBaseFilter {

	private String domain1 = "www.cnrcb.cn";
	
	public CnCnrcbTitle(){
		TitleFilterLocator.getInstance().register(domain1, this);
	}
	
	@Override
	public String getTitle(Document document, String Alternativetitle) {
		return document.select("div.newswm_content_con_title").first().text();
	}
	
}
