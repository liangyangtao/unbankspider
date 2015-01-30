package com.unbank.watch;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.unbank.robotspider.entity.ArticleInfo;
import com.unbank.robotspider.store.ArticleInfoReader;
import com.unbank.robotspider.util.XmlUtil;

public class PidRecorder {

	private static Log logger = LogFactory.getLog(PidRecorder.class);
	public String pidName;
	public String batName;
	public String nodeName;

	public PidRecorder(String batName, String nodeName) {
		this.batName = batName;
		this.nodeName = nodeName;
	}

	public void start() {
		recordPid();

		if (!nodeName.equals("UNBANKSPIDERPID")) {
			monitorWatch();
		}

	}

	private void recordPid() {
		int pid = ProgressReader.getMyPid();
		writerXml(nodeName, pid);
		readXML(nodeName);

	}

	public void writerXml(String nodeName, int pid) {
		try {
			String path = XmlUtil.class.getClassLoader().getResource("")
					.toURI().getPath()
					+ "pid.xml";
			File file = new File(path);
			SAXReader saxReader = new SAXReader();
			Document document = null;
			document = saxReader.read(file);
			Element root = document.getRootElement();
			List<Element> listnode = root.elements();
			for (int i = 0; i < listnode.size(); i++) {
				String name = listnode.get(i).getName();
				if (name.equals(nodeName)) {
					listnode.get(i).setText(pid + "");
					break;
				}
			}
			XMLWriter writer = new XMLWriter(new FileOutputStream(path));
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			logger.error(e);
		}

	}

	public void readXML(String nodeName) {
		try {
			String path = XmlUtil.class.getClassLoader().getResource("")
					.toURI().getPath()
					+ "pid.xml";
			File file = new File(path);
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(file);
			Element root = document.getRootElement();
			Iterator<Element> iterator = root.elementIterator();
			while (iterator.hasNext()) {
				Element recordEless = (Element) iterator.next();
				if (!recordEless.getName().equals(nodeName)) {
					pidName = recordEless.getText();
				}
			}

		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void monitorWatch() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {

				boolean isExit = checkArcitleTime();

				if (isExit) {
					if (!pidName.isEmpty()) {
						ProgressReader.skillProcess(Integer.parseInt(pidName));
					}
					ProgressReader.startBatch(batName);
					try {
						Thread.sleep(20 * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					readXML(nodeName);
				}
			}

		}, 0, 60000);

	}

	public boolean checkArcitleTime() {
		boolean isExit = false;
		if (pidName.isEmpty()) {
		} else {
			List<ArticleInfo> articleInfos = new ArticleInfoReader()
					.readArticleInfoByTask(2);
			Date date = articleInfos.get(0).getCrawlTime();
			long hours = date.getTime();
			Date nowTime = new Date();
			long nowHours = nowTime.getTime();
			long temp = nowHours - hours;
			if (temp > 2 * 60 * 60 * 1000) {
				isExit = true;
			}

		}
		return isExit;
	}
}
