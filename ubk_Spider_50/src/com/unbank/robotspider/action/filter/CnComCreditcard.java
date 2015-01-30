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
public class CnComCreditcard extends BaseFilter {
	private String domain = "www.creditcard.com.cn";

	public CnComCreditcard() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document
				.select(".content > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1)")
				.first();
		String cssQuerys[] = new String[] { "img[src=/uploads/allimg/141014/6471-141014091200205.jpg]" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}

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
			if (sWight == 144 && sHight == 20) {
				element.remove();
				continue;
			}
			ImageFilter filter = new ImageUrlAndSizeFilter(null, 0, 0);
			String imgUrl = new JsoupNetFetcher().fetchImage(imgSrc, filter);
			if (imgUrl != null && (!imgUrl.trim().isEmpty())) {
				element.attr("src", imgUrl);
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
		str = str
				.replace(
						"<p>信用卡之窗是国内建立最早的专业信用卡网站，与多家银行信用卡中心官方合作为您提供安全、便捷的信用卡申请服务。</p><p>网上办理信用卡请访问信用卡之窗在线申请栏目：http://www.creditcard.com.cn/apply/</p>",
						"");

		return str;
	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
