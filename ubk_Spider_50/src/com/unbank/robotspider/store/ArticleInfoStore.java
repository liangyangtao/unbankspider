package com.unbank.robotspider.store;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import com.unbank.robotspider.dao.ArticleContentMapper;
import com.unbank.robotspider.dao.ArticleInfoMapper;
import com.unbank.robotspider.dao.EveryDayNumMapper;
import com.unbank.robotspider.dao.EveryWebDayNumMapper;
import com.unbank.robotspider.dao.MyBatisConnectionFactory;
import com.unbank.robotspider.entity.ArticleContent;
import com.unbank.robotspider.entity.ArticleInfo;
import com.unbank.robotspider.entity.EveryWebDayNum;

public class ArticleInfoStore {
	private static Log logger = LogFactory.getLog(ArticleInfoStore.class);

	public void saveArticleInfo(ArticleInfo articleInfo,
			ArticleContent articleContent) {
		SqlSession sqlSession = MyBatisConnectionFactory
				.getInstanceSessionFactory().openSession();
		try {
			int crawlId = saveArticleInfo(articleInfo, sqlSession);
			saveArticleContent(articleContent, sqlSession, crawlId);
			saveEveryDayNum(sqlSession);
			saveEveryWebDayNum(sqlSession, articleInfo);
			sqlSession.commit(true);
		} catch (Exception e) {
			if (e instanceof org.apache.ibatis.exceptions.PersistenceException) {
				logger.info("", e);
			} else {
				logger.info("", e);
			}
		} finally {
			sqlSession.close();
		}

	}

	private void saveEveryWebDayNum(SqlSession sqlSession,
			ArticleInfo articleInfo) {
		EveryWebDayNumMapper everyWebDayNumMapper = sqlSession
				.getMapper(EveryWebDayNumMapper.class);
		EveryWebDayNum everyWebDayNum = new EveryWebDayNum();
		everyWebDayNum.setWebsiteId(articleInfo.getWebsiteId());
		everyWebDayNumMapper.insert(everyWebDayNum);
	}

	private void saveEveryDayNum(SqlSession sqlSession) {

		EveryDayNumMapper everyDayNumMapper = sqlSession
				.getMapper(EveryDayNumMapper.class);
		everyDayNumMapper.insert(null);

	}

	private void saveArticleContent(ArticleContent articleContent,
			SqlSession sqlSession, int crawlId) {
		ArticleContentMapper articleContentMapper = sqlSession
				.getMapper(ArticleContentMapper.class);
		articleContent.setCrawlId(crawlId);
		articleContentMapper.insertSelective(articleContent);
	}

	private int saveArticleInfo(ArticleInfo articleInfo, SqlSession sqlSession) {
		ArticleInfoMapper articleInfoMapper = sqlSession
				.getMapper(ArticleInfoMapper.class);
		articleInfoMapper.insertSelective(articleInfo);
		int crawlId = articleInfo.getCrawlId();
		return crawlId;
	}
}
