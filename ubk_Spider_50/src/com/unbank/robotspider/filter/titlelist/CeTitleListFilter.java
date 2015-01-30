package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CeTitleListFilter extends BaseTitleListFilter {
	private String domain[] = new String[] { "ent.ce.cn", "foodsafety.ce.cn",
			"edu.ce.cn", "12365.ce.cn", "yq.ce.cn", "europe.ce.cn",
			"wap.ce.cn", "hj.ce.cn", "fashion.ce.cn", "bt.ce.cn", "cz.ce.cn",
			"js.ce.cn", "sn.ce.cn", "blog.ce.cn", "ah.ce.cn", "health.ce.cn",
			"district.ce.cn", "ar.ce.cn", "cv.ce.cn", "finance.ce.cn",
			"cq.ce.cn", "sme.ce.cn", "sd.ce.cn", "gx.ce.cn", "book.ce.cn",
			"ja.ce.cn", "luxury.ce.cn", "zs.ce.cn", "kfq.ce.cn", "qh.ce.cn",
			"hlj.ce.cn", "kr.ce.cn", "art.ce.cn", "de.ce.cn", "hi.ce.cn",
			"xz.ce.cn", "baby.ce.cn", "xj.ce.cn", "en.ce.cn", "fj.ce.cn",
			"cen.ce.cn", "expo.ce.cn", "views.ce.cn", "fr.ce.cn", 
			"life.ce.cn", "hsj.ce.cn", "zhujian.ce.cn", "hb.ce.cn",
			"net.ce.cn", "intl.ce.cn", "es.ce.cn", "travel.ce.cn", "sc.ce.cn",
			"sh.ce.cn", "gongyi.ce.cn", "shuhua.ce.cn", "auto.ce.cn",
			"gd.ce.cn", "tj.ce.cn", "fz.ce.cn", "paper.ce.cn", "fangtan.ce.cn",
			"city.ce.cn", "sz.ce.cn", "he.ce.cn", "big5.ce.cn", "bbs.ce.cn",
			"sx.ce.cn", "hn.ce.cn", "ru.ce.cn", "data.ce.cn", "jx.ce.cn",
			"ha.ce.cn" };

	public CeTitleListFilter() {
		for (int i = 0; i < domain.length; i++) {
			TitleListFilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Elements getPossibleListElement(Document document) {

		String cssQuerys[] = new String[] { "div.middle_block2", ".list_left",
				".left","sec_left" ,".neirong"};
		Elements elements = null;
		for (String string : cssQuerys) {
			elements = getPossibleListElement(document, string);
			if (elements != null) {
				break;
			}
		}
		return elements;
	}
}
