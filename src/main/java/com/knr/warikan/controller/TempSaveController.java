package com.knr.warikan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knr.warikan.form.FlgForm;
import com.knr.warikan.form.YenByPersonListForm;
import com.knr.warikan.service.TempSaveService;

@Controller
public class TempSaveController {
	
	@Autowired
	TempSaveService service;
	
	@ResponseBody
	@RequestMapping(value = "/tmpsave", method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public FlgForm result(@ModelAttribute @Validated YenByPersonListForm form, BindingResult bindingResult, Model model) {
	
		boolean flg = service.tempSave(form);
		
		FlgForm flgForm = new FlgForm();
		flgForm.setFlg(flg);
		if(!flg) {
			flgForm.setMsg("セッションタイム・アウトしました。");
		}
		model.addAttribute(flgForm);

		return flgForm;
	}
}
