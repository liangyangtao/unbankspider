package com.unbank.robotspider.action.filter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;
import com.unbank.robotspider.fetch.ImageFilter;
import com.unbank.robotspider.fetch.ImageUrlAndSizeFilter;
import com.unbank.robotspider.fetch.JsoupNetFetcher;

@Service
public class CnComCpnn extends BaseFilter {
	private String domain = "www.cpnn.com.cn";

	public CnComCpnn() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		return document.select("div#Zoom").first();
	}

	@Override
	public void formatElements(Element maxTextElement) {
		super.formatElements(maxTextElement);
	}

	@Override
	public void saveImage(Element maxTextElement, String url) {

		Elements elements = maxTextElement.select("img");
		for (Element element : elements) {
			String imgSrc = element.absUrl("src");
			if (imgSrc == null || imgSrc.trim().isEmpty()) {
				continue;
			}
			if (imgSrc
					.equals("http://www.cpnn.com.cn/dw/201406/W020140624581696860811.jpg")
					|| "http://www.cpnn.com.cn/zdyw/201410/W020140910537829775879.jpg"
							.equals(imgSrc)) {// http://www.cpnn.com.cn/zdyw/201410/W020140910537829775879.jpg
				element.remove();
				continue;
			}
			BufferedImage img = null;
			try {
				if (imgSrc.startsWith("http:")) {
					img = ImageIO.read(new URL(imgSrc));
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int sWight = img.getWidth();
			int sHight = img.getHeight();

			if (sWight == 348 && sHight == 176) {
				element.remove();
				continue;
			}
			ImageFilter filter = new ImageUrlAndSizeFilter(null, 0, 0);
			String imgUrl = new JsoupNetFetcher().fetchImage(imgSrc, filter);

			logger.info(url + "          " + imgSrc + "           " + imgUrl);
			if (imgUrl != null && (!imgUrl.trim().isEmpty())) {
				element.attr("src", imgUrl);
				element.wrap("<p style='text-align:center;'></p>");
			} else {
				element.remove();
			}
		}
	}

	@Override
	public String replaceStockCode(String content) {
		return super.replaceStockCode(content);
	}

	@Override
	public String replaceSpechars(String content) {
		String str = super.replaceSpechars(content);
		int index = str.indexOf("责任编辑");
		if (index > 0) {
			return str.substring(0, index);
		} else
			return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
