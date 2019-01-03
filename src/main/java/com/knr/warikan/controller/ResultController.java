package com.knr.warikan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knr.warikan.form.ResultForm;
import com.knr.warikan.form.YenByPersonListForm;
import com.knr.warikan.service.WarikanService;

@Controller
public class ResultController {
	
	@Autowired
	WarikanService service;
	
	@ResponseBody
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public ResultForm result(@ModelAttribute @Validated YenByPersonListForm form, BindingResult bindingResult, Model model) {
		
		System.out.println("**************fordeBug");
		
		ResultForm resultForm = service.warikanLogic(form);
		model.addAttribute(resultForm);

		System.out.println("**************fordeBug");

//		return "index";
		return resultForm;
	}
}
