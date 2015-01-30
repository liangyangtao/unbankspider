package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class PeopleTitleListFilter extends BaseTitleListFilter {
	private String domain[] = new String[] { "hb.people.com.cn",
			"ah.people.com.cn", "okooo.people.com.cn", "book.people.com.cn",
			"australia.people.com.cn", "opinion.people.com.cn",
			"sh.people.com.cn", "blog.people.com.cn", "tv.people.com.cn",
			"ru.people.com.cn", "shipin.people.com.cn", "ft.people.com.cn",
			"jx.people.com.cn", "renshi.people.com.cn", "japan.people.com.cn",
			"world.people.com.cn", "qzlx.people.com.cn", "ln.people.com.cn",
			"yuqing.people.com.cn", "legal.people.com.cn",
			"comic.people.com.cn", "gs.people.com.cn", "renwu.people.com.cn",
			"energy.people.com.cn", "ip.people.com.cn", "nx.people.com.cn",
			"kf.people.com.cn", "qh.people.com.cn", "www.people.com.cn",
			"nm.people.com.cn", "kfq.people.com.cn", "gongyi.people.com.cn",
			"auto.people.com.cn", "xj.people.com.cn", "product.people.com.cn",
			"cpc.people.com.cn", "t.people.com.cn", "js.people.com.cn",
			"gonggao.people.com.cn", "cq.people.com.cn",
			"data.sports.people.com.cn", "he.people.com.cn",
			"travel315.people.com.cn", "scitech.people.com.cn",
			"ent.people.com.cn", "history.people.com.cn",
			"fujian.people.com.cn", "sn.people.com.cn", "gx.people.com.cn",
			"data.people.com.cn", "ezheng.people.com.cn", "ccn.people.com.cn",
			"homea.people.com.cn", "health.people.com.cn", "edu.people.com.cn",
			"hm.people.com.cn", "119.people.com.cn", "korea.people.com.cn",
			"sx.people.com.cn", "acwf.people.com.cn", "fj.people.com.cn",
			"travel.people.com.cn", "wz.people.com.cn", "jl.people.com.cn",
			"ldzl.people.com.cn", "ccnews.people.com.cn",
			"culture.people.com.cn", "hi.people.com.cn",
			"315.auto.people.com.cn", "golf.people.com.cn",
			"military.people.com.cn", "lady.people.com.cn",
			"news.people.com.cn", "wza.people.com.cn", "gd.people.com.cn",
			"xz.people.com.cn", "uk.people.com.cn", "edu.people.gkcx.eol.cn",
			"it.people.com.cn", "fanfu.people.com.cn", "yq.people.com.cn",
			"society.people.com.cn", "gz.people.com.cn", "su.people.com.cn",
			"zb.people.com.cn", "data.fund.people.com.cn",
			"chinapic.people.com.cn", "usa.people.com.cn",
			"media.people.com.cn", "hgsj.people.com.cn", "tyzx.people.cn",
			"pic.people.com.cn", "tw.people.com.cn", "picchina.people.com.cn",
			"game.people.com.cn", "chinese.people.com.cn", "npc.people.com.cn",
			"leaders.people.com.cn", "rexian.people.com.cn",
			"finance1.people.com.cn", "qgblog.people.com.cn",
			"vip.people.com.cn", "jiaju.people.com.cn",
			"liuxueku.people.com.cn", "okooo.people.cn",
			"dangshi.people.com.cn", "tc.people.com.cn", "sc.people.com.cn",
			"bf.people.com.cn", "zj.people.com.cn", "yn.people.com.cn",
			"passport.people.com.cn", "acftu.people.com.cn",
			"sns.people.com.cn", "hn.people.com.cn", "unn.people.com.cn",
			"sports.people.com.cn", "art.people.com.cn", "sd.people.com.cn",
			"theory.people.com.cn", "wf.people.com.cn", "house.people.com.cn",
			"hlj.people.com.cn", "vblog.people.com.cn",
			"finance.people.com.cn", "51fayan.people.com.cn",
			"env.people.com.cn", "liuyan.people.com.cn", "sz.people.com.cn",
			"paper.people.com.cn", "bj.people.com.cn", "cppcc.people.com.cn",
			"bbs1.people.com.cn", "expo.people.com.cn", "henan.people.com.cn",
			"rencai.people.com.cn", "fangtan.people.com.cn","qd.people.com.cn" };

	public PeopleTitleListFilter() {
		for (int i = 0; i < domain.length; i++) {
			TitleListFilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Elements getPossibleListElement(Document document) {
		Element bodyElement = document.body();
		if (bodyElement == null) {
			return null;
		}
		Elements possibleListElements = bodyElement.select("a");
		return possibleListElements;
	}

}
