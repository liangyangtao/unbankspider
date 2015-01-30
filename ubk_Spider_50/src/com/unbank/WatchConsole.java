package com.unbank;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.unbank.robotspider.util.FinalWord;
import com.unbank.watch.PidRecorder;

public class WatchConsole {
	private static Log logger = LogFactory.getLog(UnbankConsole.class);

	static {
		// 启动日志
		try {
			PropertyConfigurator.configure(UnbankConsole.class.getClassLoader()
					.getResource("").toURI().getPath()
					+ FinalWord.LOGFILE);
			logger.info("---日志系统启动成功---");
		} catch (Exception e) {
			logger.error("日志系统启动失败:", e);
		}
	}

	public static void main(String[] args) {
		new PidRecorder("unbankSpider.bat", "UNBANKWARCHPID").start();
	}

}
