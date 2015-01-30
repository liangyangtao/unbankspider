package com.unbank.robotspider.filter.nextPage;

import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CeNextPageFilter extends NextPageBaseFilter {

	private String domain[] = new String[] { "ent.ce.cn", "foodsafety.ce.cn",
			"edu.ce.cn", "12365.ce.cn", "yq.ce.cn", "europe.ce.cn",
			"wap.ce.cn", "hj.ce.cn", "fashion.ce.cn", "bt.ce.cn", "cz.ce.cn",
			"js.ce.cn", "sn.ce.cn", "blog.ce.cn", "ah.ce.cn", "health.ce.cn",
			"district.ce.cn", "ar.ce.cn", "cv.ce.cn", "finance.ce.cn",
			"cq.ce.cn", "sme.ce.cn", "sd.ce.cn", "gx.ce.cn", "book.ce.cn",
			"ja.ce.cn", "luxury.ce.cn", "zs.ce.cn", "kfq.ce.cn", "qh.ce.cn",
			"hlj.ce.cn", "kr.ce.cn", "art.ce.cn", "de.ce.cn", "hi.ce.cn",
			"xz.ce.cn", "baby.ce.cn", "xj.ce.cn", "en.ce.cn", "fj.ce.cn",
			"cen.ce.cn", "expo.ce.cn", "views.ce.cn", "fr.ce.cn", "www.ce.cn",
			"life.ce.cn", "hsj.ce.cn", "zhujian.ce.cn", "hb.ce.cn",
			"net.ce.cn", "intl.ce.cn", "es.ce.cn", "travel.ce.cn", "sc.ce.cn",
			"sh.ce.cn", "gongyi.ce.cn", "shuhua.ce.cn", "auto.ce.cn",
			"gd.ce.cn", "tj.ce.cn", "fz.ce.cn", "nongye.ce.cn", "paper.ce.cn",
			"fangtan.ce.cn", "city.ce.cn", "sz.ce.cn", "he.ce.cn",
			"big5.ce.cn", "bbs.ce.cn", "sx.ce.cn", "hn.ce.cn", "ru.ce.cn",
			"data.ce.cn", "jx.ce.cn", "ha.ce.cn" };

	public CeNextPageFilter() {
		for (int i = 0; i < domain.length; i++) {
			NextPageFilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Integer extractMaxPageNum(Document document) {
		Elements scriptElements = document.body().select("script");
		String countPageString = null;
		for (Element element : scriptElements) {
			if (element.toString().contains("createPageHTML")) {
				countPageString = StringUtils.substringBetween(
						element.toString(), "//WCM置标\ncreatePageHTML(", ", 0,");

			}
		}
		countPageString = StringUtils.strip(countPageString);
		if (StringUtils.isNumeric(countPageString)) {
			return Integer.parseInt(countPageString);
		}
		return 0;

	}

	@Override
	public TreeMap<Integer, String> extractNextPageUrlMap(Document document,
			String baseUrl) {
		int maxPage = extractMaxPageNum(document);
		String temp[] = baseUrl.split(".shtml");
		String preUrl = temp[0];
		TreeMap<Integer, String> nextPageUrlMap = new TreeMap<Integer, String>();
		for (int i = 1; i < maxPage; i++) {
			nextPageUrlMap.put(i, preUrl + "_" + i + ".shtml");
		}
		return nextPageUrlMap;
	}

}
