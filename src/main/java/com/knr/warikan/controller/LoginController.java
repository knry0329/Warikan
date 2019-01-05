package com.knr.warikan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knr.warikan.entity.MUserEntity;
import com.knr.warikan.form.LoginForm;
import com.knr.warikan.repositories.MUserRepository;

@Controller
public class LoginController {

	@Autowired
	MUserRepository repository;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute LoginForm form) {
		
		Iterable<MUserEntity> list = repository.findAll();

		return "index";
	}
}
