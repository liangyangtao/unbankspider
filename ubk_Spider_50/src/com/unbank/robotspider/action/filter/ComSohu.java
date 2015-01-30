package com.unbank.robotspider.action.filter;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Component
public class ComSohu extends BaseFilter {

	private String domain[] = new String[] { "stock.sohu.com",
			"business.sohu.com", "money.sohu.com" };

	public ComSohu() {

		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("#contentText > div:nth-child(1)")
				.first();

		String cssQuerys[] = new String[] { "script", "div.muLink",
				".divstockguba",
				"a[href=http://q.fund.sohu.com/fund/fundShareCompare.shtml]" };
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
		Elements elements = maxTextElement.getElementsByTag("img");
		for (int i = 0; i < elements.size(); i++) {
			Element e = elements.get(i);
			if ("http://stock.sohu.com/upload/stock20121119/images/ewm_jqb.jpg"
					.equals(e.attr("src"))) {
				elements.get(i).remove();
			}
		}
		super.saveImage(maxTextElement, url);
	}

	@Override
	public String replaceStockCode(String content) {
		return super.replaceStockCode(content);
	}

	@Override
	public String replaceSpechars(String content) {
		String str = super.replaceSpechars(content);
		List<String> list = new ArrayList<String>();
		list.add("点击进入【股友会】参与讨论");
		for (String s : list) {
			int index = str.indexOf(s);
			if (index > 0) {
				str = str.substring(0, index);
			}
		}
		return str;
	}

	@Override
	public String addTag(String content) {
		if (content == null) {
			return "";
		}
		Document document2 = Jsoup.parse(content);
		wrapPToTextNodes(document2.body());
		content = document2.body().toString();
		content = content.replace("\n", "");
		content = content.replace("\r", "");
		content = content.replace("<body>", "");
		content = content.replace("</body>", "");
		content = "<div> " + content + "</div>";

		Document document3 = Jsoup.parse(content);

		Element e = document3.getElementsByTag("p").first();
		String s = e.text();
		if (s.contains("本报记者") && s.contains("报道")) {
			document3.getElementsByTag("p").first().remove();
		}
		wrapPToTextNodes(document3.body());
		content = document3.body().toString();
		content = content.replace("\n", "");
		content = content.replace("\r", "");
		content = content.replace("<body>", "");
		content = content.replace("</body>", "");
		content = "<div> " + content + "</div>";

		return content;
	}

}
