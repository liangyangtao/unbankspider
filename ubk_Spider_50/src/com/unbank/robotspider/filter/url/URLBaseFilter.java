package com.unbank.robotspider.filter.url;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.unbank.robotspider.UnbankCrawler;

public class URLBaseFilter implements URLFilter {

	public static Log logger = LogFactory.getLog(URLBaseFilter.class);
	SimpleBloomFilter simpleBloomFilter = UnbankCrawler.getFilter();

	public boolean checkNewsURL(String url) {
		String endWithUrls[] = new String[] { ".mp4", ".pdf", ".mp3", ".flv",
				".swf", ".gif", ".bmp", ".jpg", ".png", ".gif", ".rar", ".zip",
				".7z", ".exe", ".jpeg", ".dll", ".doc", };
		String containsUrls[] = new String[] { "english", "images", "img",
				"blog", "reports", "http://syjhs.askci.com", ".bbs." };

		if (url == null || url.isEmpty()) {
			return false;
		} else {
			for (int i = 0; i < containsUrls.length; i++) {
				if (url.contains(containsUrls[i])) {
					return false;
				}
			}
			for (int i = 0; i < endWithUrls.length; i++) {
				if (url.endsWith(endWithUrls[i])) {
					return false;
				}
			}
			if (simpleBloomFilter.contains(url)) {
				return false;
			} else {
				simpleBloomFilter.add(url);
			}
		}
		return true;
	}
}
