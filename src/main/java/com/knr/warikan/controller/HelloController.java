package com.knr.warikan.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.knr.warikan.form.YenByPersonListForm;
import com.knr.warikan.repositories.MUserRepository;
import com.knr.warikan.service.TempSaveService;

@Controller
public class HelloController {

	@Autowired
	MUserRepository repository;
	@Autowired
	HttpSession session;
	@Autowired
	TempSaveService service;

	@RequestMapping("/")
	public String hello(Model model) {
		
	Object obj = session.getAttribute("user");

	if(obj!=null) {
		YenByPersonListForm resultForm = service.restore();
		model.addAttribute("personInfo", resultForm);
		model.addAttribute("personInfoCnt", resultForm.getYenByPersonForm().size());
	}
		return "index";
	}
}
