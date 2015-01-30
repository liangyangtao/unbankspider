package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComCcidnet extends BaseFilter {
	private String domain[] = new String[] { "miit.ccidnet.com",
			"info.ccidnet.com", "wlw.ccidnet.com", "nssp.ccidnet.com",
			"eco.ccidnet.com", "smb.ccidnet.com", "cul.ccidnet.com",
			"project.ccidnet.com", "iot.ccidnet.com", "www.ccidnet.com",
			"bbs.ccidnet.com", "blog.ccidnet.com", "xiazai.ccidnet.com",
			"register.ccidnet.com", "news.ccidnet.com", "tech.ccidnet.com",
			"service.ccidnet.com", "internet.ccidnet.com", "jd.ccidnet.com",
			"comm.ccidnet.com", "mi.ccidnet.com", "industry.ccidnet.com",
			"smartcity.ccidnet.com", "3s.ccidnet.com", "bdt.ccidnet.com",
			"pbxs.ccidnet.com", "cio.ccidnet.com", "auto.ccidnet.com",
			"finance.ccidnet.com", "bigdata.ccidnet.com", "cloud.ccidnet.com",
			"product.ccidnet.com", "expert.ccidnet.com",
			"education.ccidnet.com"};

	public ComCcidnet() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select(".temp").first();
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
		str = str
				.replace(
						"本文由物联中国作者独家撰写，版权属于物联中国，转载请注明作者和出处，并附上网址物联中国www.50cnnet.com，谢谢!",
						"");
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
