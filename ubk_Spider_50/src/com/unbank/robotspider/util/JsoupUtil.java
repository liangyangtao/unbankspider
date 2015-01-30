package com.unbank.robotspider.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.IllegalCharsetNameException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {
	private final static Logger logger = Logger.getLogger(JsoupUtil.class);

	// 输入url得到document
	public static Document readUrl(String newsUrl) {
		if (newsUrl == null || newsUrl.trim().isEmpty()) {
			return null;
		}
		newsUrl = changeHanzi(newsUrl);
		Document doc = null;
		Connection conn = null;
		try {
			conn = Jsoup.connect(newsUrl.toString());
			conn.header("User-Agent",
					"Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");
			conn.header("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			// conn.header("Accept-Encoding", "gzip,deflate,sdch");
			conn.header("Accept-Language", "zh-CN,zh;q=0.8");
			conn.header("Connection", "keep-alive");
			doc = conn.timeout(5 * 1000).get();
		} catch (Exception e) {
			logger.info("读取Url         " + newsUrl + "           失败", e);
			try {
				if (conn != null) {
					doc = conn.timeout(5 * 1000).post();
				}
			} catch (IOException e1) {
				logger.info("读取Url        " + newsUrl + "          失败", e);
			} catch (Exception e2) {

				if (e2 instanceof IllegalCharsetNameException) {
					logger.error("读取Url        " + newsUrl + "          失败", e);
				}

			}

		}
		return doc;
	}

	// 输入文件得到document
	public static Document readFile(String fileName, String charsetName) {
		if (fileName == null || fileName.trim().isEmpty()) {
			return null;
		}
		if (charsetName == null || charsetName.isEmpty()) {
			charsetName = "utf-8";
		}
		Document doc = null;
		try {
			doc = Jsoup.parse(new File(fileName), charsetName);
		} catch (IOException e) {
			logger.info("读取文件失败", e);
		}
		return doc;
	}

	// 将URL 中特殊字符的转换一下
	public static String changeHanzi(String url) {
		if (url == null) {
			return null;
		}
		char[] tp = url.toCharArray();
		String now = "";
		for (char ch : tp) {
			if (ch >= 0x4E00 && ch <= 0x9FA5) {
				try {
					now += URLEncoder.encode(ch + "", "gbk");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else if (ch == '{' || ch == '|' || ch == '}' || ch == ',') {
				try {
					now += URLEncoder.encode(ch + "", "gbk");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else {
				now += ch;
			}

		}
		return now;
	}

	// 保存内容中的图片
	public static String saveImage(String imgSrc) {
		if (imgSrc == null || imgSrc.trim().isEmpty()) {
			return null;
		}
		InputStream is = null;
		String imageName = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bus = null;
		try {
			if (!imgSrc.toLowerCase().startsWith("http")) {
				return null;
			}
			URL net = new URL(imgSrc);
			HttpURLConnection connection = (HttpURLConnection) net
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(8000);
			connection
					.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/533.4 (KHTML, like Gecko) Chrome/5.0.375.99 Safari/533.4");

			int times = 3;

			while (times > 0 && is == null) {
				connection.setReadTimeout(8000 + 3000 * (3 - times));
				is = connection.getInputStream();
				times--;
			}

			bis = new BufferedInputStream(is);

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(GregorianCalendar.DATE, 0);
			java.util.Date date1 = calendar.getTime();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String today = simpleDateFormat.format(date1);
			imageName = "//10.0.2.35/images/" + today + "/";
			File file = new File(imageName);
			if (!file.exists()) {
				file.mkdirs();

			}
			imageName = "//10.0.2.35/images/" + today + "/"
					+ MD5.GetMD5Code(imgSrc + new Date().getTime()) + ".jpg";
			file = new File(imageName);
			bus = new BufferedOutputStream(new FileOutputStream(file));
			byte[] buf = new byte[2048];
			int length = bis.read(buf);
			while (length != -1) {
				bus.write(buf, 0, length);
				length = bis.read(buf);
			}
		} catch (IOException e) {
			logger.info("保存图片失败", e);
			return null;

		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.info("关闭输入流失败", e);
				}
			}

			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					logger.info("关闭数据流失败", e);
				}
			}
			if (bus != null) {
				try {
					bus.close();
				} catch (IOException e) {
					logger.info("关闭数据流失败", e);
				}
			}
		}
		if (imageName != null) {
			imageName = "http://10.0.2.35:8080/unbankImage/"
					+ imageName.substring(12);
		}
		return imageName;
	}

	// 保存内容中的图片
	public static String saveImage2(String imgSrc) {
		if (imgSrc == null || imgSrc.trim().isEmpty()) {
			return null;
		}
		String imageName = null;
		try {
			if (!imgSrc.toLowerCase().startsWith("http")) {
				return null;
			}
			URL net = new URL(imgSrc);
			HttpURLConnection connection = (HttpURLConnection) net
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection
					.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/533.4 (KHTML, like Gecko) Chrome/5.0.375.99 Safari/533.4");
			BufferedInputStream bis = new BufferedInputStream(
					connection.getInputStream());
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(GregorianCalendar.DATE, 0);
			java.util.Date date1 = calendar.getTime();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String today = simpleDateFormat.format(date1);
			imageName = "//10.0.0.51/images/" + today + "/";
			File file = new File(imageName);
			if (!file.exists()) {
				file.mkdirs();

			}
			imageName = "//10.0.0.51/images/" + today + "/"
					+ MD5.GetMD5Code(imgSrc + new Date().getTime()) + ".jpg";
			file = new File(imageName);
			BufferedOutputStream bus = new BufferedOutputStream(
					new FileOutputStream(file));
			byte[] buf = new byte[2048];
			int length = bis.read(buf);
			while (length != -1) {
				bus.write(buf, 0, length);
				length = bis.read(buf);
			}
			bis.close();
			bus.close();
		} catch (IOException e) {
			logger.info("保存图片失败", e);
			return null;

		}
		if (imageName != null) {
			imageName = "http://10.0.2.35:8080/unbankImage/"
					+ imageName.substring(12);
		}
		return imageName;
	}

	// 获取标题的位置
	public static Element getMaxTitleElement(Document listDocument) {
		Element bodyElement = listDocument.body();
		if (bodyElement == null) {
			return null;
		}
		Elements childElements = bodyElement.children();
		for (int i = 0; i < childElements.size() - 1; i++) {
			for (int j = i + 1; j < childElements.size(); j++) { // 如果包含标题
				String itext = childElements.get(i).text();
				int iLength = itext.length();
				String jtext = childElements.get(j).text();
				int jLength = jtext.length();
				if (iLength < jLength) {
					Element tempElemnt = childElements.get(i);
					childElements.set(i, childElements.get(j));
					childElements.set(j, tempElemnt);
				}
			}
		} // 排序
		return childElements.first();
	}


}
