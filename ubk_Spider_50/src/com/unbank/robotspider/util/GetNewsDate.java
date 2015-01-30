package com.unbank.robotspider.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.robotspider.action.model.normal.BaseFilter;

public class GetNewsDate {

	public static Date getDate(Document document) {
		Element body = document.clone().body();
		new BaseFilter().remove(body);
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

}
