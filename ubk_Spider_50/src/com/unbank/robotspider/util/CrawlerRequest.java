package com.unbank.robotspider.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.ContentEncodingHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class CrawlerRequest {

	private final static Logger logger = Logger.getLogger(CrawlerRequest.class);
	private static String constUserAgent_Chrome = "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/533.4 (KHTML, like Gecko) Chrome/5.0.375.99 Safari/533.4";

	public String getUrlRespHtml(String url) {
		return getUrlRespHtml(url, null, null, 2000, "utf-8");
	}

	/***
	 *
	 * 
	 * 
	 */
	public String getUrlRespHtml(String pageUrl,
			List<NameValuePair> headerDict, List<NameValuePair> postDict,
			int timeout, String htmlCharset) {
		String respHtml = "";
		String defaultCharset = "utf-8";
		CloseableHttpResponse response = null;
		HttpUriRequest request = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();
		URL url = null;
		try {
			url = new URL(pageUrl);
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
		}
		URI uri = null;
		try {
			uri = new URI(url.getProtocol(), url.getHost(), url.getPath(),
					url.getQuery(), null);
		} catch (URISyntaxException e2) {
			e2.printStackTrace();
		}// 防止pageUrl中出现空格
			// httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
			// CookiePolicy.BEST_MATCH);
			// httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
			// CookiePolicy.BEST_MATCH);

		// RequestConfig globalConfig = RequestConfig.custom()
		// .setCookieSpec(CookieSpecs.BEST_MATCH)
		// .build();
		// CloseableHttpClient httpclient = HttpClients.custom()
		// .setDefaultRequestConfig(globalConfig)
		// .build();
		// RequestConfig localConfig = RequestConfig.copy(globalConfig)
		// .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY)
		// .build();
		// HttpGet httpGet = new HttpGet("/");
		// httpGet.setConfig(localConfig);

		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(5000).setConnectTimeout(5000)
				.setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build();// 设置请求和传输超时时间

		CookieStore cookieStore = new BasicCookieStore();

		// logger.info(uri);
		if (postDict != null) {
			HttpPost postReq = new HttpPost(uri);
			postReq.setConfig(requestConfig);
			postReq.addHeader("User-Agent", constUserAgent_Chrome);
			// postReq.addHeader(
			// "Accept",
			// "application/x-ms-application, image/jpeg, application/xaml+xml, "
			// + "image/gif, image/pjpeg, application/x-ms-xbap, */*");
			// postReq.addHeader("Accept-Language", "zh-CN");
			// postReq.addHeader("", "zh-CN");
			// postReq.addHeader("Connection", "close");
			// postReq.addHeader("Content-Type", "text/html;charset=UTF-8");
			try {
				HttpEntity postBodyEnt = new UrlEncodedFormEntity(postDict,
						"UTF-8");
				postReq.setEntity(postBodyEnt);
			} catch (Exception e) {
				e.printStackTrace();
			}

			request = postReq;
		} else {
			HttpGet getReq = new HttpGet(uri);
			getReq.setConfig(requestConfig);
			getReq.addHeader("User-Agent", constUserAgent_Chrome);
			// getReq.addHeader(
			// "Accept",
			// "application/x-ms-application, image/jpeg, application/xaml+xml, "
			// + "image/gif, image/pjpeg, application/x-ms-xbap, */*");
			// getReq.addHeader("Accept-Language", "zh-CN");
			// getReq.addHeader("", "zh-CN");
			// getReq.addHeader("Connection", "close");
			request = getReq;

		}

		HttpClientContext localContext = HttpClientContext.create();
		localContext.setCookieStore(cookieStore);
		try {
			response = httpClient.execute(request, localContext);
		} catch (Exception e) {
			for (int i = 0; i < 5; i++) {
				if (response != null) {
					break;
				}
				try {
					Thread.sleep(((int) (Math.random() * 6) + 1) * 1000);
					response = httpClient.execute(request, localContext);
				} catch (Exception e1) {
					// logger.info("读取失败次数" + i);
				}

			}

		}
		try {
			if (response != null
					&& response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity respEnt = response.getEntity();
				// ContentType contentType = ContentType.getOrDefault(respEnt);
				// text/html; charset=utf-8
				// String charset = StringUtil.getStringByReg(
				// contentType.toString(), "charset=([^;]*)");
				// if (charset == null || charset.isEmpty()) {
				//
				// } else {
				// htmlCharset = charset.split("=")[1];
				// }
				if ((null == htmlCharset) || htmlCharset.isEmpty()) {
					htmlCharset = defaultCharset;
				}
				respHtml = EntityUtils.toString(respEnt, htmlCharset);

			} else {
				// 保存到数据库
			}
		} catch (ClientProtocolException cpe) {
			logger.info(url + "=====读取出错===" , cpe);
			// cpe.printStackTrace();
		} catch (IOException ioe) {
			logger.info(url + "=====读取出错===" , ioe);
			// ioe.printStackTrace();
		} finally {

			try {
				cookieStore.clear();
				request.abort();
				if (response != null) {

					response.close();
				}
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("",e);
			}
		}

		return respHtml;
	}

}
