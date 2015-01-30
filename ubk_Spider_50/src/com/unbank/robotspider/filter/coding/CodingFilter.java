package com.unbank.robotspider.filter.coding;

public interface CodingFilter {
	public boolean checkMessyCode(String source);

	public String getCharset(String url);
}
