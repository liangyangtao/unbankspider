package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.robotspider.filter.coding.CodingBaseFilter;
import com.unbank.robotspider.tools.Levenshtein;
import com.unbank.robotspider.tools.PropertyValules;

public class TitleBaseFilter implements TitleFilter {

	@Override
	public String getTitle(Document document) {
		Document doc = document.clone();
		String title = "";
		float f = 0;
		String doc_title = doc.title();
		for (int i = 1; i <= 5; i++) {
			Elements elements = doc.getElementsByTag("h" + i);
			if (elements.size() > 0) {
				for (Element e : elements) {

					String s_title = dispose(e.text(), doc.baseUri());
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

	@Override
	public String getTitle(Document document, String Alternativetitle) {
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
					String s_title = dispose(e.text(), doc.baseUri());
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

	public boolean checkTitle(String string) {
		boolean success = checkMessyCode(string);
		if (success) {
			return false;
		}
		success = checkNoNeedTitle(string);
		if (success) {
			return false;
		}
		return true;

	}

	private boolean checkNoNeedTitle(String string) {
		if (string.length() < 10 || string.contains("@")
				|| string.contains("首页")) {
			return true;
		}
		return false;
	}

	public boolean checkMessyCode(String source) {
		return new CodingBaseFilter().checkMessyCode(source);
	}

	public String dispose(String str, String url) {
		String title = "";
		if (url.contains("jrj.com.cn")) {
			title = str.replace("减少字号默认字号加大字号", "");
		}

		return title == "" ? str : title;
	}
}
