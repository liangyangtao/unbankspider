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
import com.unbank.robotspider.processor.WebsiteInfoQuartzByTimeWorkShop;

public class UnbankQuartzByTimeCrawler implements UnbankCrawlerMBean {

	private static Log logger = LogFactory
			.getLog(UnbankQuartzByTimeCrawler.class);
	public Integer task;

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

	public void start() {
		logger.info("执行到此处");
		new WebsiteInfoQuartzByTimeWorkShop(webSiteInfoQueue, task).run();

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

	public Integer getTask() {
		return task;
	}

	public void setTask(Integer task) {
		this.task = task;
	}

}
