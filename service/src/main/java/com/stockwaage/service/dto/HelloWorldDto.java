package com.stockwaage.service.dto;

import io.dropwizard.Configuration;

public class HelloWorldDto  {
	
	private String txt;

	public HelloWorldDto(String txt){
		this.txt = txt;
	}
	
	public String getTxt(){
		return txt;
	}
	
}
