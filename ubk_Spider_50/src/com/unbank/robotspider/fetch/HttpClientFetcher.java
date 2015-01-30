package com.unbank.robotspider.fetch;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.unbank.robotspider.filter.coding.CodingBaseFilter;

public class HttpClientFetcher {
	private final static Logger logger = Logger
			.getLogger(HttpClientFetcher.class);

	public String getUrlRespHtml(String pageUrl) {
		String respHtml = null;
		URL url = changeURLbyString(pageUrl);
		URI uri = changeURIbyURL(url);
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(5000).setConnectTimeout(5000)
				.setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build();// 设置请求和传输超时时间
		CookieStore cookieStore = new BasicCookieStore();
		HttpRequestBase getReq = null;
		String[] postHost = new String[] { "www.ebrun.com", "www.ccgp.gov.cn" };
		Set<String> postHosts = new HashSet<String>();
		for (String string : postHost) {
			postHosts.add(string);
		}

		if (postHosts.contains(getDomain(pageUrl))) {
			getReq = new HttpPost(uri);
		} else {
			getReq = new HttpGet(uri);
		}

		getReq.setConfig(requestConfig);
		getReq.addHeader("Host", getDomain(pageUrl));
		getReq.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");
		getReq.addHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		getReq.addHeader("Accept-Language",
				"zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		getReq.addHeader("Accept-Encoding", "gzip, deflate");
		getReq.addHeader("Connection", "keep-alive");
		getReq.addHeader("Referer", pageUrl);
		getReq.addHeader("Cache-Control", "max-age=0");
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setCookieStore(cookieStore);
		try {
			response = httpClient.execute(getReq, localContext);
		} catch (Exception e) {
			logger.info(url + "=====读取出错===", e);
		}
		try {
			if (response != null
					&& response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity respEnt = response.getEntity();
				String htmlCharset = new CodingBaseFilter().getCharset(pageUrl);
				if (htmlCharset == null) {
					logger.info(url);
					htmlCharset = "UTF-8";
				}
				respHtml = EntityUtils.toString(respEnt, htmlCharset);
				respHtml = new String(respHtml.getBytes(htmlCharset), "UTF-8");

			}
		} catch (ClientProtocolException cpe) {
			logger.info(url + "=====读取出错===", cpe);
		} catch (IOException ioe) {
			logger.info(url + "=====读取出错===", ioe);
		} finally {

			try {
				cookieStore.clear();
				getReq.abort();
				if (response != null) {
					response.close();
				}
				httpClient.close();
			} catch (IOException e) {
				logger.info("", e);
			}
		}

		return respHtml;
	}

	private URL changeURLbyString(String pageUrl) {
		URL url = null;
		try {
			url = new URL(pageUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}

	private URI changeURIbyURL(URL url) {
		URI uri = null;
		try {
			uri = new URI(url.getProtocol(), url.getHost(), url.getPath(),
					url.getQuery(), null);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return uri;
	}

	public String getDomain(String url) {
		String domain = changeURLbyString(url).getHost();
		return domain;
	}
}
