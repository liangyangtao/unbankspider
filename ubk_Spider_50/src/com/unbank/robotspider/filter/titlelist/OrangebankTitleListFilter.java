package com.unbank.robotspider.filter.titlelist;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.fetch.JsoupNetFetcher;

@Service
public class OrangebankTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.orangebank.com.cn";

	public OrangebankTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		String urls[] = new String[] {
				"http://www.orangebank.com.cn/web/article/solrarticles.xhtml?jsoncallback=jQuery18302806485111090026_1411103163101&totalPage=&currentPage=1&pageSize=20&orderfield=modified_time-article_id&ordertype=2&params=publish_state%3A1-column_id%3A19140813100014534&_=1411103171100",
				"http://www.orangebank.com.cn/web/article/solrarticles.xhtml?jsoncallback=jQuery18307532054467462563_1411103206315&totalPage=&currentPage=1&pageSize=20&orderfield=modified_time-article_id&ordertype=2&params=publish_state%3A1-column_id%3A19140813100014536&_=1411103223184",
				"http://www.orangebank.com.cn/web/article/solrarticles.xhtml?jsoncallback=jQuery18307532054467462563_1411103206315&totalPage=&currentPage=1&pageSize=20&orderfield=modified_time-article_id&ordertype=2&params=publish_state%3A1-column_id%3A19140813100014537&_=1411103250353",
				"http://www.orangebank.com.cn/web/article/solrarticles.xhtml?jsoncallback=jQuery18307532054467462563_1411103206315&totalPage=&currentPage=1&pageSize=20&orderfield=modified_time-article_id&ordertype=2&params=publish_state%3A1-column_id%3A19140813100014538&_=1411103287655",
				"http://www.orangebank.com.cn/web/article/solrarticles.xhtml?jsoncallback=jQuery1830998197317039432_1411096000687&totalPage=&currentPage=1&pageSize=20&orderfield=modified_time-article_id&ordertype=2&params=publish_state%3A1-column_id%3A19140813100014535&_=1411096080796" };
		StringBuffer sb = new StringBuffer();
		for (String string : urls) {
			try {
				String content = new JsoupNetFetcher()
						.getDocumentByJsoup(string).body().text();
				String jsonString = StringUtils.substringBetween(content,
						"({\"objectT\":{},", "})");
				JSONObject jsonObject = JSONObject
						.fromObject("{\"objectT\":{}," + jsonString + "}");
				JSONArray listArray = jsonObject.getJSONArray("list");
				for (int i = 0; i < listArray.size(); i++) {
					JSONObject jsonItem = listArray.getJSONObject(i);
					String url = StringUtils.substring(
							jsonItem.getString("url"), 9);
					String temp = "<a href='http://www.orangebank.com.cn/"
							+ url + "'>" + jsonItem.getString("article_title")
							+ "</a>";
					sb.append(temp);
				}
			} catch (Exception e) {
				continue;
			}
		}
		document = Jsoup.parse(sb.toString(), document.baseUri());

		Elements possibleListElements = document.select("a");
		return possibleListElements;
	}

}
