package com.unbank.robotspider.processor;

import java.util.concurrent.LinkedBlockingQueue;

import com.unbank.robotspider.entity.NewsInfoMiddleWare;
import com.unbank.robotspider.worker.ArticleInfoConsumer;

public class StoreWorkShop extends BaseWorkShop implements Runnable {
	LinkedBlockingQueue<Object> articleInfoQueue;

	public StoreWorkShop(LinkedBlockingQueue<Object> articleInfoQueue) {
		this.articleInfoQueue = articleInfoQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (articleInfoQueue.size() > 0) {
					NewsInfoMiddleWare newsInfoMiddleWare = null;

					newsInfoMiddleWare = (NewsInfoMiddleWare) take(articleInfoQueue);

					if (newsInfoMiddleWare != null) {
						consumeArticleQueue(newsInfoMiddleWare);
					}
				}
				sleeping(5);
			} catch (Exception e) {
				logger.info("", e);
				continue;
			}
		}
	}

	private void consumeArticleQueue(NewsInfoMiddleWare newsInfoMiddleWare) {
//		logger.info(newsInfoMiddleWare.getCrawlTitle());
		new ArticleInfoConsumer().consumeArticleQueue(newsInfoMiddleWare);
	}

}
