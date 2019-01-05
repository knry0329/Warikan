package com.knr.warikan.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knr.warikan.form.LoginForm;

@Controller
public class LogoutController {

	@Autowired
	HttpSession session;
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(@ModelAttribute LoginForm form) {
		
		session.removeAttribute("user");
		session.invalidate();

		return "index";
	}
}
