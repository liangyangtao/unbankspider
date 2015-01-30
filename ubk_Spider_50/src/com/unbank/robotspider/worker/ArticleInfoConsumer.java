package com.unbank.robotspider.worker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.unbank.robotspider.entity.ArticleContent;
import com.unbank.robotspider.entity.ArticleContentTest;
import com.unbank.robotspider.entity.ArticleInfo;
import com.unbank.robotspider.entity.ArticleInfoTest;
import com.unbank.robotspider.entity.NewsInfoMiddleWare;
import com.unbank.robotspider.store.ArticleInfoStore;
import com.unbank.robotspider.store.ArticleInfoTestStore;

public class ArticleInfoConsumer {
	private static Log logger = LogFactory.getLog(ArticleInfoConsumer.class);

	public void consumeArticleQueue(NewsInfoMiddleWare newsInfoMiddleWare) {
		if (newsInfoMiddleWare.getTask() == 2) {
			ArticleInfo articleInfo = fillArticleInfo(newsInfoMiddleWare);
			ArticleContent articleContent = fillArticleContent(newsInfoMiddleWare);
			saveArticleInfo(articleInfo, articleContent);
		} else if (newsInfoMiddleWare.getTask() == 1) {
			ArticleInfoTest articleInfoTest = fillArticleInfoTest(newsInfoMiddleWare);
			ArticleContentTest articleContentTest = fillArticleContentTest(newsInfoMiddleWare);
			saveArticleInfo(articleInfoTest, articleContentTest);
		}

	}

	private void saveArticleInfo(ArticleInfoTest articleInfoTest,
			ArticleContentTest articleContentTest) {
		new ArticleInfoTestStore().saveArticleInfo(articleInfoTest,
				articleContentTest);

	}

	private ArticleContentTest fillArticleContentTest(
			NewsInfoMiddleWare newsInfoMiddleWare) {
		ArticleContentTest articleContent = new ArticleContentTest();
		articleContent.setText(newsInfoMiddleWare.getText());
		return articleContent;
	}

	private ArticleInfoTest fillArticleInfoTest(
			NewsInfoMiddleWare newsInfoMiddleWare) {
		ArticleInfoTest articleInfo = new ArticleInfoTest();
		articleInfo.setWebName(newsInfoMiddleWare.getWebName());
		articleInfo.setWebsiteId(newsInfoMiddleWare.getWebsiteId());
		articleInfo.setUrl(newsInfoMiddleWare.getUrl());
		articleInfo.setCrawlTitle(newsInfoMiddleWare.getCrawlTitle());
		articleInfo.setFileIndex(newsInfoMiddleWare.getFileIndex());
		articleInfo.setCrawlTime(newsInfoMiddleWare.getCrawlTime());
		articleInfo.setTask(newsInfoMiddleWare.getTask());
		articleInfo.setCrawlBrief(newsInfoMiddleWare.getCrawlBrief());
		articleInfo.setNewsTime(newsInfoMiddleWare.getNewsTime());
		return articleInfo;
	}

	private void saveArticleInfo(ArticleInfo articleInfo,
			ArticleContent articleContent) {
		new ArticleInfoStore().saveArticleInfo(articleInfo, articleContent);
	}

	private ArticleContent fillArticleContent(
			NewsInfoMiddleWare newsInfoMiddleWare) {
		ArticleContent articleContent = new ArticleContent();
		articleContent.setText(newsInfoMiddleWare.getText());
		return articleContent;
	}

	private ArticleInfo fillArticleInfo(NewsInfoMiddleWare newsInfoMiddleWare) {

		ArticleInfo articleInfo = new ArticleInfo();
		articleInfo.setWebName(newsInfoMiddleWare.getWebName());
		articleInfo.setWebsiteId(newsInfoMiddleWare.getWebsiteId());
		articleInfo.setUrl(newsInfoMiddleWare.getUrl());
		articleInfo.setCrawlTitle(newsInfoMiddleWare.getCrawlTitle());
		articleInfo.setFileIndex(newsInfoMiddleWare.getFileIndex());
		articleInfo.setCrawlTime(newsInfoMiddleWare.getCrawlTime());
		articleInfo.setTask(newsInfoMiddleWare.getTask());
		articleInfo.setCrawlBrief(newsInfoMiddleWare.getCrawlBrief());
		articleInfo.setNewsTime(newsInfoMiddleWare.getNewsTime());
		return articleInfo;

	}
}
