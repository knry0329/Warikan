package com.knr.warikan.form;

import java.util.Map;

public class PayByPerson {

	private String name;
//	private ArrayList<String> targetName;
//	private ArrayList<Integer> yen;
	private Map<String, Integer> yenByTargetName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public ArrayList<String> getTargetName() {
//		return targetName;
//	}
//	public void setTargetName(ArrayList<String> targetName) {
//		this.targetName = targetName;
//	}
//	public ArrayList<Integer> getYen() {
//		return yen;
//	}
//	public void setYen(ArrayList<Integer> yen) {
//		this.yen = yen;
//	}
	public Map<String, Integer> getYenByTargetName() {
		return yenByTargetName;
	}
	public void setYenByTargetName(Map<String, Integer> yenByTargetName) {
		this.yenByTargetName = yenByTargetName;
	}
}
