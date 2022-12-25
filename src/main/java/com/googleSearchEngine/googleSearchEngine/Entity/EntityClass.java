package com.googleSearchEngine.googleSearchEngine.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "custom_search", schema = "onemat")
public class EntityClass {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "id")
	private Integer Id;
	
	@Column(name= "searchkey")
	private String searchKey;
	
	
	@Column(name= "count")
	private Integer count;
	
}
