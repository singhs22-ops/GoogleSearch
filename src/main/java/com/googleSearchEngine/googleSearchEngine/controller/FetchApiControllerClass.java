package com.googleSearchEngine.googleSearchEngine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.googleSearchEngine.googleSearchEngine.service.FetchServiceLogic;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@RestController
@Slf4j
@RequestMapping("/google-search-engine/v1")
public class FetchApiControllerClass {

	@Autowired
	FetchServiceLogic fetchService;
	
	Integer i = 0;
	@GetMapping("/ping")
	public ResponseEntity<Object> ping(){
		System.out.println("PING Successfull");
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping(value = "/searchvalue/Keys", produces = "application/json")
	//@GetMapping("/searchvalue/Keys")
	public List<String> getAllSearchedKey(){
			System.out.println("getAllSearchedKey"+ fetchService.fetchFromDatabase());
			i++;
			fetchService.updateIntoDatabase("china"+ i.toString());
		 return fetchService.fetchFromDatabase();
		
	}
	
	@GetMapping(value = "/searchvalue/topKeys", produces = "application/json")
	public List<String> getTopSearchedKey(){
			System.out.println("getTopSearchedKey"+ fetchService.fetchTopDatabase());
			return fetchService.fetchTopDatabase();
		
	}
	
	@PostMapping("/updateValue/{key}")
	public ResponseEntity<Object> updateCreateIntoEntity(@PathVariable("key") String key) {
		try {
			System.out.println("updateCreateIntoEntity");
			fetchService.updateIntoDatabase(key);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	
}
