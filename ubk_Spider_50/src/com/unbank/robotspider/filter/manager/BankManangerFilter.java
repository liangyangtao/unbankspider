package com.unbank.robotspider.filter.manager;

import java.util.Set;

import com.unbank.robotspider.UnbankCrawler;
import com.unbank.robotspider.entity.BankManager;
import com.unbank.robotspider.entity.NewsInfoMiddleWare;

public class BankManangerFilter {
	// BankManagerLoader bankManagerLoader =
	// UnbankCrawler.getBankManagerLoader();
	BankManagerLoader bankManagerLoader;

	/***
	 * 注意：
	 * 
	 * 
	 * 这个类的功能去掉了
	 * 
	 * @param newsInfoMiddleWare
	 */

	public void findManager(NewsInfoMiddleWare newsInfoMiddleWare) {
		String content = newsInfoMiddleWare.getText();
		Set<BankManager> bankManagers = bankManagerLoader.getBankManagers();
		for (BankManager bankManager : bankManagers) {
			if (content.contains(bankManager.getBankName())
					&& content.contains(bankManager.getManagerName())) {
				String temp = newsInfoMiddleWare.getCrawlTitle();
				newsInfoMiddleWare.setCrawlTitle("["
						+ bankManager.getBankName() + ":"
						+ bankManager.getManagerName() + "]" + temp);
				newsInfoMiddleWare.setWebsiteId(Integer.parseInt(bankManager
						.getCategory()));
				break;
			}

		}
	}

}
