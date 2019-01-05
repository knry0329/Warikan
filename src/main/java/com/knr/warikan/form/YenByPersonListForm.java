package com.knr.warikan.form;

import java.util.List;

import javax.validation.Valid;

public class YenByPersonListForm {

	@Valid
	private List<YenByPersonForm> yenByPersonForm;

	public List<YenByPersonForm> getYenByPersonForm() {
		return yenByPersonForm;
	}

	public void setYenByPersonForm(List<YenByPersonForm> yenByPersonForm) {
		this.yenByPersonForm = yenByPersonForm;
	}

}
