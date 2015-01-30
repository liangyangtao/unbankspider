package com.unbank.duplicate;

import java.util.Timer;
import java.util.TimerTask;

public class DuplicateConsole {

	public static void main(String[] args) {

		Similarity.loaddata();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Similarity.pancheng();

			}
		}, 10000, 60000);
	}
}
