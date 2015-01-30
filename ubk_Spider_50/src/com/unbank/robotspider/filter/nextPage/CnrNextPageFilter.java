package com.unbank.robotspider.filter.nextPage;

import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnrNextPageFilter extends NextPageBaseFilter {

	private String domain[] = new String[] { "www.cnr.cn", "news.cnr.cn",
			"finance.cnr.cn", "auto.cnr.cn", "ent.cnr.cn", "pic.cnr.cn",
			"tv.cnr.cn", "tech.cnr.cn", "military.cnr.cn", "travel.cnr.cn",
			"sports.cnr.cn", "health.cnr.cn", "gongyi.cnr.cn", "aod.cnr.cn",
			"edu.cnr.cn", "bbs.cnr.cn", "lady.cnr.cn", "life.cnr.cn",
			"country.cnr.cn", "roll.cnr.cn", "china.cnr.cn",
			"musicradio.cnr.cn", "old.cnr.cn", "fm1018.cnr.cn",
			"hxradio.cnr.cn", "photo.cnr.cn", "cbu.cnr.cn", "cneb.cnr.cn",
			"ygzq.cnr.cn", "diaocha.cnr.cn", "gb.cnr.cn" };

	public CnrNextPageFilter() {
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

				String text = element.toString().replaceAll("\\s", "");
				countPageString = StringUtils.substringBetween(text,
						"//WCM置标createPageHTML(", ",0,");
				break;
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
