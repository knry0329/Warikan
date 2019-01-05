package com.knr.warikan.form;

import java.util.List;

import javax.validation.constraints.Size;

public class YenByPersonForm {

	@Size(min = 1, max = 10)
	private String name;
	@Size(min = 1, max = 20)
	private List<String> use;
	@Size(min = 1, max = 20)
	private List<Integer> yen;
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getUse() {
		return use;
	}
	public void setUse(List<String> use) {
		this.use = use;
	}
	public List<Integer> getYen() {
		return yen;
	}
	public void setYen(List<Integer> yen) {
		this.yen = yen;
	}
	
}
