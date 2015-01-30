package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class DzwwwContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(DzwwwContentFilter.class);

	private String[] domain = new String[] { "www.dzwww.com", "zzlr.dzwww.com",
			"shexun.dzwww.com", "lady.dzwww.com", "kr.dzwww.com",
			"yantai.dzwww.com", "jp.dzwww.com", "weifang.dzwww.com",
			"vote.dzwww.com", "sdxjw.dzwww.com", "dzxf.dzwww.com",
			"dzrb.dzwww.com", "mail.dzwww.com", "dzinfo.dzwww.com",
			"sd.dzwww.com", "yuqing.dzwww.com", "sports.dzwww.com",
			"liaocheng.dzwww.com", "sdqy.dzwww.com", "jiu.dzwww.com",
			"survey.dzwww.com", "health.dzwww.com", "ent.dzwww.com",
			"zz.dzwww.com", "dyql.dzwww.com", "weihai.dzwww.com",
			"ncdz.dzwww.com", "book.dzwww.com", "linyi.dzwww.com",
			"xwshw.dzwww.com", "hotel.dzwww.com", "wenshi.dzwww.com",
			"auto.dzwww.com", "jingxuan.dzwww.com", "3g.dzwww.com",
			"ggg.dzwww.com", "reg.dzwww.com", "tp.dzwww.com",
			"lianzheng.dzwww.com", "edu.dzwww.com", "sdgy.dzwww.com",
			"club.dzwww.com", "qnjz.dzwww.com", "taian.dzwww.com",
			"peixun.dzwww.com", "dingbao.dzwww.com", "tuan.dzwww.com",
			"qlms.dzwww.com", "jingjie.dzwww.com", "binzhou.dzwww.com",
			"bbs.dzwww.com", "blog.dzwww.com", "elec.dzwww.com",
			"heze.dzwww.com", "home.dzwww.com", "sdqnb.dzwww.com",
			"laiwu.dzwww.com", "shuhua.dzwww.com", "sd24.dzwww.com",
			"dv.dzwww.com", "jnbbs.dzwww.com", "shrb.dzwww.com",
			"house.dzwww.com", "food.dzwww.com", "zibo.dzwww.com",
			"zaozhuang.dzwww.com", "finance.dzwww.com", "rizhao.dzwww.com",
			"qingdao.dzwww.com", "jinan.dzwww.com", "mdsd.dzwww.com",
			"dongying.dzwww.com", "jining.dzwww.com", "news.dzwww.com",
			"dezhou.dzwww.com", "fazhi.dzwww.com", "tour.dzwww.com" };

	public DzwwwContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);

		}

	}

	public Element getContentNode(Document document) {
		String[] cssQuerys = new String[] { ".TRS_Editor", "#news-con" };
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
		String cssQuerys[] = new String[] { "div.blank10", "#asp_3352",
				"#qrcode" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
