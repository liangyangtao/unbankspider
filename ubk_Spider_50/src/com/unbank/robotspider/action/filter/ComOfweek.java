package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComOfweek extends BaseFilter {
	private String domain[] = new String[] { "www.ofweek.com",
			"global.ofweek.com", "lights.ofweek.com", "solar.ofweek.com",
			"ee.ofweek.com", "laser.ofweek.com", "fiber.ofweek.com",
			"gongkong.ofweek.com", "display.ofweek.com",
			"smartgrids.ofweek.com", "tele.ofweek.com", "ecep.ofweek.com",
			"windpower.ofweek.com", "iot.ofweek.com", "3dprint.ofweek.com",
			"smarthome.ofweek.com", "nev.ofweek.com", "instrument.ofweek.com",
			"dianyuan.ofweek.com", "lighting.ofweek.com", "optics.ofweek.com",
			"power.ofweek.com", "libattery.ofweek.com", "robot.ofweek.com",
			"wearable.ofweek.com", "bbs.ofweek.com", "research.ofweek.com",
			"finance.ofweek.com", "wenku.ofweek.com", "video.ofweek.com",
			"baike.ofweek.com", "exhibition.ofweek.com", "gfhr.ofweek.com",
			"hr.ofweek.com", "b2b.ofweek.com", "en.ofweek.com",
			"webinar.ofweek.com", "seminar.ofweek.com" };

	public ComOfweek() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select(".tob_wzb_lbp").first();
		if(element==null){
			element=document.select("div#articleC").first();
		}
		
		String cssQuerys[] = new String[] { ".page" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(element, cssQuery);
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
		String[] cc={"OFweek3D打印网讯：","OFweek可穿戴设备网讯："};
		for(String s:cc){
			str=str.replace(s, "");
		}
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
