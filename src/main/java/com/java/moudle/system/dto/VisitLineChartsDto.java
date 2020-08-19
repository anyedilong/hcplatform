package com.java.moudle.system.dto;

import java.io.Serializable;

public class VisitLineChartsDto implements Serializable {
   
	private static final long serialVersionUID = 121346854234L;
	
    private String name;
    private String num;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
    
}
