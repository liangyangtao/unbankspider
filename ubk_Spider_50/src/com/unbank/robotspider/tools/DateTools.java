package com.unbank.robotspider.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTools {

	public static boolean isRegex(String html, String regex) {
		Pattern patten = Pattern.compile(regex);
		Matcher mat = patten.matcher(html);
		if (mat.find()) {
			return true;
		}
		return false;
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
				formatter = new SimpleDateFormat(format);
				ctime = formatter.parse(time);
			} catch (ParseException e) {
				ctime = new Date();
			}
		}
		return ctime;
	}

	private Date changeHHmmss(Date newsTime) {
		String newsDateStr = dateToString(newsTime);
		if (newsDateStr.substring(11).contains("00:00:00")) {
			Date nowdate = new Date();
			String dateStr = dateToString(nowdate);
			String yestoday = getyyyyMMddTimeString(-1);
			String newstime = null;
			if (newsDateStr.substring(0, 10).equals(yestoday)) {
				newstime = newsDateStr.substring(0, 11) + "23:59:59";
			} else {
				newstime = newsDateStr.substring(0, 11) + dateStr.substring(11);
			}
			newsTime = stringToDate(newstime);

		}
		return newsTime;
	}

	private String getyyyyMMddTimeString(int num) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(GregorianCalendar.DATE, num);
		Date date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = simpleDateFormat.format(date);
		return dateString;
	}

}
