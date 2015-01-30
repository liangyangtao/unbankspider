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
public class ComIfeng extends BaseFilter {
	private String domain[] = new String[] { "news.ifeng.com", "www.ifeng.com",
			"i.ifeng.com", "3g.ifeng.com", "v.ifeng.com", "my.ifeng.com",
			"opinion.ifeng.com", "vip.v.ifeng.com", "finance.ifeng.com",
			"18.ifeng.com", "ent.ifeng.com", "tech.ifeng.com",
			"digi.ifeng.com", "sports.ifeng.com", "auto.ifeng.com",
			"data.auto.ifeng.com", "travel.ifeng.com", "fashion.ifeng.com",
			"culture.ifeng.com", "book.ifeng.com", "edu.ifeng.com",
			"blog.ifeng.com", "bbs.ifeng.com", "gongyi.ifeng.com",
			"fo.ifeng.com", "diantai.ifeng.com", "house.ifeng.com",
			"home.ifeng.com", "city.ifeng.com", "games.ifeng.com",
			"yue.ifeng.com", "astro.ifeng.com", "talk.ifeng.com",
			"bc.ifeng.com", "zx.cp.ifeng.com", "phtv.ifeng.com",
			"dol.deliver.ifeng.com", "abroad.edu.ifeng.com", "hebei.ifeng.com",
			"js.ifeng.com", "sd.ifeng.com", "hunan.ifeng.com", "hb.ifeng.com",
			"cq.ifeng.com", "ln.ifeng.com", "qd.ifeng.com",
			"dolphin.deliver.ifeng.com", "cp.ifeng.com", "tv.v.ifeng.com",
			"ds.ifeng.com", "tjzj.finance.ifeng.com", "gcj.ifeng.com",
			"zyy.ifeng.com", "car.auto.ifeng.com", "beijing.auto.ifeng.com",
			"wuhan.auto.ifeng.com", "tangshan.auto.ifeng.com",
			"bbs.auto.ifeng.com", "weifang.auto.ifeng.com",
			"suzhou.auto.ifeng.com", "id.ifeng.com", "miss.ifeng.com",
			"news.house.ifeng.com", "bbs.house.ifeng.com",
			"gz.house.ifeng.com", "sy.house.ifeng.com", "nj.house.ifeng.com",
			"sjz.house.ifeng.com", "ty.house.ifeng.com", "xa.house.ifeng.com",
			"hz.house.ifeng.com", "cosmetics.ifeng.com",
			"try.cosmetics.ifeng.com", "rbt.ifeng.com", "t.ifeng.com",
			"tieba.news.ifeng.com", "mil.ifeng.com", "play.ifeng.com",
			"app.edu.ifeng.com", "app.ent.ifeng.com", "v.book.ifeng.com",
			"vip.itv.ifeng.com", "apps.ifeng.com", "api.3g.ifeng.com",
			"appi.ifeng.com", "art.ifeng.com", "biz.ifeng.com", "hn.ifeng.com",
			"hainan.ifeng.com", "jx.ifeng.com", "sn.ifeng.com",
			"xibei.ifeng.com", "gz.ifeng.com", "fj.ifeng.com", "xm.ifeng.com",
			"nb.ifeng.com", "hlj.ifeng.com", "ir.ifeng.com",
			"career.ifeng.com", "help.ifeng.com", "img.ifeng.com" };

	public ComIfeng() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document.select("div#main_content").first();
		if (element == null) {
			element = document.select(".article").first();
		}

		String cssQuerys[] = new String[] {
				".ifengLogo",
				"img[src=http://y0.ifengimg.com/haina/2014_32/bf9b30ba5e106b5.jpg]",
				"#embed_hzh_div" };

		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}

		Elements elements = element.getElementsByTag("img");
		if (elements.size() > 0) {
			for (int i = 0; i < elements.size(); i++) {
				Element e = elements.get(i);
				if ("150".equals(e.attr("width"))
						&& "150".equals(e.attr("height"))) {
					e.remove();
				}
			}
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
			if (imgSrc.equals("http://img.ifeng.com/page/Logo.gif")) {
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

		// super.saveImage(maxTextElement, url);
	}

	@Override
	public String replaceStockCode(String content) {
		String source = super.replaceStockCode(content);
		// 海嘉定悦合国际广场[最新消息 价格 户型 点评]项目资金链
		// 金额的46%，其中海外[简介 最新动态]融资436.1
		source = source.replace("</v>[最新消息</v>价格</v>户型</v>点评</v>]", "");
		source = source.replace("[简介</v>最新动态</v>]", "");
		source = source.replace("返回21世纪网首页&gt;&gt;", "");
		return source;
	}

	@Override
	public String replaceSpechars(String content) {
		String str = super.replaceSpechars(content);

		String[] subStr = { "<p>更多</p>", "相关报道见：" };
		for (String s : subStr) {
			if (str.contains(s)) {
				str = str.substring(0, str.indexOf(s));
			}
		}
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
