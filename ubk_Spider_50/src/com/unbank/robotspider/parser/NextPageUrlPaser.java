package com.unbank.robotspider.parser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.robotspider.filter.nextPage.NextPageFilter;
import com.unbank.robotspider.filter.nextPage.NextPageFilterLocator;

public class NextPageUrlPaser extends BasePaser {
	private final static Logger logger = Logger
			.getLogger(NextPageUrlPaser.class);
	private Document doc;
	private String baseurl;
	private Map<String, String> urlMap = new HashMap<String, String>();
	private TreeMap<Integer, String> nextPageUrlMap = new TreeMap<Integer, String>();

	public boolean hasNextPage() {
		if (nextPageUrlMap != null && nextPageUrlMap.size() > 0) {
			return true;
		}
		return false;
	}

	private void init() {
		Elements a_elements = doc.getElementsByTag("a");
		for (Element e : a_elements) {
			String absUrl = e.absUrl("href");
			if (absUrl == null || absUrl.trim().isEmpty()) {
				continue;
			}
			String a_text = e.text();
			urlMap.put(a_text, absUrl);
		}
		fecthNextPageUrl();
	}

	private void fecthNextPageUrl() {
		findNextPage();
		if (nextPageUrlMap.size() == 0) {
			fecthJavaScriptNextPageUrl();
		}

	}

	private void findNextPage() {
		Set<String> keysset = urlMap.keySet();
		Iterator<String> iterator = keysset.iterator();
		while (iterator.hasNext()) {
			String a_text = (String) iterator.next();
			String url = urlMap.get(a_text);
			boolean bl = checkText(a_text);
			if (bl) {
				int cu = checkUrl(baseurl, url);
				if (cu != -1) {
					nextPageUrlMap.put(cu, url);
				}
			}
		}
	}

	private void fecthJavaScriptNextPageUrl() {
		NextPageFilterLocator nextPageFilterLocator = NextPageFilterLocator
				.getInstance();
		NextPageFilter nextPageFilter = nextPageFilterLocator
				.getFilter(baseurl);
		nextPageUrlMap = nextPageFilter.extractNextPageUrlMap(doc, baseurl);

	}

	private boolean checkText(String text) {
		text = text.replace(" ", "");
		text = text.replace("&nbsp;", "");
		String[] texts = { "首页", "第一页", "下一页", "末页", "最后一页", "尾页" };
		for (int i = 0; i < texts.length; i++) {
			if (texts[i].equals(text)) {
				return true;
			}
		}
		
		if (text.matches("\\d{1,2}")) {
			return true;
		}
		if (text.matches("【\\d{1,2}】")) {
			return true;
		}
		if (text.matches("\\[\\d{1,2}\\]")) {
			return true;
		}

		return false;
	}

	private int checkUrl(String url1, String url2) {
		int l1 = url1.length();
		int l2 = url2.length();
		if (l1 == 0 || l2 == 0) {
			return -1;
		}

		String longStr = l1 > l2 ? url1 : url2;
		String shortStr = l1 < l2 ? url1 : url2;
		int j = 0;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < longStr.length() - 1; i++) {
			if (longStr.charAt(i) != shortStr.charAt(j)) {
				sb.append(longStr.charAt(i));
			} else {
				j++;
				if (j == shortStr.length()) {
					break;
				}
			}
		}
		if (sb.length() == 0) {
			return -1;
		}
		String variances = sb.toString();
		String numStr = variances.replaceAll("_", "").replaceAll("=", "")
				.replaceAll("index", "").replaceAll("page", "")
				.replaceAll("p", "").replaceAll("-", "");
		if (numStr.matches("\\d{1,2}")) {
			return Integer.valueOf(numStr);
		} else {
			return -1;
		}
	}

	public NextPageUrlPaser(Document doc, String baseurl) {
		this.doc = doc.clone();
		this.baseurl = baseurl;
		init();
	}

	public TreeMap<Integer, String> getNextPageUrlMap() {
		formatUrlMap();
		return nextPageUrlMap;
	}

	private void formatUrlMap() {
		int lastkey = nextPageUrlMap.lastKey();
		int size = nextPageUrlMap.size();
		if (size == 1) {
			return;
		}
		if (size + 1 != lastkey) {

			Iterator<Integer> iterator = nextPageUrlMap.keySet().iterator();

			int key1 = iterator.next();
			int key2 = iterator.next();

			String url1 = nextPageUrlMap.get(key1);
			String url2 = nextPageUrlMap.get(key2);

			int index = getPageIndex(url1, url2);
			if (index == -1) {
				return;
			}
			int firstKey = nextPageUrlMap.firstKey();
			int lastKey = nextPageUrlMap.lastKey();

			StringBuffer sb = new StringBuffer(url1);

			for (int i = firstKey; i <= lastKey; i++) {
				boolean bl = nextPageUrlMap.containsKey(i);

				if (!bl) {
					String.valueOf(i);
					if (i > 10) {
						sb.delete(index, index + 2);
					} else {
						sb.deleteCharAt(index);
					}
					sb.insert(index, i);
					String newUrl = sb.toString();
					nextPageUrlMap.put(i, newUrl);
				}
			}
		}
	}

	private int getPageIndex(String url1, String url2) {
		if (url1 == null || url2 == null || url1.length() != url2.length()) {
			return -1;
		}
		for (int i = 0; i < url1.length(); i++) {
			if (url1.charAt(i) != url2.charAt(i)) {
				return i;
			}
		}
		return -1;
	}

	private static String getDomain(String url) {
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
