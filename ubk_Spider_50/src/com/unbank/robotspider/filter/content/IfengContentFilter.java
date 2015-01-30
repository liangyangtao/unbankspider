package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class IfengContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(IfengContentFilter.class);

	private String domain[] = new String[] { "talk.ifeng.com", "t.ifeng.com",
			"hb.ifeng.com", "phtv.ifeng.com", "nb.ifeng.com", "3g.ifeng.com",
			"ir.ifeng.com", "car.auto.ifeng.com", "try.cosmetics.ifeng.com",
			"games.ifeng.com", "my.ifeng.com", "news.house.ifeng.com",
			"js.ifeng.com", "adou1951.blog.ifeng.com", "career.ifeng.com",
			"help.ifeng.com", "app.ent.ifeng.com", "ln.ifeng.com",
			"diantai.ifeng.com", "bbs.ifeng.com", "fo.ifeng.com",
			"beijing.auto.ifeng.com", "gongyi.ifeng.com", "finance.ifeng.com",
			"tech.ifeng.com", "cp.ifeng.com", "dolphin.deliver.ifeng.com",
			"culture.ifeng.com", "gz.ifeng.com", "fashion.ifeng.com",
			"edu.ifeng.com", "sjz.house.ifeng.com", "api.3g.ifeng.com",
			"weifang.auto.ifeng.com", "bc.ifeng.com", "home.ifeng.com",
			"news.ifeng.com", "sn.ifeng.com", "opinion.ifeng.com",
			"blog.ifeng.com", "hz.house.ifeng.com", "xibei.ifeng.com",
			"sports.ifeng.com", "img.ifeng.com", "mil.ifeng.com",
			"apps.ifeng.com", "tieba.news.ifeng.com", "dol.deliver.ifeng.com",
			"id.ifeng.com", "auto.ifeng.com", "changchun.auto.ifeng.com",
			"hlj.ifeng.com", "book.ifeng.com", "cosmetics.ifeng.com",
			"art.ifeng.com", "zx.cp.ifeng.com", "hainan.ifeng.com",
			"miss.ifeng.com", "gz.house.ifeng.com", "ty.house.ifeng.com",
			"rbt.ifeng.com", "biz.ifeng.com", "digi.ifeng.com", "xm.ifeng.com",
			"app.travel.fashion.ifeng.com", "hn.ifeng.com", "18.ifeng.com",
			"10086.ifeng.com", "mikesakai.blog.ifeng.com", "play.ifeng.com",
			"house.ifeng.com", "v.ifeng.com", "gcj.ifeng.com",
			"sy.house.ifeng.com", "city.ifeng.com", "vip.itv.ifeng.com",
			"chaogu.ifeng.com", "sd.ifeng.com", "cq.ifeng.com", "qd.ifeng.com",
			"fj.ifeng.com", "chengfeng513.blog.ifeng.com", "astro.ifeng.com",
			"ent.ifeng.com", "zyy.ifeng.com", "v.book.ifeng.com",
			"abroad.edu.ifeng.com", "suzhou.auto.ifeng.com", "hunan.ifeng.com",
			"hebei.ifeng.com", "bbs.house.ifeng.com", "appi.ifeng.com",
			"bbs.auto.ifeng.com", "vip.v.ifeng.com", "tv.v.ifeng.com",
			"data.auto.ifeng.com", "tjzj.finance.ifeng.com", "yue.ifeng.com",
			"www.ifeng.com", "i.ifeng.com" };

	public IfengContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		String cssQuerys[] = new String[] { "#i-article1", "#main_content",
				"#slidedesc2", "#blog_article_content", ".arl-c-txt", ".sent01" };
		Element element = null;
		for (String string : cssQuerys) {
			element = assignPossibleElement(document, string);
			if (element != null) {
				break;
			}
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
		String cssQuerys[] = new String[] { "#embed_hzh_div", ".ifengLogo",
				".txtLink", "#inner_hq", "#js_clear",
				"#BAIDU_DUP_wrapper_u1059855_0" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
