package com.unbank.robotspider.store;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import com.unbank.robotspider.dao.ArticleContentTestMapper;
import com.unbank.robotspider.dao.ArticleInfoTestMapper;
import com.unbank.robotspider.dao.MyBatisConnectionFactory;
import com.unbank.robotspider.entity.ArticleContentTest;
import com.unbank.robotspider.entity.ArticleInfoTest;

public class ArticleInfoTestStore {
	private static Log logger = LogFactory.getLog(ArticleInfoTestStore.class);

	public void saveArticleInfo(ArticleInfoTest articleInfo,
			ArticleContentTest articleContent) {
		SqlSession sqlSession = MyBatisConnectionFactory
				.getInstanceSessionFactory().openSession();
		try {
			int crawlId = saveArticleInfo(articleInfo, sqlSession);
			saveArticleContent(articleContent, sqlSession, crawlId);
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

	private void saveArticleContent(ArticleContentTest articleContent,
			SqlSession sqlSession, int crawlId) {
		ArticleContentTestMapper articleContentMapper = sqlSession
				.getMapper(ArticleContentTestMapper.class);
		articleContent.setCrawlId(crawlId);
		articleContentMapper.insertSelective(articleContent);
	}

	private int saveArticleInfo(ArticleInfoTest articleInfo,
			SqlSession sqlSession) {
		ArticleInfoTestMapper articleInfoMapper = sqlSession
				.getMapper(ArticleInfoTestMapper.class);
		articleInfoMapper.insertSelective(articleInfo);
		int crawlId = articleInfo.getCrawlId();
		return crawlId;
	}

}
