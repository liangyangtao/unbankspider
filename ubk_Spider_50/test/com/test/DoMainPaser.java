package com.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.robotspider.fetch.JsoupNetFetcher;

public class DoMainPaser {

	public static void main(String[] args) {
		String url = "http://www.10jqka.com.cn/";

		Document document = new JsoupNetFetcher().fetchText(url);

		List<String> domainList = new ArrayList<String>();

		Set<String> domains = new HashSet<String>();
		Elements elements = document.select("a");
		for (Element element : elements) {
			String url2 = element.absUrl("href");
			if (url2.contains(".10jqka.")) {
				String domain = getDomain(url2);
				if (domains.contains(domain)) {
					continue;
				} else {
					domains.add(domain);
				}
				domainList.add(domain);
//				System.out.print("\"" + domain + "\",");
			} else {
				continue;
			}

		}
		Collections.sort(domainList);

		for (String domain : domainList) {
			System.out.print("\"" + domain + "\",");
		}

	}

	public static String getDomain(String url) {
		String domain = "";
		try {
			URL u = new URL(url);
			domain = u.getHost();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return domain;
	}

}
