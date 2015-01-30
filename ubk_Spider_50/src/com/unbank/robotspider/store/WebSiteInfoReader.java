package com.unbank.robotspider.store;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.unbank.robotspider.dao.MyBatisConnectionFactory;
import com.unbank.robotspider.dao.WebSiteInfoMapper;
import com.unbank.robotspider.entity.WebSiteInfo;
import com.unbank.robotspider.entity.WebSiteInfoExample;

public class WebSiteInfoReader {

	private final Logger logger = Logger.getLogger(WebSiteInfoReader.class);

	public List<WebSiteInfo> readWebSiteInfoList(Integer num) {
		SqlSession sqlSession = MyBatisConnectionFactory
				.getInstanceSessionFactory().openSession();
		WebSiteInfoMapper webSiteInfoMapper = sqlSession
				.getMapper(WebSiteInfoMapper.class);
		WebSiteInfoExample webSiteInfoExample = getTaskConditionExample(num);
		List<WebSiteInfo> webSiteInfos = null;
		try {
			webSiteInfos = webSiteInfoMapper
					.selectByExample(webSiteInfoExample);
		} catch (Exception e) {
			logger.info("", e);

		} finally {
			sqlSession.close();
		}
		return webSiteInfos;

	}

	private WebSiteInfoExample getTaskConditionExample(Integer num) {
		WebSiteInfoExample webSiteInfoExample = new WebSiteInfoExample();
		if (num != 0) {
			webSiteInfoExample.or().andIstaskEqualTo(num);
		}
		return webSiteInfoExample;
	}

	public List<WebSiteInfo> getWebSiteInfoByRank(Integer task,
			Integer timeInterval) {
		SqlSession sqlSession = MyBatisConnectionFactory
				.getInstanceSessionFactory().openSession();
		WebSiteInfoMapper webSiteInfoMapper = sqlSession
				.getMapper(WebSiteInfoMapper.class);
		WebSiteInfoExample webSiteInfoExample = getTaskConditionExample(task,
				timeInterval);
		List<WebSiteInfo> webSiteInfos = null;
		try {
			webSiteInfos = webSiteInfoMapper
					.selectByExample(webSiteInfoExample);
		} catch (Exception e) {
			logger.info("", e);

		} finally {
			sqlSession.close();
		}
		return webSiteInfos;
	}

	private WebSiteInfoExample getTaskConditionExample(Integer task,
			Integer timeInterval) {
		WebSiteInfoExample webSiteInfoExample = new WebSiteInfoExample();
		if (task != 0) {
			webSiteInfoExample.or().andIstaskEqualTo(task)
					.andRankEqualTo(timeInterval);
		} else {
			webSiteInfoExample.or().andRankEqualTo(timeInterval);
		}
		return webSiteInfoExample;
	}

	public List<WebSiteInfo> getWebSiteInfoByTask(Integer task) {
		if (task == 2) {
			SqlSession sqlSession = MyBatisConnectionFactory
					.getInstanceSessionFactory().openSession();
			WebSiteInfoMapper webSiteInfoMapper = sqlSession
					.getMapper(WebSiteInfoMapper.class);
			WebSiteInfoExample webSiteInfoExample = getTaskConditionExample(task);
			List<WebSiteInfo> webSiteInfos = null;
			try {
				webSiteInfos = webSiteInfoMapper
						.selectByExample(webSiteInfoExample);
			} catch (Exception e) {
				logger.info("", e);

			} finally {
				sqlSession.close();
			}
			return webSiteInfos;
		} else if (task == 1) {

			SqlSession sqlSession = MyBatisConnectionFactory
					.getInstanceSessionFactory().openSession();
			WebSiteInfoMapper webSiteInfoMapper = sqlSession
					.getMapper(WebSiteInfoMapper.class);
			WebSiteInfoExample webSiteInfoExample = getTask1ConditionExample(task);
			List<WebSiteInfo> webSiteInfos = null;
			try {
				webSiteInfos = webSiteInfoMapper
						.selectByExample(webSiteInfoExample);
			} catch (Exception e) {
				logger.info("", e);

			} finally {
				sqlSession.close();
			}
			return webSiteInfos;

		}
		return null;

	}

	private WebSiteInfoExample getTask1ConditionExample(Integer task) {
		WebSiteInfoExample webSiteInfoExample = new WebSiteInfoExample();
		Date strartTime = getMyDate(new Date(), -1);
		webSiteInfoExample.or().andIstaskEqualTo(task)
				.andCDateBetween(strartTime, new Date());
		return webSiteInfoExample;
	}

	public static Date getMyDate(Date date, int num) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DATE, num);
		date = calendar.getTime();
		return date;
	}

}
