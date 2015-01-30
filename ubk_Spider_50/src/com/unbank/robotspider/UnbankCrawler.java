package com.unbank.robotspider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.unbank.robotspider.filter.url.SimpleBloomFilter;
import com.unbank.robotspider.processor.ArticleWorkShop;
import com.unbank.robotspider.processor.NewsListWorkShop;
import com.unbank.robotspider.processor.StoreWorkShop;
import com.unbank.robotspider.processor.WebPageWorkShop;
import com.unbank.robotspider.processor.WebsiteInfoWorkShop;

public class UnbankCrawler implements UnbankCrawlerMBean {

	private static Log logger = LogFactory.getLog(UnbankCrawler.class);
	public static SimpleBloomFilter filter = SimpleBloomFilter.getInstance();
	LinkedBlockingQueue<Object> webSiteInfoQueue = null;
	LinkedBlockingQueue<Object> newsListQueue = null;
	LinkedBlockingQueue<Object> newsPageQueue = null;
	LinkedBlockingQueue<Object> articleInfoQueue = null;

	public void start() {
		Integer task = 2;
		webSiteInfoQueue = new LinkedBlockingQueue<Object>();

		newsListQueue = new LinkedBlockingQueue<Object>();

		newsPageQueue = new LinkedBlockingQueue<Object>();

		articleInfoQueue = new LinkedBlockingQueue<Object>();

		ExecutorService executor = Executors.newFixedThreadPool(200);

		executor.execute(new WebsiteInfoWorkShop(webSiteInfoQueue, task));

		for (int i = 0; i < 4; i++) {
			executor.execute(new NewsListWorkShop(webSiteInfoQueue,
					newsListQueue));
		}
		for (int i = 0; i < 96; i++) {
			executor.execute(new WebPageWorkShop(newsListQueue, newsPageQueue));
		}
		for (int i = 0; i < 4; i++) {
			executor.execute(new ArticleWorkShop(newsPageQueue,
					articleInfoQueue));
		}
		for (int i = 0; i < 32; i++) {
			executor.execute(new StoreWorkShop(articleInfoQueue));
		}
		executor.shutdown();

	}

	@Override
	public int getWebSiteInfoQueueSize() {
		return webSiteInfoQueue.size();
	}

	@Override
	public int getNewsListQueueSize() {
		return newsListQueue.size();
	}

	@Override
	public int getNewsPageQueueSize() {
		return newsPageQueue.size();
	}

	@Override
	public int getArticleInfoQueueSize() {
		return articleInfoQueue.size();
	}

	public static SimpleBloomFilter getFilter() {
		return filter;
	}

}
