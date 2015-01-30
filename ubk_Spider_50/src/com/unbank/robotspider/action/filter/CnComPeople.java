package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class CnComPeople extends BaseFilter {

	String domains[] = new String[] { "passport.people.com.cn",
			"bbs1.people.com.cn", "qgblog.people.com.cn", "sns.people.com.cn",
			"ezheng.people.com.cn", "www.people.com.cn", "t.people.com.cn",
			"wza.people.com.cn", "paper.people.com.cn",
			"history.people.com.cn", "cpc.people.com.cn", "qzlx.people.com.cn",
			"tyzx.people.cn", "npc.people.com.cn", "cppcc.people.com.cn",
			"acftu.people.com.cn", "acwf.people.com.cn",
			"scitech.people.com.cn", "ip.people.com.cn",
			"rencai.people.com.cn", "bf.people.com.cn", "119.people.com.cn",
			"ccn.people.com.cn", "hgsj.people.com.cn",
			"finance1.people.com.cn", "news.people.com.cn",
			"politics.people.com.cn", "society.people.com.cn",
			"legal.people.com.cn", "tv.people.com.cn", "unn.people.com.cn",
			"leaders.people.com.cn", "travel.people.com.cn",
			"renwu.people.com.cn", "finance.people.com.cn",
			"energy.people.com.cn", "ccnews.people.com.cn",
			"env.people.com.cn", "gongyi.people.com.cn", "okooo.people.com.cn",
			"edu.people.com.cn", "art.people.com.cn", "pic.people.com.cn",
			"opinion.people.com.cn", "theory.people.com.cn",
			"media.people.com.cn", "yq.people.com.cn", "fangtan.people.com.cn",
			"world.people.com.cn", "tw.people.com.cn", "hm.people.com.cn",
			"military.people.com.cn", "auto.people.com.cn", "it.people.com.cn",
			"tc.people.com.cn", "homea.people.com.cn", "house.people.com.cn",
			"shipin.people.com.cn", "health.people.com.cn",
			"lady.people.com.cn", "culture.people.com.cn",
			"sports.people.com.cn", "ent.people.com.cn", "game.people.com.cn",
			"comic.people.com.cn", "liuyan.people.com.cn",
			"henan.people.com.cn", "ah.people.com.cn", "hi.people.com.cn",
			"sn.people.com.cn", "js.people.com.cn", "fujian.people.com.cn",
			"hb.people.com.cn", "jx.people.com.cn", "xj.people.com.cn",
			"gx.people.com.cn", "sc.people.com.cn", "hlj.people.com.cn",
			"cq.people.com.cn", "gz.people.com.cn", "sh.people.com.cn",
			"sd.people.com.cn", "zj.people.com.cn", "ln.people.com.cn",
			"hn.people.com.cn", "gd.people.com.cn", "sz.people.com.cn",
			"bj.people.com.cn", "he.people.com.cn", "sx.people.com.cn",
			"nm.people.com.cn", "jl.people.com.cn", "yn.people.com.cn",
			"xz.people.com.cn", "gs.people.com.cn", "qh.people.com.cn",
			"nx.people.com.cn", "wf.people.com.cn", "wz.people.com.cn",
			"su.people.com.cn", "pmm.people.com.cn", "ft.people.com.cn",
			"chinapic.people.com.cn", "blog.people.com.cn",
			"rexian.people.com.cn", "yuqing.people.com.cn",
			"vblog.people.com.cn", "51fayan.people.com.cn",
			"travel315.people.com.cn", "315.auto.people.com.cn",
			"book.people.com.cn", "fanfu.people.com.cn",
			"dangshi.people.com.cn", "dangjian.people.com.cn",
			"comments.people.com.cn", "renshi.people.com.cn",
			"ldzl.people.com.cn", "kfq.people.com.cn", "jiaju.people.com.cn",
			"expo.people.com.cn", "vip.people.com.cn",
			"picchina.people.com.cn", "golf.people.com.cn", "fj.people.com.cn",
			"data.sports.people.com.cn", "data.people.com.cn",
			"edu.people.gkcx.eol.cn", "liuxueku.people.com.cn",
			"product.people.com.cn", "data.fund.people.com.cn",
			"japan.people.com.cn", "usa.people.com.cn", "uk.people.com.cn",
			"ru.people.com.cn", "korea.people.com.cn",
			"australia.people.com.cn", "gonggao.people.com.cn",
			"kf.people.com.cn" ,"qd.people.com.cn"};

	public CnComPeople() {
		for (String s : domains) {
			FilterLocator.getInstance().register(s, this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("#p_content").first();
		String cssQuerys[] = new String[] { "#p_content > center > table",
				".otitle", ".zdfy" };
		for (String string : cssQuerys) {
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
		super.saveImage(maxTextElement, url);
	}

	@Override
	public String replaceStockCode(String content) {
		return super.replaceStockCode(content);
	}

	@Override
	public String replaceSpechars(String content) {
		String str = super.replaceSpechars(content);
		str = str.replace("【详细】", "");
		return str;
	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
