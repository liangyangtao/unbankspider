package com.unbank.test.content;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unbank.robotspider.fetch.Fetcher;
import com.unbank.robotspider.fetch.NativeStringFetcher;
import com.unbank.robotspider.parser.TitlePaser;
import com.unbank.robotspider.util.GetNewsContent;
import com.unbank.robotspider.util.GetNewsDate;
import com.unbank.robotspider.util.StringUtil;
import com.unbank.test.FileOperate;

public class Verifier {

	private final static Logger logger = Logger.getLogger(Verifier.class);
	private static final String pagePath = "D:/TestParser/urls.txt";
	private static final String SOURCEPATH = "D:/TestParser/sourcePages/";

	@Test
	public void test() {

		new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		Fetcher fetcher = new NativeStringFetcher();
		SourcePageReader spr = new SourcePageReader();

		// id和url的键值对
		Map<String, String> sourcePageMap = getSourcePage();
		Map<Integer, Map<String, String>> sourceMap = spr.getSource();

		TestCaseReader tcr = new TestCaseReader();
		Map<Integer, Map<String, Page>> testCasePages = tcr.getPages();

		Set<Integer> keySet = sourceMap.keySet();
		Iterator<Integer> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			int key = iterator.next();
			Map<String, String> map = sourceMap.get(key);

			Set<String> set = map.keySet();
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				String k = it.next();
				logger.info("website_id:" + k);
				Document document = fetcher.fetchText(map.get(k));
				String title = "";
				title = extractNewsTitle(document);

				String content = extractNewsContent(sourcePageMap.get(k),
						document, title);

				Page page = new Page();
				page.setContent(content);
				page.setTitle(title);
				logger.info("SourceContentWithoutImgTag:"
						+ page.getContentWithoutImg());
				Page t_page = testCasePages.get(key).get(k);
				logger.info("testCaseContentWithoutImgTag:"
						+ t_page.getContentWithoutImg());
				assertEquals(page, t_page);
				logger.info("=======================Test Pass:" + k
						+ "======================");
			}
		}

	}

	// 获取新闻时间
	private static Date extractNewsDate(Document document) {
		if (document == null) {
			return null;
		}
		Date webDate = GetNewsDate.getDate(document);
		if (webDate == null) {
			webDate = new Date();
		}
		// 抓取时间 直接在数据库中插入即可
		String webDateStr = StringUtil.dateToString(webDate);
		webDate = StringUtil.stringToDate(webDateStr);
		return webDate;
	}

	// 获取新闻标题
	private static String extractNewsTitle(Document document) {
		String title = new TitlePaser().getTitle(document);
		logger.info("title:" + title);
		title = title.trim().replaceAll("：", ":").replaceAll("'", "")
				.replaceAll("·", "");
		if (title.contains("�") || title.contains("熶") || title.contains("銆")
				|| title.contains("為")) {
			return null;
		}
		return title;
	}

	// 获取新闻内容
	private static String extractNewsContent(String newsUrl, Document document,
			String title) {
		String content = GetNewsContent.parser(newsUrl, document, title);
		if (content == null) {
			logger.info("内容为空");
			return null;
		}
		content = content.trim().replaceAll("：", ":").replaceAll("'", "");
		if (content.contains("�") || content.contains("熶")
				|| content.contains("銆") || content.contains("為")) {
			logger.info("有乱码");
			return null;
		}

		return content;
	}

	private static Map<String, String> getSourcePage() {
		List<String> urlList = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();

		String path = SOURCEPATH + "/source.txt";

		urlList = FileOperate.readLines(path);
		for (String s : urlList) {
			if (!StringUtils.isBlank(s)) {
				String[] ss = s.split(" ");
				if (ss.length == 2) {
					String id = ss[0];
					String url = ss[1];
					map.put(id, url);
				}
			}
		}
		return map;
	}
}
