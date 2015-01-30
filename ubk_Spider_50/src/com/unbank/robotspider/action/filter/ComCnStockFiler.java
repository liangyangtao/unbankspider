package com.unbank.robotspider.action.filter;

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
public class ComCnStockFiler extends BaseFilter {
	private String domain[] = new String[] { "jrz.cnstock.com",
			"caifu.cnstock.com", "www.cnstock.com", "3g.cnstock.com",
			"news.cnstock.com", "company.cnstock.com", "ggjd.cnstock.com",
			"irm.cnstock.com", "roadshow.cnstock.com", "v.cnstock.com",
			"fund.cnstock.com", "stock.cnstock.com", "data.cnstock.com",
			"gjzx.cnstock.com", "blog.cnstock.com", "onlinebiz.cnstock.com",
			"paper.cnstock.com", "mall.cnstock.com", "xinpi.cnstock.com",
			"blogzt.cnstock.com", "wwkerhui.blog.cnstock.com", "t.cnstock.com",
			"yjbg.cnstock.com", "xxbiaoi.blog.cnstock.com",
			"guanqingyou.blog.cnstock.com", "shuipi.blog.cnstock.com",
			"yaoshujie.blog.cnstock.com", "zhuluguhai.blog.cnstock.com",
			"ymz123123.blog.cnstock.com", "huangxiangyuan.blog.cnstock.com",
			"bytz888.blog.cnstock.com", "yhjj.blog.cnstock.com",
			"zlgus.blog.cnstock.com", "luodai.blog.cnstock.com" };

	public ComCnStockFiler() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		String contentQuerys[] = new String[] { "#qmt_content_div", ".des",
				".content" };
		Element element = null;

		for (String string : contentQuerys) {
			element = document.select(string).first();
			if (element != null) {
				break;
			}
		}
		String cssQuery[] = new String[] { "#output_hangqing_div", "input",
				"a[href=http://news.cnstock.com/bwsd]",
				"a[href=javascript:goPage(1)", "#contentPager",
				"a[href=http://stock.cnstock.com/live]", "ul" };
		for (String string : cssQuery) {
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
			if (imgSrc.equals("http://static.cnstock.com/img/wealth/wx.gif")
					|| imgSrc
							.equals("http://static.cnstock.com/img/wealth/cfwz.png")) {
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
		String[] spechars = new String[] { "【相关阅读】", "(本报数据研究部" };
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
