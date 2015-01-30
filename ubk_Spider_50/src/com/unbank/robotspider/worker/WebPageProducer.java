package com.unbank.robotspider.worker;

import com.unbank.robotspider.entity.NewsInfoMiddleWare;
import com.unbank.robotspider.filter.url.URLBaseFilter;
import com.unbank.robotspider.parser.WebPagePaser;

public class WebPageProducer {

	public NewsInfoMiddleWare fillNewsPageQueue(
			NewsInfoMiddleWare newsInfoMiddleWare) {

		boolean success = newsInfoMiddleWareURLFilter(newsInfoMiddleWare
				.getUrl());
		if (success) {
			return new WebPagePaser().paser(newsInfoMiddleWare);
		} else {
			return null;
		}
	}

	private boolean newsInfoMiddleWareURLFilter(String url) {
		URLBaseFilter urlBaseFilter = new URLBaseFilter();
		return urlBaseFilter.checkNewsURL(url);
	}

}
