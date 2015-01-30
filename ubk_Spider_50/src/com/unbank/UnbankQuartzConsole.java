package com.unbank;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unbank.robotspider.util.FinalWord;

public class UnbankQuartzConsole {
	private static Log logger = LogFactory.getLog(UnbankQuartzConsole.class);
	static {
		// 启动日志
		try {
			PropertyConfigurator.configure(UnbankQuartzConsole.class
					.getClassLoader().getResource("").toURI().getPath()
					+ FinalWord.LOGFILE);
			logger.info("---日志系统启动成功---");
		} catch (Exception e) {
			logger.error("日志系统启动失败:", e);
		}
	}

	public static void main(String[] args) {
		// 启动抓取程序
		new ClassPathXmlApplicationContext(new String[] {
				"applicationContext.xml", "quartz_spring.xml" });
		// 启动监控

	}

}
