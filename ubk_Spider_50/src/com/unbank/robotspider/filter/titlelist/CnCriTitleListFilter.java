package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnCriTitleListFilter extends BaseTitleListFilter {
	private String domain = "gb.cri.cn";

	public CnCriTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
	    return	document.select("div#more>table>tbody>tr>td>a");

	}

}
