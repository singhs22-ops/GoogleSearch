package com.googleSearchEngine.googleSearchEngine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Service
public class FetchServiceLogicImpl implements FetchServiceLogic {

	@Autowired
	private JdbcTemplate jdbc;
	
	List<String> keysInDatabase = new ArrayList<>();
	
	

	@Override
	public List<String> fetchFromDatabase() {
		
		try {
			String query = "select distinct searchkey FROM onemat.custom_search";
			List<String> queryForList = jdbc.queryForList(query, String.class);
			keysInDatabase = queryForList;
			return queryForList;
	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return keysInDatabase;
		
	}



	@Override
	public void updateIntoDatabase(String key) {
		
		Integer queryCount = 1;
			try {
				//fetchDao.insertDistinctSearchedKey(key);
				if(keysInDatabase.contains(key)) {
					queryCount = jdbc.queryForObject("SELECT count FROM onemat.custom_search\r\n"
							+ "where searchkey = '"+key+"'", Integer.class);
					queryCount = queryCount+1;
					System.out.println(queryCount);
					String updateQuery = "UPDATE onemat.custom_search SET  count="+ queryCount +" WHERE searchkey = '"+key+"';";
					Integer updated = jdbc.update(updateQuery);
					System.out.println(updateQuery+"KEYS UPDATED"+ updated);
				}
				else {
					String query = "INSERT INTO onemat.custom_search (searchkey,count)\r\n"
							+ "VALUES ('"+ key+"',"+ queryCount +");";
						int update = jdbc.update(query);
						System.out.println("KEYS INSERTED"+ update);
						
				}				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		
		
	}



	@Override
	public List<String> fetchTopDatabase() {
		// TODO Auto-generated method stub
		return jdbc.queryForList("select searchkey from onemat.custom_search order by count desc limit 3;", String.class);
	}

	
}
