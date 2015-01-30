package com.unbank.robotspider.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.robotspider.entity.NewsInfoMiddleWare;
import com.unbank.robotspider.tools.DateTools;

public class NewsDatePaser extends BasePaser {

	public static Date getDate(Document document) {
		Element body = document.clone().body();
		if (body == null) {
			return null;
		}
		Elements elements = body.getAllElements();

		String[] regs = {
				"\\d{4}-(\\d{2}|\\d{1})-(\\d{2}|\\d{1})( {0,1}(\\d{2}|\\d{1}):\\d{2}(:\\d{2}){0,1}){0,1}",
				"\\d{4}年(\\d{2}|\\d{1})月(\\d{2}|\\d{1})日( {0,1}(\\d{2}|\\d{1}):\\d{2}(:\\d{2}){0,1}){0,1}",
				"\\d{4}.(\\d{2}|\\d{1}).(\\d{2}|\\d{1})( {0,1}(\\d{2}|\\d{1}):\\d{2}(:\\d{2}){0,1}){0,1}",
				"\\d{4}/(\\d{2}|\\d{1})/(\\d{2}|\\d{1})( {0,1}(\\d{2}|\\d{1}):\\d{2}(:\\d{2}){0,1}){0,1}",
				"\\d{8}" };
		String[] formatStrs = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
				"yyyy-MM-ddHH:mm:ss", "yyyy-MM-ddHH:mm", "yyyy-MM-dd",
				"yyyy年MM月dd日 HH:mm:ss", "yyyy年MM月dd日 HH:mm",
				"yyyy年MM月dd日HH:mm:ss", "yyyy年MM月dd日HH:mm", "yyyy年MM月dd日",
				"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
				"yyyy/MM/ddHH:mm:ss", "yyyy/MM/ddHH:mm", "yyyy/MM/dd",
				"yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm",
				"yyyy.MM.ddHH:mm:ss", "yyyy.MM.ddHH:mm", "yyyy.MM.dd",
				"yyyyMMdd" };

		Pattern num_pattern = Pattern.compile("\\d");
		Date date = null;
		Long time1 = System.currentTimeMillis();
		Long t = 1000 * 60 * 60 * 24 * 12 * 365L;

		for (int i = 0; i < elements.size(); i++) {
			int size = elements.get(i).getElementsMatchingOwnText(num_pattern)
					.size();// 含有数字的元素
			if (size > 0) {
				for (int j = 0; j < regs.length; j++) {
					Pattern pattern = Pattern.compile(regs[j]);
					Elements es = elements.get(i).getElementsMatchingOwnText(
							pattern);
					if (es.size() > 0) {// 是否属于日期格式
						Matcher matcher = pattern.matcher(es.text());
						if (matcher.find()) {
							for (int m = 0; m < formatStrs.length; m++) {
								Date d = StringToDate(matcher.group(),
										formatStrs[m]);
								if (d != null) {
									Long times = Math.abs(d.getTime() - time1);
									if ((times < t) && times > 1000 * 60 * 10) {
										date = d;
										t = Math.abs(d.getTime() - time1);
									}
								}
							}
						}

					}
				}
			}
		}
		return date;
	}

	/**
	 * 
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static Date StringToDate(String dateStr, String formatStr) {
		DateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
		}
		return date;
	}

	public boolean fillNewsTime(NewsInfoMiddleWare articleInfo,
			Document document) {
		Date webDate = analyzeWebDate(document, articleInfo);
		if (webDate == null) {
			return false;
		} else {
			articleInfo.setNewsTime(changeTimeHHmmss(webDate));
			return true;
		}
	}

	private Date analyzeWebDate(Document document,
			NewsInfoMiddleWare articleInfo) {
		Date webDate = getDate(document);
		if (webDate == null) {
			webDate = new Date();
		}
		String webDateStr = DateTools.dateToString(webDate);
		webDate = DateTools.stringToDate(webDateStr);
		String today = getyyyyMMddTimeString(0);
		String yestoday = getyyyyMMddTimeString(-1);
		if (articleInfo.getTask() == 2) {
			if (webDateStr.substring(0, 10).equals(today)
					|| webDateStr.substring(0, 10).equals(yestoday)) {
				return webDate;
			} else {
				return null;
			}
		} else if (articleInfo.getTask() == 1) {
			return webDate;
		} else {
			return null;
		}

	}

	private String getyyyyMMddTimeString(int num) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(GregorianCalendar.DATE, num);
		Date date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = simpleDateFormat.format(date);
		return today;
	}

	private Date changeTimeHHmmss(Date newsTime) {
		String newsDateStr = DateTools.dateToString(newsTime);
		if (newsDateStr.substring(11).contains("00:00:00")) {
			Date nowdate = new Date();
			String dateStr = DateTools.dateToString(nowdate);
			String yestoday = getyyyyMMddTimeString(-1);
			String newstime = null;
			if (newsDateStr.substring(0, 10).equals(yestoday)) {
				newstime = newsDateStr.substring(0, 11) + "23:59:59";
			} else {
				newstime = newsDateStr.substring(0, 11) + dateStr.substring(11);
			}
			newsTime = DateTools.stringToDate(newstime);

		}
		return newsTime;
	}

}
