package com.unbank.robotspider.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/***
 * 
 * @author 梁杨桃
 * 
 */
public class StringUtil {
	private final static Logger logger = Logger.getLogger(StringUtil.class);

	public static boolean isRegex(String html, String regex) {
		Pattern patten = Pattern.compile(regex);
		Matcher mat = patten.matcher(html);
		if (mat.find()) {
			return true;
		}
		return false;
	}

	public static String getStringByRegList(String html, String regex,
			int number) {
		Pattern patten = Pattern.compile(regex);
		Matcher mat = patten.matcher(html);
		StringBuffer sb = new StringBuffer();
		while (mat.find()) {
			if (number == -1) {
				sb.append(mat.group());
				continue;
			}
			if (number > 0) {
				sb.append(mat.group());
				number--;
			} else {
				break;
			}
		}

		return sb.toString();
	}

	public static String getStringByReg(String content, String reg) {
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(content);
		String result = "";
		if (matcher.find()) {
			result = matcher.group();
		}
		return result;
	}

	/**
	 * 时间转换为字符串
	 * 
	 * */
	public static String dateToString(Date time) {
		if (time == null) {
			time = new Date();
		}
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ctime = formatter.format(time);
		return ctime;
	}

	public static void main(String[] args) {
		// System.out.println(dateToString(stringToDate("14-03-07")));
		System.out
				.println(getStringByReg(
						"   <span id='pubtime_baidu'>2014-05-26 15:54:45</span>",
						"(\\d{2,4}[-/.]\\d{1,2}[-/.]\\d{1,2})|(\\d{2,4}年\\d{1,2}月\\d{1,2}日)|(\\d{2,4}年 \\d{1,2}月 \\d{1,2}日)|(\\d{1,2}月\\d{1,2}日)"));
	}

	/**
	 * 字符串转换为java.util.Date<br>
	 * 支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD at 22:10:59 PSD'<br>
	 * yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00'<br>
	 * yy/MM/dd HH:mm:ss pm 如 '2002/1/1 17:55:00 pm'<br>
	 * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00' <br>
	 * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am' <br>
	 * 
	 * @param time
	 *            String 字符串<br>
	 * @return Date 日期<br>
	 */
	public static Date stringToDate(String time) {
		Date ctime = null;
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);// 得到年

		if (time == null || time.trim().isEmpty()) {
			ctime = new Date();
		} else {
			SimpleDateFormat formatter = null;
			time = time.trim();
			String reg = "";
			String format = "";
			if (time.contains("日")) {
				String temp[] = time.split("日");

				temp[0] = temp[0].replace(" ", "");
				if (temp.length == 2) {
					time = temp[0] + "日" + temp[1];
				} else {
					time = temp[0] + "日";
				}
			}

			if (time.contains("-")) {
				reg = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
				if (isRegex(time, reg)) {
					format = "yyyy-MM-dd HH:mm:ss";
				} else {
					reg = "\\d{2}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
					if (isRegex(time, reg)) {
						format = "yyyy-MM-dd HH:mm:ss";
						time = String.valueOf(year).substring(0, 2) + time;
					} else {
						reg = "\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
						if (isRegex(time, reg)) {
							format = "yyyy-MM-dd HH:mm:ss";
							time = year + "-" + time;
						} else {
							reg = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}";
							if (isRegex(time, reg)) {
								format = "yyyy-MM-dd HH:mm";
							} else {
								reg = "\\d{2}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}";
								if (isRegex(time, reg)) {
									format = "yyyy-MM-dd HH:mm";
									time = String.valueOf(year).substring(0, 2)
											+ time;
								} else {
									reg = "\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}";
									if (isRegex(time, reg)) {
										format = "yyyy-MM-dd HH:mm";
										time = year + "-" + time;
									} else {
										reg = "\\d{4}-\\d{1,2}-\\d{1,2}";
										if (isRegex(time, reg)) {
											format = "yyyy-MM-dd";
										} else {

											reg = "\\d{2}-\\d{1,2}-\\d{1,2}";
											if (isRegex(time, reg)) {
												format = "yyyy-MM-dd";
												time = String.valueOf(year)
														.substring(0, 2) + time;

											} else {
												reg = "\\d{1,2}-\\d{1,2}";
												if (isRegex(time, reg)) {
													format = "yyyy-MM-dd";
													time = year + "-" + time;
												}
											}

										}
									}
								}

							}
						}

					}

				}
			} else if (time.contains("/")) {
				reg = "\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
				if (isRegex(time, reg)) {
					format = "yyyy/MM/dd HH:mm:ss";
				} else {
					reg = "\\d{2}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
					if (isRegex(time, reg)) {
						format = "yyyy/MM/dd HH:mm:ss";
						time = String.valueOf(year).substring(0, 2) + time;
					} else {
						reg = "\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
						if (isRegex(time, reg)) {
							format = "yyyy/MM/dd HH:mm:ss";
							time = year + "/" + time;
						} else {
							reg = "\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}";
							if (isRegex(time, reg)) {
								format = "yyyy/MM/dd HH:mm";
							} else {
								reg = "\\d{2}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}";
								if (isRegex(time, reg)) {
									format = "yyyy/MM/dd HH:mm";
									time = String.valueOf(year).substring(0, 2)
											+ time;
								} else {
									reg = "\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}";
									if (isRegex(time, reg)) {
										format = "yyyy/MM/dd HH:mm";
										time = year + "/" + time;
									} else {
										reg = "\\d{4}/\\d{1,2}/\\d{1,2}";
										if (isRegex(time, reg)) {
											format = "yyyy/MM/dd";
										} else {

											reg = "\\d{2}/\\d{1,2}/\\d{1,2}";
											if (isRegex(time, reg)) {
												format = "yyyy/MM/dd";
												time = String.valueOf(year)
														.substring(0, 2) + time;

											} else {
												reg = "\\d{1,2}/\\d{1,2}";
												if (isRegex(time, reg)) {
													format = "yyyy/MM/dd";
													time = year + "/" + time;
												}
											}

										}
									}
								}

							}
						}

					}

				}
			} else if (time.contains(".")) {
				reg = "\\d{4}.\\d{1,2}.\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
				if (isRegex(time, reg)) {
					format = "yyyy.MM.dd HH:mm:ss";
				} else {
					reg = "\\d{2}.\\d{1,2}.\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
					if (isRegex(time, reg)) {
						format = "yyyy.MM.dd HH:mm:ss";
						time = String.valueOf(year).substring(0, 2) + time;
					} else {
						reg = "\\d{1,2}.\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
						if (isRegex(time, reg)) {
							format = "yyyy.MM.dd HH:mm:ss";
							time = year + "." + time;
						} else {
							reg = "\\d{4}.\\d{1,2}.\\d{1,2} \\d{1,2}:\\d{1,2}";
							if (isRegex(time, reg)) {
								format = "yyyy.MM.dd HH:mm";
							} else {
								reg = "\\d{2}.\\d{1,2}.\\d{1,2} \\d{1,2}:\\d{1,2}";
								if (isRegex(time, reg)) {
									format = "yyyy.MM.dd HH:mm";
									time = String.valueOf(year).substring(0, 2)
											+ time;
								} else {
									reg = "\\d{1,2}.\\d{1,2} \\d{1,2}:\\d{1,2}";
									if (isRegex(time, reg)) {
										format = "yyyy.MM.dd HH:mm";
										time = year + "." + time;
									} else {
										reg = "\\d{4}.\\d{1,2}.\\d{1,2}";
										if (isRegex(time, reg)) {
											format = "yyyy.MM.dd";
										} else {

											reg = "\\d{2}.\\d{1,2}.\\d{1,2}";
											if (isRegex(time, reg)) {
												format = "yyyy.MM.dd";
												time = String.valueOf(year)
														.substring(0, 2) + time;

											} else {
												reg = "\\d{1,2}.\\d{1,2}";
												if (isRegex(time, reg)) {
													format = "yyyy.MM.dd";
													time = year + "." + time;
												}
											}

										}
									}
								}

							}
						}

					}

				}
			} else {
				reg = "\\d{4}年\\d{1,2}月\\d{1,2}日 \\d{1,2}:\\d{1,2}:\\d{1,2}";
				if (isRegex(time, reg)) {
					format = "yyyy年MM月dd日 HH:mm:ss";
				} else {
					reg = "\\d{2}年\\d{1,2}月\\d{1,2}日 \\d{1,2}:\\d{1,2}:\\d{1,2}";
					if (isRegex(time, reg)) {
						format = "yyyy年MM月dd日 HH:mm:ss";
						time = String.valueOf(year).substring(0, 2) + time;
					} else {
						reg = "\\d{1,2}月\\d{1,2}日 \\d{1,2}:\\d{1,2}:\\d{1,2}";
						if (isRegex(time, reg)) {
							format = "yyyy年MM月dd日 HH:mm:ss";
							time = year + "年" + time;
						} else {
							reg = "\\d{4}年\\d{1,2}月\\d{1,2}日 \\d{1,2}:\\d{1,2}";
							if (isRegex(time, reg)) {
								format = "yyyy年MM月dd日 HH:mm";
							} else {
								reg = "\\d{2}年\\d{1,2}月\\d{1,2}日 \\d{1,2}:\\d{1,2}";
								if (isRegex(time, reg)) {
									format = "yyyy年MM月dd日 HH:mm";
									time = String.valueOf(year).substring(0, 2)
											+ time;
								} else {
									reg = "\\d{1,2}月\\d{1,2}日 \\d{1,2}:\\d{1,2}";
									if (isRegex(time, reg)) {
										format = "yyyy年MM月dd日 HH:mm";
										time = year + "年" + time;
									} else {
										reg = "\\d{4}年\\d{1,2}月\\d{1,2}日";
										if (isRegex(time, reg)) {
											format = "yyyy年MM月dd日";
										} else {

											reg = "\\d{2}年\\d{1,2}月\\d{1,2}日";
											if (isRegex(time, reg)) {
												format = "yyyy年MM月dd日";
												time = String.valueOf(year)
														.substring(0, 2) + time;

											} else {
												reg = "\\d{1,2}月\\d{1,2}日";
												if (isRegex(time, reg)) {
													format = "yyyy年MM月dd日";
													time = year + "年" + time;
												}
											}

										}
									}
								}

							}
						}

					}

				}
			}
			try {
				// logger.info(time);
				// logger.info(format);
				formatter = new SimpleDateFormat(format);
				ctime = formatter.parse(time);
			} catch (ParseException e) {
				logger.info(time);
				logger.info(format);
				logger.error("时间转换异常" ,e);
				ctime = new Date();
			}
		}
		// logger.info(ctime);
		return ctime;
	}

	public static boolean isDate(String str_input, String rDateFormat) {
		if (str_input != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(rDateFormat);
			formatter.setLenient(false);
			try {
				formatter.format(formatter.parse(str_input));
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		return false;

	}

	public static String Html2Text(String htmlStr) {
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		java.util.regex.Pattern p_html1;
		java.util.regex.Matcher m_html1;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>pP]+>"; // 定义HTML标签的正则表达式
			String regEx_html1 = "<[^>pP]+/>";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}

	public static boolean isNmber(String text) {

		Pattern pattern = Pattern.compile("[0-9]");
		return pattern.matcher(text).matches();

	}

	static final char FIGURE_START = 65296;// 数字开始
	static final char FIGURE_END = 65305;// 数字END
	static final char CHAR_START = 65345;// 字母开始
	static final char CHAR_END = 65370;// 字母END
	static final int CONVERT_STEP = 65248; // 全角半角转换间隔
	static final char SBC_SPACE = 12288; // 全角空格 12288
	static final char DBC_SPACE = ' '; // 半角空格

	public static String angleConvertion(String str) {

		if (str == null) {
			return str;
		}
		StringBuilder sbud = new StringBuilder(str.length());
		char[] convertionChar = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {
			if (convertionChar[i] == 65282) {
				sbud.append((char) (8220));
				continue;
			}
			// (
			if (convertionChar[i] == 65288) {
				sbud.append((char) (40));
				continue;
			}
			// )
			if (convertionChar[i] == 65289) {
				sbud.append((char) (41));
				continue;
			}
			if (convertionChar[i] == 65285) {
				sbud.append((char) (37));
				continue;
			}
			if (convertionChar[i] == 65283) {
				sbud.append((char) (35));
				continue;
			}
			if (convertionChar[i] == 65312) {
				sbud.append((char) (64));
				continue;
			}
			if (convertionChar[i] == 65294) {
				sbud.append((char) (46));
				continue;
			}

			if (convertionChar[i] >= FIGURE_START
					&& convertionChar[i] <= FIGURE_END
					|| convertionChar[i] >= CHAR_START
					&& convertionChar[i] <= CHAR_END) { // 如果位于全角！到全角～区间内
				sbud.append((char) (convertionChar[i] - CONVERT_STEP));
			} else if (convertionChar[i] == SBC_SPACE) { // 如果是全角空格
				sbud.append(DBC_SPACE);
			} else { // 不处理全角空格，全角！到全角～区间外的字符
				sbud.append(convertionChar[i]);
			}
		}
		return sbud.toString();
	}
	
	
	/**
	 * 半角转全角
	 * 
	 * @param input
	 *            String.
	 * @return 全角字符串.
	 */
	public static String ToSBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);

			}
		}
		return new String(c);
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 *            String.
	 * @return 半角字符串
	 */
	public static String ToDBC(String input) {

		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);

			}
		}
		String returnString = new String(c);

		return returnString;
	}
}
