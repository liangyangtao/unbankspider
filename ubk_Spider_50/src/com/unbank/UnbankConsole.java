package com.unbank;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unbank.robotspider.UnbankCrawler;
import com.unbank.robotspider.util.FinalWord;
import com.unbank.robotspider.util.Values;
import com.unbank.watch.PidRecorder;

public class UnbankConsole {
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
		// 启动抓取程序
		new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		// 启动监控
		
		
		new PidRecorder("unbankWatch.bat", "UNBANKSPIDERPID").start();
		UnbankCrawler uc = new UnbankCrawler();
		try {
			init_jmx(uc);
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		uc.start();
	}

	public static void init_jmx(UnbankCrawler uc) throws MalformedObjectNameException,
			NullPointerException, InstanceAlreadyExistsException,
			MBeanRegistrationException, NotCompliantMBeanException, IOException {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		
		
		ObjectName name = new ObjectName("com.unbank.robotspider:type=UnbankCrawler");

		mbs.registerMBean(uc, name);
		
		int rmiPort = 9999;
		String jmxServerName = "unbankCrawler";

		java.rmi.registry.LocateRegistry.createRegistry(rmiPort);
		
		logger.info("host:"+Values.v.IMAGEHOST);
		JMXServiceURL url = new JMXServiceURL(
				"service:jmx:rmi:///jndi/rmi://localhost:" + rmiPort + "/"
						+ jmxServerName);
		JMXConnectorServer jmxConnServer = JMXConnectorServerFactory
				.newJMXConnectorServer(url, null, mbs);
		
		jmxConnServer.start();
		logger.info("JMX监控启动成功....................");
	}
}
