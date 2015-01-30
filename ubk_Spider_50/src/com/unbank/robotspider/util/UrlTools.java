package com.unbank.robotspider.util;

/**
 * 
 * @author 梁杨桃
 * 
 * 
 * 
 * */
public class UrlTools {


	public static String getImageURL(String url, String initurl) {
		String fullurl = null;
		String root = null;
		String uri = null;
		if (url == null || initurl == null) {
			return "";
		}
		url = url.trim();
		initurl = initurl.trim().replace("\'", "");
		if (initurl == null || initurl.trim().isEmpty()) {
			return null;
		}
		if (initurl.startsWith("http") || initurl.startsWith("ftp")) {
			return initurl;
		} else {
			if (url.startsWith("http") || url.startsWith("ftp")) {
				String[] ss = url.split("//");
				if (!ss[1].contains("/")) {
					root = ss[0] + "//" + ss[1];
				} else {
					root = ss[0] + "//"
							+ ss[1].substring(0, ss[1].indexOf("/"));
				}
				root = root + "/";
			}

			uri = url.substring(0, url.lastIndexOf("/"));
			if (initurl.startsWith("../../../../")) {
				String temp = uri.substring(0, uri.lastIndexOf("/"));
				temp = temp.substring(0, temp.lastIndexOf("/"));
				temp = temp.substring(0, temp.lastIndexOf("/"));
				fullurl = temp.substring(0, temp.lastIndexOf("/"))
						+ initurl.substring(11);
			} else if (initurl.startsWith("../../../")) {
				String temp = uri.substring(0, uri.lastIndexOf("/"));
				temp = temp.substring(0, temp.lastIndexOf("/"));
				fullurl = temp.substring(0, temp.lastIndexOf("/"))
						+ initurl.substring(8);
			} else if (initurl.startsWith("../../")) {

				fullurl = uri + initurl.substring(5);

			} else if (initurl.startsWith("../")) {
				fullurl = uri.substring(0, uri.lastIndexOf("/"))
						+ initurl.substring(2);
			} else if (initurl.startsWith("./")) {
				fullurl = uri + initurl.substring(1);
			} else if (initurl.startsWith("/")) {
				fullurl = root + initurl.substring(1);
			} else if (initurl.contains("javascript")) {
				return null;
			} else {
				fullurl = uri + "/" + initurl;
			}
			// fullurl = uri + "/" + initurl;
		}

		return fullurl;
	}

	// 根据根节点和 a标签的链接得到 a标签的标准的链接
	public static String getFullUrl(String url, String initurl) {

		if (initurl.endsWith(".mp4") || initurl.endsWith(".pdf")
				|| initurl.endsWith(".mp3") || initurl.endsWith(".flv")
				|| initurl.endsWith(".swf") || initurl.endsWith(".gif")
				|| initurl.endsWith(".bmp") || initurl.endsWith(".jpg")
				|| initurl.endsWith(".png") || initurl.endsWith(".gif")
				|| initurl.endsWith(".rar") || initurl.endsWith(".zip")
				|| initurl.endsWith(".7z") || initurl.endsWith(".exe")
				|| initurl.endsWith(".jpeg") || initurl.endsWith(".dll")
				|| initurl.endsWith(".doc") || initurl.contains("bbs")
				|| initurl.contains("english") || initurl.contains("images")
				|| initurl.contains("img")) {
			return null;
		}

		String fullurl = null;
		String root = null;
		String uri = null;
		if (url == null || initurl == null) {
			return "";
		}
		url = url.trim();
		initurl = initurl.trim().replace("\'", "");
		if (initurl == null || initurl.trim().isEmpty()) {
			return null;
		}
		if (initurl.startsWith("http") || initurl.startsWith("ftp")) {
			return initurl;
		} else {
			if (url.startsWith("http") || url.startsWith("ftp")) {
				String[] ss = url.split("//");
				if (!ss[1].contains("/")) {
					root = ss[0] + "//" + ss[1];
				} else {
					root = ss[0] + "//"
							+ ss[1].substring(0, ss[1].indexOf("/"));
				}
				root = root + "/";
			}
			uri = url.substring(0, url.lastIndexOf("/"));
			if (initurl.startsWith("../../../../")) {
				String temp = uri.substring(0, uri.lastIndexOf("/"));
				temp = temp.substring(0, temp.lastIndexOf("/"));
				temp = temp.substring(0, temp.lastIndexOf("/"));
				fullurl = temp.substring(0, temp.lastIndexOf("/"))
						+ initurl.substring(11);
			} else if (initurl.startsWith("../../../")) {
				String temp = uri.substring(0, uri.lastIndexOf("/"));
				temp = temp.substring(0, temp.lastIndexOf("/"));
				fullurl = temp.substring(0, temp.lastIndexOf("/"))
						+ initurl.substring(8);
			} else if (initurl.startsWith("../../")) {
				String temp = uri.substring(0, uri.lastIndexOf("/"));
				fullurl = temp.substring(0, temp.lastIndexOf("/"))
						+ initurl.substring(5);
			} else if (initurl.startsWith("../")) {
				fullurl = uri.substring(0, uri.lastIndexOf("/"))
						+ initurl.substring(2);
			} else if (initurl.startsWith("./")) {
				fullurl = uri + initurl.substring(1);
			} else if (initurl.startsWith("/")) {
				fullurl = root + initurl.substring(1);
			} else if (initurl.contains("javascript")) {
				return null;
			} else {
				fullurl = uri + "/" + initurl;
			}

		}

		return fullurl;
	}

}
