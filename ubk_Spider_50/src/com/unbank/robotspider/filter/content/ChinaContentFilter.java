package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class ChinaContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(ChinaContentFilter.class);

	private String domain[] = new String[] { "zy.china.com.cn",
			"jjzg.china.com.cn", "photo.china.com.cn", "gswsjzxx.china.com.cn",
			"ml.china.com.cn", "invest.china.com.cn", "korean.china.org.cn",
			"yanglao.china.com.cn", "esperanto.china.org.cn", "art.china.cn",
			"house.china.com.cn", "www.china.org.cn", "hao.china.com.cn",
			"ccwm.china.com.cn", "sports.china.com.cn", "union.china.com.cn",
			"local.china.com.cn", "travel.china.com.cn",
			"science.china.com.cn", "hotel.china.com.cn", "edu.china.com.cn",
			"lohas.china.com.cn", "119.china.com.cn", "henan.china.com.cn",
			"health.china.com.cn", "caifang.china.com.cn", "auto.china.com.cn",
			"blog.china.com.cn", "jx.china.com.cn", "net.china.com.cn",
			"ocean.china.com.cn", "forum.china.com.cn", "yuqing.china.com.cn",
			"ipo.china.com.cn", "minqi.china.com.cn", "xiaofei.china.com.cn",
			"french.china.org.cn", "opinion.china.com.cn", "fj.china.com.cn",
			"ezt.china.com.cn", "mz.china.com.cn", "city.china.com.cn",
			"shaoeryingyu.edu.china.com.cn", "jjsx.china.com.cn",
			"js.china.com.cn", "media.china.com.cn", "gongyi.china.com.cn",
			"wza.china.com.cn", "bh.china.com.cn", "mail.china.com.cn",
			"mnc.china.com.cn", "sd.china.com.cn", "railway.china.com.cn",
			"russian.china.org.cn", "pingyao.china.com.cn",
			"culture.china.com.cn", "photostock.china.com.cn",
			"zj.china.com.cn", "jtjy.china.com.cn", "my.finance.china.com.cn",
			"beijing.china.org.cn", "dianda.china.com.cn", "y.china.com.cn",
			"news.china.com.cn", "mlzg.china.com.cn", "jiangsu.china.com.cn",
			"spanish.china.org.cn", "hebei.china.com.cn", "ent.china.com.cn",
			"japanese.china.org.cn", "invest.china.cn", "people.china.com.cn",
			"yy.china.com.cn", "cppcc.china.com.cn", "archive.china.com.cn",
			"mv.china.com.cn", "blue.china.com.cn", "german.china.org.cn",
			"legal.china.com.cn", "cn.china.cn", "life.china.com.cn",
			"food.china.com.cn", "tech.china.com.cn", "hj.china.com.cn",
			"pinpai.china.com.cn", "weather.china.com.cn",
			"finance.china.com.cn", "business.china.com.cn",
			"u.edu.china.com.cn", "www.china.com.cn", "baby.china.com.cn",
			"sc.china.com.cn", "home.china.com.cn", "military.china.com.cn",
			"huludao.swimsuit.china.com.cn", "kfq.china.com.cn",
			"v.china.com.cn", "arabic.china.org.cn", "fangtan.china.com.cn",
			"cul.china.com.cn", "xinjiang.china.com.cn",
			"app.finance.china.com.cn", "guoqing.china.com.cn",
			"games.china.com.cn", "f.home.china.com.cn", "zgsc.china.com.cn",
			"tea.china.com.cn", "download.china.cn", "fashion.china.com.cn" };

	public ChinaContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);

		}

	}

	public Element getContentNode(Document document) {
		String[] cssQuerys = new String[] { "#main_content", "#content",
				"#blog_article", "#artbody", "#fontzoom", ".article-content" };
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
		String cssQuerys[] = new String[] { "iframe", "ins" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
