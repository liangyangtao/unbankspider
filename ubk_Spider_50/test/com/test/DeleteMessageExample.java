package com.test;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class DeleteMessageExample {
	public static void main(String args[]) throws Exception {
		String host = "10.0.1.182";
		String username = "liangyangtao@unbank.info";
		String password = "123456";
		Session session = Session.getInstance(System.getProperties(), null);
		Store store = session.getStore("pop3");
		store.connect(host, username, password);
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_WRITE);
		Message message[] = folder.getMessages();
		for (int i = 0, n = message.length; i < n; i++) {
			message[i].setFlag(Flags.Flag.DELETED, true);
		}
		folder.close(true);
		store.close();
	}
}