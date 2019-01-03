package com.knr.warikan.form;

import java.util.ArrayList;

import javax.validation.Valid;

public class YenByPersonListForm {

	@Valid
	private ArrayList<YenByPersonForm> yenByPersonForm;

	public ArrayList<YenByPersonForm> getYenByPersonForm() {
		return yenByPersonForm;
	}

	public void setYenByPersonForm(ArrayList<YenByPersonForm> yenByPersonForm) {
		this.yenByPersonForm = yenByPersonForm;
	}

}
