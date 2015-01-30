package com.unbank.robotspider.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.unbank.robotspider.entity.NewsInfoMiddleWare;

public class WebPagePaser extends BasePaser {

	public NewsInfoMiddleWare paser(NewsInfoMiddleWare newsInfoMiddleWare) {
		Document document = getDocumentByURL(newsInfoMiddleWare.getUrl());
		if (document == null) {
			return null;
		}
		boolean success = new TitlePaser().fillCrawlTitle(newsInfoMiddleWare,
				document);
		if (!success) {
			return null;
		}
		success = new NewsDatePaser()
				.fillNewsTime(newsInfoMiddleWare, document);
		if (!success) {
			return null;
		}
		String content = fillNewsContent(document, newsInfoMiddleWare);
		if (content == null) {
			return null;
		}
		String crawlBrief = extractNewsBrief(content);
		if (crawlBrief == null) {
			return null;
		}
		newsInfoMiddleWare.setCrawlBrief(crawlBrief);
		newsInfoMiddleWare.setText(content);
		return newsInfoMiddleWare;
	}

	private String fillNewsContent(Document document,
			NewsInfoMiddleWare newsInfoMiddleWare) {
		String content = new ContentPaser().extractNewsContent(
				newsInfoMiddleWare.getUrl(), document,
				newsInfoMiddleWare.getCrawlTitle());
		if (content == null) {
			return null;
		}
		return content;
	}

	// 获取新闻简介
	private String extractNewsBrief(String content) {
		String crawl_brief = Jsoup.parse(content).text();
		if (crawl_brief.length() > 100) {
			crawl_brief = crawl_brief.substring(0, 100);
		} else {
			return null;
		}
		return crawl_brief;
	}
}
