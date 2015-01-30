package com.unbank.robotspider.action.filter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;
import com.unbank.robotspider.fetch.ImageFilter;
import com.unbank.robotspider.fetch.ImageUrlAndSizeFilter;
import com.unbank.robotspider.fetch.JsoupNetFetcher;

@Component
public class CnXinmin extends BaseFilter {
	private String domain = "news.xinmin.cn";

	public CnXinmin() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("div#MP_article").first();
		removeNoNeedElement(element, "#content_wx_arrleft");
		removeNoNeedElement(element, "#content_wx_arrright");
		removeNoNeedElement(element, "#contentTuiguang_box");
		removeNoNeedElement(element, "style");
		removeNoNeedElement(element, "script");
		removeNoNeedTextElement(element, "原标题");
		return element;
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
			if (sWight == 600 && sHight == 155) {
				element.remove();
				continue;
			}
			if (sWight == 348 && sHight == 176) {
				element.remove();
				continue;
			}

			ImageFilter filter = new ImageUrlAndSizeFilter(null, 0, 0);
			String imgUrl = new JsoupNetFetcher().fetchImage(imgSrc, filter);
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
		str = str.replace("&gt;&gt;&gt;&gt;&gt;&gt;点击进入网谈实录", "");
		String[] spechars = new String[] { "(原标题", "原标题", "延伸阅读", "相关新闻", "来源" };
		for (String string : spechars) {
			int index = str.indexOf(string);
			if (index > 0) {
				str = str.substring(0, index);
			}
		}
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
