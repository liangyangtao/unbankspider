package com.unbank.robotspider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.unbank.robotspider.action.model.normal.DBFilter;
import com.unbank.robotspider.filter.title.dbfilter.TitleDBFilter;
import com.unbank.robotspider.filter.titlelist.dbfilter.TitleListDBFilter;
import com.unbank.robotspider.processor.ArticleWorkShop;
import com.unbank.robotspider.processor.NewsListWorkShop;
import com.unbank.robotspider.processor.StoreWorkShop;
import com.unbank.robotspider.processor.WebPageWorkShop;
import com.unbank.robotspider.processor.WebsiteInfoQuartzWorkShop;

public class UnbankQuartzCrawler implements UnbankCrawlerMBean {

	private static Log logger = LogFactory.getLog(UnbankQuartzCrawler.class);
	public Integer timeInterval;
	LinkedBlockingQueue<Object> webSiteInfoQueue = null;
	LinkedBlockingQueue<Object> newsListQueue = null;
	LinkedBlockingQueue<Object> newsPageQueue = null;
	LinkedBlockingQueue<Object> articleInfoQueue = null;

	public void init() {

		webSiteInfoQueue = new LinkedBlockingQueue<Object>();

		newsListQueue = new LinkedBlockingQueue<Object>();

		newsPageQueue = new LinkedBlockingQueue<Object>();

		articleInfoQueue = new LinkedBlockingQueue<Object>();
		
		logger.info("===================从数据库中加载Filters=======================");
		DBFilter.getInstance().loadFiltersFromDB();
		TitleListDBFilter.getInstance().loadFiltersFromDB();
		TitleDBFilter.getInstance().loadFiltersFromDB();
		logger.info("===================加载Filters完成！===========================");
		
		
		ExecutorService executor = Executors.newFixedThreadPool(160);

		for (int i = 0; i < 16; i++) {
			executor.execute(new NewsListWorkShop(webSiteInfoQueue,
					newsListQueue));
		}
		for (int i = 0; i < 80; i++) {
			executor.execute(new WebPageWorkShop(newsListQueue, newsPageQueue));
		}
		for (int i = 0; i < 16; i++) {
			executor.execute(new ArticleWorkShop(newsPageQueue,
					articleInfoQueue));
		}
		for (int i = 0; i < 16; i++) {
			executor.execute(new StoreWorkShop(articleInfoQueue));
		}
		executor.shutdown();
	}

	public void start() {
		Integer task = 1;
		new WebsiteInfoQuartzWorkShop(webSiteInfoQueue, task, timeInterval)
				.run();
		
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

	public Integer getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(Integer timeInterval) {
		this.timeInterval = timeInterval;
	}

}
