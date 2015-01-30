package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class OfweekContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(OfweekContentFilter.class);

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

	public OfweekContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, ".tob_wzb_lbp");
		if (element == null) {
			return null;
		}
		return element;
	}

	public void removeNoNeedElementsByText(Element contentElement) {
		// String textQuerys[] = new String[] { "" };
		// for (String textQuery : textQuerys) {
		// removeNoNeedTextElement(contentElement, textQuery);
		// }
	}

	public void removeNoNeedElementsByCssQuery(Element contentElement) {
		String cssQuerys[] = new String[] { ".page" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
