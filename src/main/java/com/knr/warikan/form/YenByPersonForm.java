package com.knr.warikan.form;

import java.util.ArrayList;

import javax.validation.constraints.Size;

public class YenByPersonForm {

	@Size(min = 1, max = 10)
	private String name;
	@Size(min = 1, max = 20)
	private ArrayList<String> use;
	@Size(min = 1, max = 20)
	private ArrayList<Integer> yen;
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getUse() {
		return use;
	}
	public void setUse(ArrayList<String> use) {
		this.use = use;
	}
	public ArrayList<Integer> getYen() {
		return yen;
	}
	public void setYen(ArrayList<Integer> yen) {
		this.yen = yen;
	}
	
}
