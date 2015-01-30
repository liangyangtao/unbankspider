package com.unbank.robotspider.action.filter;

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
public class CnCom10jqka extends BaseFilter {
	private String domain[] = new String[] { "ace.10jqka.com.cn",
			"activity.10jqka.com.cn", "ask.10jqka.com.cn",
			"basic.10jqka.com.cn", "blog.10jqka.com.cn", "bond.10jqka.com.cn",
			"data.10jqka.com.cn", "doctor.10jqka.com.cn",
			"download.10jqka.com.cn", "fe.10jqka.com.cn",
			"field.10jqka.com.cn", "focus.10jqka.com.cn", "fund.10jqka.com.cn",
			"futures.10jqka.com.cn", "goodsfu.10jqka.com.cn",
			"i.10jqka.com.cn", "invest.10jqka.com.cn", "job.10jqka.com.cn",
			"kaihu.10jqka.com.cn", "lcjz.10jqka.com.cn",
			"master.10jqka.com.cn", "mobile.10jqka.com.cn",
			"moni.10jqka.com.cn", "news.10jqka.com.cn", "pass.10jqka.com.cn",
			"pic.10jqka.com.cn", "q.10jqka.com.cn", "research.10jqka.com.cn",
			"school.10jqka.com.cn", "sd.10jqka.com.cn", "search.10jqka.com.cn",
			"smfactory.10jqka.com.cn", "sp.10jqka.com.cn",
			"stock.10jqka.com.cn", "stockpage.10jqka.com.cn",
			"t.10jqka.com.cn", "tools.fund.10jqka.com.cn",
			"trust.10jqka.com.cn", "vis.10jqka.com.cn", "vote.10jqka.com.cn",
			"www.10jqka.com.cn", "yuanchuang.10jqka.com.cn" };

	public CnCom10jqka() {

		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		return document.select("#J_article > div.art_cnt > div").first();
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
			if (imgSrc.equals("http://e.thsi.cn/img/853f8fcc7f06fdfb-1")
					|| imgSrc.equals("http://e.thsi.cn/img/c7686448176d9ee3-1")) {
				element.remove();
				continue;
			}
			if (imgSrc.startsWith("http://comment.10jqka.com.cn/sourcepic/")) {
				String imgUrl = new JsoupNetFetcher().fetchImage(imgSrc, null);
				logger.info(imgSrc + "           " + imgUrl);
				if (imgUrl != null && (!imgUrl.trim().isEmpty())) {
					element.attr("src", imgUrl);
					element.attr("class", "img_inline");
				} else {
					element.remove();
				}
				continue;
			}
			ImageFilter filter = new ImageUrlAndSizeFilter(null, 650, 110);
			String imgUrl = new JsoupNetFetcher().fetchImage(imgSrc, filter);

			logger.info(url + "          " + imgSrc + "           " + imgUrl);
			if (imgUrl != null && (!imgUrl.trim().isEmpty())) {
				element.attr("src", imgUrl);
				element.wrap("<p style='align:center;'></p>");
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
		String s = str;

		if (str.contains("【在线答疑】【汇通讲堂】")) {
			s = str.replace("【在线答疑】【汇通讲堂】", "");
		}
		if (str.contains("在线答疑汇通讲堂")) {
			s = str.replace("在线答疑汇通讲堂", "");
		}
		// <p><strong>制图：孙如心</strong></p>

		// <p>责任编辑：代敏</v></p>
		if (s.contains("制图")) {
			s = s.replaceAll("<p><strong>制图：.*</strong></p>", "");
		}
		if (s.contains("责任编辑")) {
			s = s.replaceAll("<p>责任编辑：.*</p>", "");
		}

		if (s.contains("(更多详细精彩内容")) {
			int index = str.indexOf("(更多详细精彩内容");
			if (index > 0) {
				s = s.substring(0, index);
			}
		}
		return s;
	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
