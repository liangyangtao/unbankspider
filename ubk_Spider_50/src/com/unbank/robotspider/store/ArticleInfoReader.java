package com.unbank.robotspider.store;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import com.unbank.robotspider.dao.ArticleInfoMapper;
import com.unbank.robotspider.dao.MyBatisConnectionFactory;
import com.unbank.robotspider.entity.ArticleInfo;
import com.unbank.robotspider.entity.ArticleInfoExample;

public class ArticleInfoReader {

	private static Log logger = LogFactory.getLog(ArticleInfoReader.class);

	public List<ArticleInfo> readArticleInfoByTask(Integer task) {

		SqlSession sqlSession = MyBatisConnectionFactory
				.getInstanceSessionFactory().openSession();
		ArticleInfoMapper articleInfoMapper = sqlSession
				.getMapper(ArticleInfoMapper.class);
		ArticleInfoExample articleInfoExample = getArticleInfoExampleByTask(task);
		List<ArticleInfo> articleInfos = null;
		try {
			articleInfos = articleInfoMapper
					.selectByExample(articleInfoExample);
		} catch (Exception e) {
			logger.info("", e);

		} finally {
			sqlSession.close();
		}
		return articleInfos;
	}

	private ArticleInfoExample getArticleInfoExampleByTask(Integer task) {
		ArticleInfoExample articleInfoExample = new ArticleInfoExample();
		articleInfoExample.or().andTaskEqualTo(Byte.parseByte(task + ""));
		articleInfoExample.setOrderByClause("crawl_id  desc limit 1");
		return articleInfoExample;
	}

}
