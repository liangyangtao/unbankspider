package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class CnOpsteel extends BaseFilter {
	private String domain = "www.opsteel.cn";

	public CnOpsteel() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("#articlebody").first();
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
		str = str.replace("更多钢铁业内热点资讯请进入行业聚焦栏目", "");
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

	public String removeStockCode(String content) {
		String stockCodes[] = new String[] {
				"<!--.[^-]*(?=-->)-->",
				"(?is)<!--.*?-->",
				"\\(\\d{6},\\)",
				"\\(\\d{1,6}[\\.|,|，|；|;|-][ |(&nbsp;)]{0,10}\\w{2}\\)",
				"\\(\\d{1,6}\\.\\w{2,4}[，|,|;|；][\\u4e00-\\u9fa5]{2,10}\\)",
				"\\(\\d{1,6}\\.\\w{2,4}/\\d{1,6}\\.\\w{2,4}\\)",
				"\\(\\d{1,6}\\.\\w{2,4};\\d{1,6}\\.\\w{2,4}\\)",
				"\\(\\d{1,6}\\.\\w{2,4}；\\d{1,6}\\.\\w{2,4}\\)",
				"\\(\\d{1,6}\\.\\w{2,4}、\\d{1,6}\\.\\w{2,4}\\)",
				"\\(\\d{1,6}\\.\\w{2,4},\\d{1,6}\\.\\w{2,4}\\)",
				"\\(\\d{1,6}\\.\\w{2,4}，\\d{1,6}\\.\\w{2,4}\\)",
				"\\(\\d{1,6}\\.\\w{2,4},-{0,1}\\d{1,2}\\.\\d{1,4},-{0,1}\\d{1,2}\\.\\d{1,2}%\\)",
				"\\(\\d{1,6}\\.\\w{2,4}, -{0,1}\\d{1,2}\\.\\d{1,4}, -{0,1}\\d{1,2}\\.\\d{1,2}%\\)",
				"\\(\\d{1,6}\\.\\w{2,4},(&nbsp;){0,10}-{0,1}\\d{1,2}\\.\\d{1,4},(&nbsp;){0,10}-{0,1}\\d{1,2}\\.\\d{1,2}%\\)",
				"\\(\\d{1,6}\\.\\w{2,4}，-{0,1}\\d{1,2}\\.\\d{1,4}，-{0,1}\\d{1,2}\\.\\d{1,2}%\\)",
				"\\(\\d{1,6}\\.\\w{2,4}， -{0,1}\\d{1,2}\\.\\d{1,4}， -{0,1}\\d{1,2}\\.\\d{1,2}%\\)",
				"\\(\\d{1,6}\\.\\w{2,4}，(&nbsp;){0,10}-{0,1}\\d{1,2}\\.\\d{1,4}，(&nbsp;){0,10}-{0,1}\\d{1,2}\\.\\d{1,2}%\\)",
				"\\(\\d{1,6}\\.\\w{1,4},-{0,1}\\d{1,6}\\.\\w{2,4},-{0,1}\\d{1,2}\\.\\d{1,2}%,实时行情\\)",
				"\\(\\d{1,6}\\.\\w{1,4}，-{0,1}\\d{1,6}\\.\\w{2,4}，-{0,1}\\d{1,2}\\.\\d{1,2}%，实时行情\\)",
				"\\[-{0,1}\\d{1,4}\\.\\d{1,2}% 资金 研报\\]",
				"\\[-{0,1}\\d{1,4}\\.\\d{1,2}%[(&nbsp;)| |	| ]{0,10}资金 研报\\]",

				"摘自\\w{2,5}网", "【[\\u4e00-\\u9fa5]{2,6}网】",
				"\\(([^\\(]*)?\\d{5,6}([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?微博([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?基金吧([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?股吧([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?代码([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?记者([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?编辑([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?作者([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?点击([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?访问([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?www\\.([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?http://([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?来源([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?标题([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?微信([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?收盘价([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?客户端([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?交易所([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?行情([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?评论([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?声明([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?版权([^\\(|\\)]*)?\\)",

		};
		for (String string : stockCodes) {
			content = content.replaceAll(string, "");
		}
		return content;
	}

}
