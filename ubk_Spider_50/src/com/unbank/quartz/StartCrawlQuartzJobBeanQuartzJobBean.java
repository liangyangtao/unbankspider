package com.unbank.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.unbank.robotspider.UnbankQuartzByTimeCrawler;

public class StartCrawlQuartzJobBeanQuartzJobBean {
	private static Log logger = LogFactory
			.getLog(StartCrawlQuartzJobBeanQuartzJobBean.class);
	UnbankQuartzByTimeCrawler unbankQuartzByTimeCrawler;

	public void executeInternal() {
		try {
			unbankQuartzByTimeCrawler.start();
		} catch (Exception e) {
			logger.info("检测内容节点出错", e);
		}
	}

	public UnbankQuartzByTimeCrawler getUnbankQuartzByTimeCrawler() {
		return unbankQuartzByTimeCrawler;
	}

	public void setUnbankQuartzByTimeCrawler(
			UnbankQuartzByTimeCrawler unbankQuartzByTimeCrawler) {
		this.unbankQuartzByTimeCrawler = unbankQuartzByTimeCrawler;
	}

}
