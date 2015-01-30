package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class SouhuContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(SouhuContentFilter.class);
	private String domain[] = new String[] { "pic.news.sohu.com",
			"photo.sohu.com", "club.learning.sohu.com", "pic.chihe.sohu.com",
			"stock.sohu.com", "pic.men.sohu.com", "club.stock.sohu.com",
			"club.qd.sohu.com", "mt.sohu.com", "baodian.women.sohu.com",
			"club.chihe.sohu.com", "health.sohu.com", "17173.tv.sohu.com",
			"www.sohu.com", "pinglun.sohu.com", "hr.sohu.com",
			"money.business.sohu.com", "media.sohu.com", "try.women.sohu.com",
			"my.tv.sohu.com", "zhujianzhong.blog.sohu.com", "book.sohu.com",
			"history.sohu.com", "pic.media.sohu.com", "music.sohu.com",
			"cul.sohu.com", "golf.sports.sohu.com", "wqbird.blog.sohu.com",
			"green.sohu.com", "heller10.blog.sohu.com", "club.cul.sohu.com",
			"guangzhou.auto.sohu.com", "www.m.sohu.com",
			"dise.health.sohu.com", "changchun.auto.sohu.com", "jiu.sohu.com",
			"news.sms.sohu.com", "lawyerkangzhenyu.blog.sohu.com",
			"nbtishen.blog.sohu.com", "mgame.sohu.com", "tdh318.blog.sohu.com",
			"yule.sohu.com", "ningbo.auto.sohu.com", "s.learning.sohu.com",
			"pic.astro.sohu.com", "pay.sohu.com", "astro.sohu.com",
			"product.it.sohu.com", "club.health.sohu.com", "it.sohu.com",
			"business.sohu.com", "ylxblog.blog.sohu.com",
			"club.business.sohu.com", "shiweipingblog.blog.sohu.com",
			"ting.sohu.com", "k.sohu.com", "zzzrbj.blog.sohu.com",
			"sitemap.sohu.com", "ad.sohu.com", "club.baobao.sohu.com",
			"club.news.sohu.com", "club.sz.sohu.com", "beauty.sohu.com",
			"sports.sohu.com", "arts.sohu.com", "cbachina.sports.sohu.com",
			"help.sohu.com", "digi.it.sohu.com", "travel.sohu.com",
			"money.sohu.com", "dealer.auto.sohu.com", "tianjin.auto.sohu.com",
			"women.sohu.com", "club.city.sohu.com", "app.sohu.com",
			"news.sohu.com", "train.travel.sohu.com",
			"zhangyanxiavip.blog.sohu.com", "music.yule.sohu.com",
			"corp.sohu.com", "yingyang2009.blog.sohu.com", "bschool.sohu.com",
			"roll.sohu.com", "club.travel.sohu.com", "wenzhou.auto.sohu.com",
			"hotel.sohu.com", "mini.sohu.com", "baobao.sohu.com",
			"tangshan.auto.sohu.com", "tv.sohu.com",
			"yixianrongblog.blog.sohu.com", "home.sohu.com",
			"changsha.auto.sohu.com", "star.news.sohu.com",
			"goto.sms.sohu.com", "men.sohu.com", "pic.history.sohu.com",
			"club.sohu.com", "club.women.sohu.com", "images.sohu.com",
			"mil.sohu.com", "jingqu.travel.sohu.com", "chihe.sohu.com",
			"q.stock.sohu.com", "db.auto.sohu.com", "kunming.auto.sohu.com",
			"yanghengjun65.blog.sohu.com", "city.sohu.com", "pp.sohu.com",
			"goabroad.sohu.com", "quan.sohu.com", "geek.it.sohu.com",
			"pic.yule.sohu.com", "astro.women.sohu.com", "guokr.blog.sohu.com",
			"qingdao.auto.sohu.com", "fund.sohu.com", "2sc.sohu.com",
			"caipiao.sohu.com", "wangyilog.blog.sohu.com", "pic.book.sohu.com",
			"mobile.auto.sohu.com", "s.sohu.com", "zhouhaibin.blog.sohu.com",
			"suzhou.auto.sohu.com", "gongyi.sohu.com", "pic.money.sohu.com",
			"dongguan.auto.sohu.com", "pic.women.sohu.com", "man.sohu.com",
			"city.club.sohu.com", "air.sohu.com", "club.dl.sohu.com",
			"luxury.sohu.com", "pic.sohu.com", "zhongyi.sohu.com",
			"pic.green.sohu.com", "club.book.sohu.com", "t.sohu.com",
			"club.money.sohu.com", "stock.business.sohu.com",
			"sohucallcenter.blog.sohu.com", "pic.sports.sohu.com",
			"shenzhen.auto.sohu.com", "qianjunblog.blog.sohu.com",
			"mail.sohu.com", "pic.fund.sohu.com", "so.tv.sohu.com",
			"jwcfvip.blog.sohu.com", "zibo.auto.sohu.com", "i.sohu.com",
			"auto.sohu.com", "jinkaiping.blog.sohu.com", "pic.luxury.sohu.com",
			"pic.fashion.sohu.com", "investors.sohu.com", "learning.sohu.com",
			"weather.news.sohu.com", "blog.sohu.com",
			"yaohongen.blog.sohu.com", "yantai.auto.sohu.com",
			"xian.auto.sohu.com", "fashion.sohu.com", "beijing.auto.sohu.com",
			"vip.book.sohu.com", "saa.auto.sohu.com", "yc.sohu.com",
			"drug.health.sohu.com", "xying1962.blog.sohu.com",
			"pic.learning.sohu.com" };

	public SouhuContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);

		}
	}

	public Element getContentNode(Document document) {
		String[] cssquerys = new String[] { "#contentText", "#main-content",
				"#content" };
		Element element = null;
		for (String string : cssquerys) {
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
		String cssQuerys[] = new String[] { ".muLink", ".divstockguba",
				".newBlog-bom", "#sz_qz", "div.clear", "ul", "h1", "#bdshare",
				"div.clearfix", "div.bdshare_t", "#changyan_area", "#SOHUCS",
				".sele-frag", ".pages", ".pagestip", ".editShare" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
