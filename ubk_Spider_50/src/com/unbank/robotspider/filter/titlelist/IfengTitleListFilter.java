package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class IfengTitleListFilter extends BaseTitleListFilter {
	private String domains[] = new String[] { "news.ifeng.com",
			"www.ifeng.com", "i.ifeng.com", "3g.ifeng.com", "v.ifeng.com",
			"my.ifeng.com", "opinion.ifeng.com", "vip.v.ifeng.com",
			"finance.ifeng.com", "18.ifeng.com", "ent.ifeng.com",
			"tech.ifeng.com", "digi.ifeng.com", "sports.ifeng.com",
			"auto.ifeng.com", "data.auto.ifeng.com", "travel.ifeng.com",
			"fashion.ifeng.com", "culture.ifeng.com", "book.ifeng.com",
			"edu.ifeng.com", "blog.ifeng.com", "bbs.ifeng.com",
			"gongyi.ifeng.com", "fo.ifeng.com", "diantai.ifeng.com",
			"house.ifeng.com", "home.ifeng.com", "city.ifeng.com",
			"games.ifeng.com", "yue.ifeng.com", "astro.ifeng.com",
			"talk.ifeng.com", "bc.ifeng.com", "zx.cp.ifeng.com",
			"phtv.ifeng.com", "dol.deliver.ifeng.com", "abroad.edu.ifeng.com",
			"hebei.ifeng.com", "js.ifeng.com", "sd.ifeng.com",
			"hunan.ifeng.com", "hb.ifeng.com", "cq.ifeng.com", "ln.ifeng.com",
			"qd.ifeng.com", "dolphin.deliver.ifeng.com", "cp.ifeng.com",
			"tv.v.ifeng.com", "ds.ifeng.com", "tjzj.finance.ifeng.com",
			"gcj.ifeng.com", "zyy.ifeng.com", "car.auto.ifeng.com",
			"beijing.auto.ifeng.com", "wuhan.auto.ifeng.com",
			"tangshan.auto.ifeng.com", "bbs.auto.ifeng.com",
			"weifang.auto.ifeng.com", "suzhou.auto.ifeng.com", "id.ifeng.com",
			"miss.ifeng.com", "news.house.ifeng.com", "bbs.house.ifeng.com",
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

	public IfengTitleListFilter() {
		for (String domain : domains) {
			TitleListFilterLocator.getInstance().register(domain, this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		Elements elements = getPossibleListElement(document, ".news-list");

		if (elements == null) {
			elements = getPossibleListElement(document, ".main");
		}
		return elements;
	}

}
