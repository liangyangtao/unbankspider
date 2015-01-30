package com.unbank.robotspider.util;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import com.unbank.robotspider.parser.ContentPaser;

public class GetNewsContent {

	private final static Logger logger = Logger.getLogger(GetNewsContent.class);

	// 获取新闻内容
	public static String parser(String url, Document document, String title) {
		
		ContentPaser  cpl=new ContentPaser();
		String content= cpl.extractNewsContent(url, document, title);
		
		
		return content;
	}

/*	// 通用得到新闻内容（比较节点最大的内容节点）
	public static String getNewsContent(String url, Document document,
			String title) {
		if (document == null) {
			logger.info("url错误" + url);
			return null;
		}
		Filter filter = FilterLocator.getInstance().getFilter(url);
		String content= filter.getContent(url, document, title);
		return content;
	}*/
}
