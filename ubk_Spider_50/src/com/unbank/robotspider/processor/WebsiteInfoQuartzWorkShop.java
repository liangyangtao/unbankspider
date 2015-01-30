package com.unbank.robotspider.processor;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import com.unbank.robotspider.entity.WebSiteInfo;
import com.unbank.robotspider.worker.WebSiteInfoProductor;

public class WebsiteInfoQuartzWorkShop extends BaseWorkShop implements Runnable {
	protected LinkedBlockingQueue<Object> webSiteQueue;
	protected Integer task;
	protected Integer timeInterval;

	public WebsiteInfoQuartzWorkShop(LinkedBlockingQueue<Object> webSiteQueue,
			Integer task, Integer timeInterval) {
		this.webSiteQueue = webSiteQueue;
		this.task = task;
		this.timeInterval = timeInterval;
	}

	@Override
	public void run() {
		try {
			if (webSiteQueue.size() == 0) {
				fillQueue();
			}
		} catch (Exception e) {
			logger.info("", e);
		}
	}

	private void fillQueue() {
		List<WebSiteInfo> webSites = new WebSiteInfoProductor()
				.getWebSiteInfoByRank(task, timeInterval);
		for (WebSiteInfo webSiteInfo : webSites) {
			put(webSiteQueue, webSiteInfo);
		}
		int num = webSites.size();
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
