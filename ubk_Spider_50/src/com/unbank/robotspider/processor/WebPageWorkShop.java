package com.unbank.robotspider.processor;

import java.util.concurrent.LinkedBlockingQueue;

import com.unbank.robotspider.entity.NewsInfoMiddleWare;
import com.unbank.robotspider.worker.WebPageProducer;

public class WebPageWorkShop extends BaseWorkShop implements Runnable {
	protected LinkedBlockingQueue<Object> newsPageQueue;
	protected LinkedBlockingQueue<Object> newsListQueue;

	public WebPageWorkShop(LinkedBlockingQueue<Object> newsListQueue,
			LinkedBlockingQueue<Object> newsPageQueue) {
		this.newsListQueue = newsListQueue;
		this.newsPageQueue = newsPageQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (newsListQueue.size() > 0) {
					NewsInfoMiddleWare newsInfoMiddleWare = null;

					newsInfoMiddleWare = (NewsInfoMiddleWare) take(newsListQueue);

					if (newsInfoMiddleWare != null) {
						try {
							fillNewsPageQueue(newsInfoMiddleWare);
						} catch (Exception e) {
							logger.info("获取内容失败", e);
						}
					}
				}
				sleeping(1);
			} catch (Exception e) {
				logger.info("", e);
				continue;
			}
		}
	}

	private void fillNewsPageQueue(NewsInfoMiddleWare newsInfoMiddleWare) {

		NewsInfoMiddleWare articleInfoText = new WebPageProducer()
				.fillNewsPageQueue(newsInfoMiddleWare);
		if (articleInfoText != null) {
			put(newsPageQueue, articleInfoText);
		}

	}

}
