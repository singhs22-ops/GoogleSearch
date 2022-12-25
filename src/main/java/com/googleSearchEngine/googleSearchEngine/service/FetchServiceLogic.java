package com.googleSearchEngine.googleSearchEngine.service;

import java.util.List;

public interface FetchServiceLogic {
	
	public List<String> fetchFromDatabase();
	
	public void updateIntoDatabase(String key);
	
	public List<String> fetchTopDatabase();
	

}
