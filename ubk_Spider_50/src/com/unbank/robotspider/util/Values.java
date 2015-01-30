package com.unbank.robotspider.util;

public class Values {

	public String IMAGEHOST;
	public static Values v;

	public void init() {
		v = this;
		v.IMAGEHOST = this.IMAGEHOST;
	}

	public String getIMAGEHOST() {
		return IMAGEHOST;
	}

	public void setIMAGEHOST(String iMAGEHOST) {
		IMAGEHOST = iMAGEHOST;
	}

}
