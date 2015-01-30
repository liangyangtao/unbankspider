package com.unbank.robotspider.filter.manager;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import com.unbank.robotspider.entity.BankManager;

public class BankManagerLoader extends WordLorder {

	public static BankManagerLoader bankManagerLoader;

	public static BankManagerLoader getInstance() {
		if (bankManagerLoader == null) {
			bankManagerLoader = new BankManagerLoader();
			bankManagerLoader.read();
		}
		return bankManagerLoader;
	}

	public static Set<BankManager> bankManagers = new HashSet<BankManager>();

	public void read() {
		Set<String> bankwords = loadWords((InputStream) this.getClass()
				.getResourceAsStream("1711.txt"));
		for (String string : bankwords) {
			String[] temp = string.split("@@@");
			if (temp.length != 2) {
				continue;
			}
			String bankName = temp[0];
			String managerName = temp[1];
			BankManager bankManager = new BankManager();
			bankManager.setBankName(bankName);
			bankManager.setCategory("1711");
			bankManager.setManagerName(managerName);
			bankManagers.add(bankManager);
		}
	}

	public Set<BankManager> getBankManagers() {
		return bankManagers;
	}

	public void setBankManagers(Set<BankManager> bankManagers) {
		this.bankManagers = bankManagers;
	}
}
