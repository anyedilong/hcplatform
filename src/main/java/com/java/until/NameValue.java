package com.java.until;

import org.apache.http.NameValuePair;

public class NameValue implements NameValuePair{
	
	
	private String name;
	private String value;
	
	public NameValue(String name,String value) {
		  this.name=name;
		  this.value=value;
		
	}

	@Override
	public String getName() {
		
		return name;
	}

	@Override
	public String getValue() {
	
		return value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	

}
