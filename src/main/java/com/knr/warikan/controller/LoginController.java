package com.knr.warikan.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knr.warikan.entity.MUserEntity;
import com.knr.warikan.form.FlgForm;
import com.knr.warikan.form.LoginForm;
import com.knr.warikan.repositories.MUserRepository;

@Controller
public class LoginController {

	@Autowired
	MUserRepository repository;
	@Autowired
	HttpSession session;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute LoginForm form) {
		
//		Iterable<MUserEntity> list = repository.findAll();
		session.setAttribute("user", form.getUser_id());

		return "index";
	}

	@ResponseBody
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public FlgForm loginCheck(@ModelAttribute LoginForm form, Model model) {
		
		FlgForm flgForm = new FlgForm();
		flgForm.setFlg(false);
		
		Optional<MUserEntity> entity = repository.findById(form.getUser_id());
		
		if(entity.isPresent()) {
			if(entity.get().getPassword().equals(form.getPassword())) {
				flgForm.setFlg(true);
			}
		}
		
		model.addAttribute(flgForm);
		return flgForm;
	}

}
