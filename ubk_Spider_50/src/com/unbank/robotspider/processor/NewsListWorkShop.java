package com.unbank.robotspider.processor;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import com.unbank.robotspider.entity.NewsInfoMiddleWare;
import com.unbank.robotspider.entity.WebSiteInfo;
import com.unbank.robotspider.worker.NewsListProducer;

public class NewsListWorkShop extends BaseWorkShop implements Runnable {
	protected LinkedBlockingQueue<Object> webSiteQueue;

	protected LinkedBlockingQueue<Object> newsListQueue;

	public NewsListWorkShop(LinkedBlockingQueue<Object> webSiteQueue,
			LinkedBlockingQueue<Object> newsListQueue) {
		this.webSiteQueue = webSiteQueue;
		this.newsListQueue = newsListQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (newsListQueue.size() <= 100000 && webSiteQueue.size() > 0) {

					WebSiteInfo webSiteInfo = null;

					webSiteInfo = (WebSiteInfo) take(webSiteQueue);

					if (webSiteInfo != null) {
						fillNewsListQueue(webSiteInfo);
					}
				}
				sleeping(1);
			} catch (Exception e) {
				logger.info("", e);
				continue;
			}
		}
	}

	private void fillNewsListQueue(WebSiteInfo webSiteInfo) {
		List<NewsInfoMiddleWare> middleWares = new NewsListProducer()
				.fillNewsListQueue(webSiteInfo);
		for (NewsInfoMiddleWare middleWare : middleWares) {
			logger.info(middleWare.getCrawlTitle());
			if (middleWare != null) {
				put(newsListQueue, middleWare);
			}
		}
		middleWares.clear();

	}
}
