package com.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unbank.robotspider.fetch.JsoupNetFetcher;
import com.unbank.robotspider.filter.titlelist.TitleListFilter;
import com.unbank.robotspider.filter.titlelist.TitleListFilterLocator;

public class TitleListTest {

	public static void main(String[] args) {

		new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		String url = "http://www.hengshui.gov.cn/HSXW/JRHS/index.shtml";
		Document document = new JsoupNetFetcher().fetchText(url);
		// System.out.println(document);
		TitleListFilter titleListFilter = TitleListFilterLocator.getInstance()
				.getFilter(url);
		Elements maxElements = titleListFilter.getPossibleListElement(document);
		// System.out.println(maxElements);
		for (Element element : maxElements) {
			System.out.println(element.absUrl("href"));
			System.out.println(element.text());

		}
		
	}
}
