package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class TakungpaoTitleListFilter extends BaseTitleListFilter {

	private String domain[] = new String[] { "rss.takungpao.com",
			"bodhi.takungpao.com", "sports.takungpao.com",
			"photo.takungpao.com", "ent.takungpao.com", "arts.takungpao.com",
			"hea.takungpao.com", "search.takungpao.com",
			"health.takungpao.com", "food.takungpao.com", "digi.takungpao.com",
			"edu.takungpao.com", "talk.takungpao.com", "www.takungpao.com",
			"finance.takungpao.com", "news.takungpao.com",
			"comments.takungpao.com", "auto.takungpao.com",
			"lady.takungpao.com" };

	public TakungpaoTitleListFilter() {
		for (int i = 0; i < domain.length; i++) {
			TitleListFilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		Elements elements = getPossibleListElement(document, ".txtImgList");
		if (elements ==null) {
			elements = getPossibleListElement(document, ".m_txt_news");
		}
		return elements;
	}

	public void removeNoNeedElementsByCssQuery(Element contentElement) {
		String cssQuerys[] = new String[] { "a.a_time" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}
}
