package com.unbank.robotspider.worker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.robotspider.entity.NewsInfoMiddleWare;
import com.unbank.robotspider.entity.WebSiteInfo;
import com.unbank.robotspider.filter.titlelist.TitleListFilter;
import com.unbank.robotspider.filter.titlelist.TitleListFilterLocator;
import com.unbank.robotspider.parser.BasePaser;

public class NewsListProducer {

	public List<NewsInfoMiddleWare> fillNewsListQueue(WebSiteInfo webSiteInfo) {
		List<String> seeds = fillUrlSeeds(webSiteInfo);
		List<NewsInfoMiddleWare> middleWares = new ArrayList<NewsInfoMiddleWare>();
		for (String string : seeds) {
			List<NewsInfoMiddleWare> temp = readTitleList(webSiteInfo, string);
			if (temp != null) {
				middleWares.addAll(temp);
				temp.clear();
			}
		}
		seeds.clear();
		return middleWares;
	}

	private List<NewsInfoMiddleWare> readTitleList(WebSiteInfo webSiteInfo,
			String string) {
		Document document = new BasePaser().getDocumentByURL(string);
		if (document != null) {
			Elements maxElements = getTitleListElement(string, document);
			if (maxElements == null || maxElements.size() == 0) {
				return null;
			}
			return putQueue(webSiteInfo, maxElements);
		}
		return null;
	}

	private List<NewsInfoMiddleWare> putQueue(WebSiteInfo webSiteInfo,
			Elements maxElements) {
		List<NewsInfoMiddleWare> middleWares = new ArrayList<NewsInfoMiddleWare>();
		for (Element element : maxElements) {
			NewsInfoMiddleWare middleWare = fillArticleInfo(webSiteInfo,
					element);
			middleWares.add(middleWare);
		}
		maxElements.clear();
		return middleWares;
	}

	private Elements getTitleListElement(String string, Document document) {
		TitleListFilter titleListFilter = TitleListFilterLocator.getInstance()
				.getFilter(string);
		Elements maxElements = titleListFilter.getPossibleListElement(document);
		return maxElements;
	}

	private NewsInfoMiddleWare fillArticleInfo(WebSiteInfo webSiteInfo,
			Element element) {

		NewsInfoMiddleWare middleWare = new NewsInfoMiddleWare();
		middleWare.setWebName(webSiteInfo.getWebName());
		middleWare.setWebsiteId(webSiteInfo.getWebsiteId());
		middleWare.setUrl(element.absUrl("href"));
		middleWare.setCrawlTitle(findCrawlTitle(element));
		middleWare.setFileIndex((byte) 7);
		middleWare.setCrawlTime(new Date());
		middleWare.setTask(Byte.parseByte(webSiteInfo.getIstask() + ""));
		return middleWare;

	}

	private String findCrawlTitle(Element possibleNewsElement) {
		String crawlTitle = possibleNewsElement.text();
		Elements elements = possibleNewsElement.getAllElements();
		for (Element element : elements) {
			if (element.tagName().startsWith("h")) {
				crawlTitle = element.text();
				break;
			}
		}
		elements.clear();
		return crawlTitle;
	}

	public List<String> fillUrlSeeds(WebSiteInfo webSiteInfo) {
		List<String> seeds = new ArrayList<String>();
		seeds.add(webSiteInfo.getUrlHome());
		if (webSiteInfo.getUrlSecond() != null
				&& !webSiteInfo.getUrlSecond().isEmpty()) {
			seeds.add(webSiteInfo.getUrlSecond());
		}
		if (webSiteInfo.getUrlThird() != null
				&& !webSiteInfo.getUrlThird().isEmpty()) {
			seeds.add(webSiteInfo.getUrlThird());
		}
		return seeds;
	}

}
