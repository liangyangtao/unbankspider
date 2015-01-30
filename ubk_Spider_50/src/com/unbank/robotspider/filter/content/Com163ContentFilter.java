package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class Com163ContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(Com163ContentFilter.class);

	private String domain[] = new String[] { "qiumingjiejie.blog.163.com",
			"tianyu.163.com", "yuehui.163.com", "bbs.163.com", "qiye.163.com",
			"sitemap.163.com", "d.163.com", "y3.163.com", "view.163.com",
			"bbs.bj.house.163.com", "data.book.163.com", "cc.163.com",
			"lvxuanming.blog.163.com", "kids.163.com", "ka.game.163.com",
			"m.163.com", "guizhou.travel.163.com", "fashion.163.com",
			"xyq.163.com", "bbs.travel.163.com", "game.163.com", "mm.163.com",
			"bj.house.163.com", "discovery.163.com", "money.163.com",
			"tie.163.com", "gg.163.com", "media.163.com", "dt2.163.com",
			"cba.sports.163.com", "vhouse.163.com", "club.tech.163.com",
			"mxlimy.blog.163.com", "changchunguomao.blog.163.com",
			"david.tse.198.blog.163.com", "bobo.163.com", "caipiao.163.com",
			"vipmail.163.com", "post.news.163.com", "help.163.com",
			"e.house.163.com", "s.163.com", "open.163.com",
			"zhengyuchuan.vip.blog.163.com", "yezidexiaochu.blog.163.com",
			"ff.163.com", "w.163.com", "zs.163.com",
			"huaqiaoliehurenjia.blog.163.com", "qkxlj.blog.163.com",
			"j.news.163.com", "bbs.news.163.com", "baoxian.163.com",
			"imjiuge.blog.163.com", "news.163.com",
			"hunghuangilook.blog.163.com", "chaogu.money.163.com",
			"muffin66.blog.163.com", "love.163.com", "pub.dy.163.com",
			"data.ent.163.com", "comment.163.com",
			"shiliupopojiadao.blog.163.com", "3g.163.com",
			"yztvip.blog.163.com", "uu.163.com", "house.163.com",
			"yaoshujiewy.blog.163.com", "xf.house.163.com",
			"shouyou.game.163.com", "quotes.money.163.com",
			"annyflower.blog.163.com", "bbs.baby.163.com", "music.163.com",
			"jiuwoguoguo.blog.163.com", "maggyhsu.blog.163.com",
			"study.163.com", "baby.163.com", "h.163.com",
			"hu.shifu.blog.163.com", "gongyi.163.com",
			"songqingjies.blog.163.com", "jackzhudaming.blog.163.com",
			"hea.163.com", "huyuyublog.blog.163.com", "blog.163.com",
			"war.163.com", "t.163.com", "gz.house.163.com", "travel.163.com",
			"tx3.163.com", "fssggg.blog.163.com", "zhaochuboke.blog.163.com",
			"lady.163.com", "dingdong805313676.blog.163.com", "tech.163.com",
			"v.money.163.com", "focus.news.163.com", "tianpujun.blog.163.com",
			"pay.163.com", "sports.163.com", "pk.163.com",
			"product.auto.163.com", "digibbs.tech.163.com", "digi.163.com",
			"ccmissingu.blog.163.com", "g.163.com", "emarketing.biz.163.com",
			"reg.163.com", "zx.caipiao.163.com", "email.163.com",
			"wuxiaocaicaicai.blog.163.com", "home.163.com", "yc.163.com",
			"popme.163.com", "goal.sports.163.com", "yuetu.163.com",
			"fax.163.com", "bbs.lady.163.com", "sh.house.163.com",
			"xwzm.blog.163.com", "qn.163.com", "go.travel.163.com",
			"reg.vip.163.com", "trip.163.com", "survey2.163.com",
			"chinaweekly.blog.163.com", "pp.163.com", "biz.163.com",
			"club2011.auto.163.com", "cs.sports.163.com", "yuedu.163.com",
			"www.163.com", "corp.163.com", "men.163.com", "edu.163.com",
			"wsx003.blog.163.com", "xym.163.com", "data.163.com",
			"v.mobile.163.com", "bbs.gz.house.163.com", "mail.163.com",
			"fm.163.com", "reg.email.163.com", "a.163.com",
			"product.mobile.163.com", "daxue.163.com",
			"modishvane.blog.163.com", "jiu.163.com",
			"maoyushi88.blog.163.com", "bbs.home.163.com", "nie.163.com",
			"showing0808.blog.163.com", "9.163.com", "ecard.163.com",
			"nba.sports.163.com", "yuanst.blog.163.com", "mobile.163.com",
			"huazongdiu.blog.163.com", "waimeijingxuan.blog.163.com",
			"jingdian.travel.163.com", "x3.163.com",
			"changsongliu2012.blog.163.com", "dy.163.com", "61.163.com",
			"club.auto.163.com", "zhenhua.163.com", "huihudichan.blog.163.com",
			"auto.163.com", "pic.auto.163.com", "book.163.com",
			"analytics.163.com", "xy2.163.com", "cimg.163.com",
			"i.money.163.com", "tag.163.com", "piao.163.com", "focus.163.com",
			"v.163.com", "istyle.163.com", "fushi.163.com", "ent.163.com",
			"xlynn.card.blog.163.com", "gb.corp.163.com",
			"caozuolan2013.blog.163.com", "yiduiread.blog.163.com",
			"cosmetic.lady.163.com", "mall.163.com", "8.163.com", "wh.163.com",
			"photo.163.com", "hellohardcandy.blog.163.com", "yxp.163.com" };

	public Com163ContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}
	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "div#endText");
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
		String cssQuerys[] = new String[] { "div.ep-source", "div.gg200x300",
				"#pages" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
