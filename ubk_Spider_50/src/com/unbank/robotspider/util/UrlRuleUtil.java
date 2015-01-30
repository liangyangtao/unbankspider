package com.unbank.robotspider.util;


public class UrlRuleUtil {

	public static String getcheckURL(String url) {
		String url_rule = "";
		if (url.contains("0%PAGE")) {
			url_rule = url.replace("0%PAGE", "02");
		} else {
			url_rule = url.replace("%PAGE", "2");
		}
		return url_rule;
	}

//	public static List<String> getURLList(GrabURL grabURL) {
//		List<String> urls = new ArrayList<String>();
//		urls.add(grabURL.getUrl_home());
//		if (grabURL.getUrl_second() != null) {
//			urls.add(grabURL.getUrl_second());
//		}
//		if (grabURL.getUrl_third() != null) {
//			urls.add(grabURL.getUrl_third());
//		}
//
//		// String url = grabURL.getUrl_rule();
//		// if (url.contains("%PAGE")) {
//
//		// } else {
//		// return urls;
//		// }
//		/***
//		 * 此处不必翻页了， 没有意义,若要抓取历史数据的话，可以放开
//		 */
//		// for (int i = 4; i < 11; i++) {
//		// if (i < 10) {
//		// if (url.contains("0%PAGE")) {
//		// urls.add(url.replace("0%PAGE", "0" + i));
//		// } else {
//		// urls.add(url.replace("%PAGE", i + ""));
//		// }
//		// } else {
//		// if (url.contains("0%PAGE")) {
//		// urls.add(url.replace("0%PAGE", "" + i));
//		// } else {
//		// urls.add(url.replace("%PAGE", i + ""));
//		// }
//		// }
//		// }
//		return urls;
//	}

	public static String getURlRule(String a, String b) {
		a = a.trim();
		b = b.trim();
		char[] array1 = a.toCharArray();
		char[] array2 = b.toCharArray();
		int num = a.length();
		if (a.length() > b.length()) {
			num = b.length();
		} else {
			num = a.length();
		}
		StringBuffer sb = new StringBuffer();
		int index = 0;
		for (int i = 0; i < num; i++) {
			if (array1[i] == array2[i]) {
				if (index != 0) {
					break;
				}
				continue;
			} else {
				if (index == 0) {
					index = i;
				}
				sb.append(array1[i]);
			}
		}
		int indexOf = sb.length();
		if (indexOf == 0) {
			return a;
		}
		String before = a.substring(0, index);
		String after = a.substring(index + indexOf);
		String ruleURL = "";
		if (before.endsWith("0")) {
			ruleURL = before.substring(0, before.length() - 1) + "0%PAGE"
					+ after;
		} else {
			ruleURL = before + "%PAGE" + after;
		}
		return ruleURL;
	}
}
