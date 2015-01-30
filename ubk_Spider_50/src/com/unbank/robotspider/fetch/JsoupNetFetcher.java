package com.unbank.robotspider.fetch;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;

import com.unbank.robotspider.tools.MD5;
import com.unbank.robotspider.util.Values;

public class JsoupNetFetcher implements NetFetcher {
	private final static Logger logger = Logger
			.getLogger(JsoupNetFetcher.class);

	private static final int TIMEOUT = 5 * 1000;
	private static final short RETRYTIMES = 2;

	@Override
	public Document fetchText(String url) {
		boolean bl = verifyURL(url);
		if (!bl) {
			return null;
		}
		Document doc = getDocumentByJsoup(url);
		return doc;
	}

	public Document getDocumentByHttpClent(String url) {
		String content = new HttpClientFetcher().getUrlRespHtml(url);
		if (content == null) {
			return null;
		}
		Document document = Jsoup.parse(content, url);
		return document;

	}

	public Document getDocumentByJsoup(String url) {
		Document doc = null;
		Connection conn = null;
		conn = Jsoup.connect(url);
		fillConnection(url, conn);
		// GET方式获取内容
		try {
			doc = conn.ignoreContentType(true).timeout(TIMEOUT).get();
		} catch (IOException e) {
			if (e instanceof SocketTimeoutException
					|| e instanceof java.net.UnknownHostException) {
				for (int i = 0; i < RETRYTIMES; i++) {
					try {
						doc = conn.timeout(TIMEOUT + (int) (Math.random() * 3))
								.get();
					} catch (IOException e1) {

					} finally {
						if (doc != null) {
							break;
						}
					}
				}
			} else if (e instanceof EOFException) {

			} else {
				logger.info(url + "       ", e);
				return null;
			}

		} catch (Exception e) {
			logger.info(url + "        ", e);
		}
		// GET方式获取内容失败，尝试POST方式获取
		if (doc == null) {
			try {
				doc = conn.timeout(TIMEOUT).post();
			} catch (IOException e) {
				if (e instanceof SocketTimeoutException
						|| e instanceof java.net.UnknownHostException) {
					for (int i = 0; i < RETRYTIMES; i++) {
						try {
							doc = conn.timeout(
									TIMEOUT + (int) (Math.random() * 3)).post();
						} catch (IOException e1) {

						} finally {
							if (doc != null) {
								break;
							}
						}
					}
				} else {
					logger.info(url + "         ", e);
					return null;
				}

			} catch (Exception e) {
				logger.info(url + "         ", e);
			}
		}
		return doc;
	}

	private void fillConnection(String url, Connection conn) {
		conn.header("Host", getDomain(url));
		conn.header("User-Agent",
				"Mozilla/5.0 (Windows NT 5.1; rv:29.0) Gecko/20100101 Firefox/29.0");
		conn.header("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.header("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		conn.header("Accept-Encoding", "gzip, deflate");
		conn.header("Connection", "keep-alive");
		conn.header("Referer", url);
		conn.header("Cache-Control", "max-age=0");
	}

	@Override
	public String fetchImage(String imageSrc, ImageFilter filter) {
		if (!verifyURL(imageSrc)) {
			return null;
		}

		InputStream is = null;
		String imageName = null;
		BufferedInputStream bis = null;
		ImageInputStream iis = null;
		String imageFormatName = "";

		try {
			URL net = new URL(imageSrc);
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
				// logger.info("正在下载图片，图片源地址：" + imageSrc);
				connection.setReadTimeout(8000 + 3000 * (3 - times));
				is = connection.getInputStream();
				if (is != null) {
					break;
				}
				times--;
			}

			bis = new BufferedInputStream(is);

			Date today = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String todayStr = simpleDateFormat.format(today);

			// 存储图片的路径
			String imagePath = "//" + Values.v.IMAGEHOST + "/images/"
					+ todayStr + "/";

			File imageDir = new File(imagePath);
			if (!imageDir.exists()) {
				imageDir.mkdirs();
			}
			iis = ImageIO.createImageInputStream(is);

			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
			if (iter.hasNext()) {
				ImageReader reader = iter.next();
				reader.setInput(iis);
				imageFormatName = reader.getFormatName().toLowerCase();
				imageName = imagePath
						+ MD5.GetMD5Code(imageSrc + new Date().getTime()) + "."
						+ imageFormatName;
				int width = reader.getWidth(0);
				int height = reader.getHeight(0);

				// 添加例外过滤器
				if (filter != null) {
					boolean bl = filter.reject(imageSrc, width, height);

					if (bl) {
						return null;
					}
				}

				ImageReadParam irp = reader.getDefaultReadParam();
				BufferedImage bi = reader.read(0, irp);
				ImageIO.write(bi, imageFormatName, new File(imageName));
				// logger.info("下载图片：" + imageName);
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

			if (iis != null) {
				try {
					iis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (imageName != null) {
			imageName = "http://" + Values.v.IMAGEHOST + ":8080/unbankImage/"
					+ imageName.substring(12);
		}
		return imageName;
	}

	@Override
	public boolean verifyURL(String url) {
		if (StringUtil.isBlank(url)) {
			return false;
		}
		if (!(url.startsWith("http://") || url.startsWith("https://"))) {
			return false;
		}
		return true;
	}

	@Override
	public String encodeURL(String url) {
		if (url == null) {
			return null;
		}
		char[] tp = url.toCharArray();
		StringBuffer URLBuffer = new StringBuffer();
		for (char ch : tp) {
			if ((ch >= 0x4E00 && ch <= 0x9FA5)
					|| (ch == '{' || ch == '|' || ch == '}' || ch == ',')) {
				try {
					URLBuffer.append(URLEncoder.encode(ch + "", "gbk"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else {
				URLBuffer.append(ch);
			}
		}
		return URLBuffer.toString();
	}

	public String getDomain(String url) {
		String domain = "";
		try {
			URL u = new URL(url);
			domain = u.getHost();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return domain;
	}

}
