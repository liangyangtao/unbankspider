package com.unbank.robotspider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.robotspider.entity.NewsInfoMiddleWare;
import com.unbank.robotspider.filter.title.TitleFilterLocator;
import com.unbank.robotspider.tools.Levenshtein;
import com.unbank.robotspider.tools.PropertyValules;

/**
 * 提取新闻标题
 * 
 * @author 梁擎宇
 * 
 */
public class TitlePaser extends BasePaser {

	/**
	 * s 遍历所有的H标签，然后和Document的title进行比较
	 * 
	 * @param document
	 * @return
	 */
	public static String getTitle(Document document) {
		Document doc = document.clone();
		String title = "";
		float f = 0;
		String doc_title = doc.title();
		for (int i = 1; i <= 5; i++) {
			Elements elements = doc.getElementsByTag("h" + i);
			if (elements.size() > 0) {
				for (Element e : elements) {

					String s_title = e.text();
					float ff = Levenshtein.getSimilarityRatio(doc_title,
							s_title);
					if (ff > PropertyValules.SCOREFLAG && ff > f) {
						title = s_title;
						f = ff;
					}
				}
			}
		}
		return (title == null || "".equals(title)) ? doc_title : title;
	}

	/**
	 * 推荐使用 遍历所有的H标签，然后和Alternativetitle进行比较 Alternativetitle可以是新闻列表页中抓取到的新闻标题
	 * 
	 * @param document
	 * @param Alternativetitle
	 *            可能的标题
	 * @return
	 */
	public static String getTitle(Document document, String Alternativetitle) {
		Document doc = document.clone();
		String title = "";
		float f = 0;
		for (int i = 1; i <= 5; i++) {
			Elements elements = doc.getElementsByTag("h" + i);
			// 除了h标签还有类和ID为title的
			Elements classElements = doc.getElementsByClass("title");
			Element idElement = doc.getElementById("title");
			if (classElements != null) {
				elements.addAll(classElements);
			}
			if (idElement != null) {
				elements.add(idElement);
			}
			if (elements.size() > 0) {
				for (Element e : elements) {
					if (e == null || e.text() == null) {
						continue;
					}
					String s_title = e.ownText();
					float ff = Levenshtein.getSimilarityRatio(Alternativetitle,
							s_title);
					if (ff > PropertyValules.SCOREFLAG && ff > f) {
						title = s_title;
						f = ff;
					}
				}
			}
		}
		return (title == null || "".equals(title)) ? Alternativetitle : title;
	}

	public boolean fillCrawlTitle(NewsInfoMiddleWare articleInfo,
			Document document) {
		String title = TitleFilterLocator.getInstance()
				.getFilter(document.baseUri())
				.getTitle(document, articleInfo.getCrawlTitle());
		title = removeSingleQuotes(title);
		title = dispose(title, articleInfo.getUrl());
		articleInfo.setCrawlTitle(title.trim());
		return true;

	}

	public String dispose(String str, String url) {
		String title = "";
		if (url.contains("jrj.com.cn")) {
			title = str.replace("减少字号默认字号加大字号", "");
		}

		return title == "" ? str : title;
	}
}
