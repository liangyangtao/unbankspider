package com.unbank.robotspider.processor;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import com.unbank.robotspider.entity.WebSiteInfo;
import com.unbank.robotspider.worker.WebSiteInfoProductor;

public class WebsiteInfoWorkShop extends BaseWorkShop implements Runnable {
	protected LinkedBlockingQueue<Object> webSiteQueue;
	protected Integer task;

	public WebsiteInfoWorkShop(LinkedBlockingQueue<Object> webSiteQueue,
			Integer task) {
		this.webSiteQueue = webSiteQueue;
		this.task = task;
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (webSiteQueue.size() <= 10) {
					fillQueue();
				}
			} catch (Exception e) {
				logger.info("", e);
				continue;
			}

		}
	}

	private void fillQueue() {
		List<WebSiteInfo> webSites = new WebSiteInfoProductor()
				.getWebSiteInfoList(task);
		for (WebSiteInfo webSiteInfo : webSites) {
			put(webSiteQueue, webSiteInfo);
		}
		int num = webSites.size();
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		webSites.clear();

	}

}
