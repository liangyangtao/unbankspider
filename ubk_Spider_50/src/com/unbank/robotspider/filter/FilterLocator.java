package com.unbank.robotspider.filter;

import java.net.MalformedURLException;
import java.net.URL;

public class FilterLocator {

	public String getDomain(String url) {
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
