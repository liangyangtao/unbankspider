package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.fetch.ImageFilter;
import com.unbank.robotspider.fetch.ImageUrlAndSizeFilter;
import com.unbank.robotspider.fetch.JsoupNetFetcher;

@Service
public class OrangebankContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(OrangebankContentFilter.class);

	private String domain = "www.orangebank.com.cn";

	public OrangebankContentFilter() {
		ContentFilterLocator.getInstance().register(domain, this);
	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#contentDiv");
		if (element == null) {
			return null;
		}
		return element;
	}

	public void removeNoNeedElementsByText(Element contentElement) {
		// String textQuerys[] = new String[] { "" };
		// for (String textQuery : textQuerys) {
		// removeNoNeedTextElement(contentElement, textQuery);
		// }
	}

	public void removeNoNeedElementsByCssQuery(Element contentElement) {
		String cssQuerys[] = new String[] { "#hzh", "#nextpage", "#pages" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

	public boolean saveImage(Element contentElement) {
		Elements elements = contentElement.select("img");
		for (Element element : elements) {
			String temp[] = element.absUrl("src").split("/cpp");
			String imgSrc = "http://www.orangebank.com.cn" + temp[1];
			if (imgSrc == null || imgSrc.trim().isEmpty()) {
				continue;
			}

			ImageFilter filter = new ImageUrlAndSizeFilter(null, 0, 0);
			String imgUrl = new JsoupNetFetcher().fetchImage(imgSrc, filter);

			if (imgUrl != null && (!imgUrl.trim().isEmpty())) {
				element.attr("src", imgUrl);
			     continue;
			} else {
				element.remove();
			    return false;
			}
		}
		return false;
	}

}
