package com.unbank.robotspider.worker;

import java.util.List;

import com.unbank.robotspider.entity.WebSiteInfo;
import com.unbank.robotspider.store.WebSiteInfoReader;

public class WebSiteInfoProductor {

	public List<WebSiteInfo> getWebSiteInfoList(Integer num) {
		return new WebSiteInfoReader().readWebSiteInfoList(num);
	}

	public List<WebSiteInfo> getWebSiteInfoByRank(Integer task,
			Integer timeInterval) {
		return new WebSiteInfoReader().getWebSiteInfoByRank(task, timeInterval);
	}

	public List<WebSiteInfo> getWebSiteInfoByTask(Integer task) {
		
		return new WebSiteInfoReader().getWebSiteInfoByTask(task);
	}

}
