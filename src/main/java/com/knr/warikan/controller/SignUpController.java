package com.knr.warikan.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knr.warikan.entity.MUserEntity;
import com.knr.warikan.form.ErrorForm;
import com.knr.warikan.form.FlgForm;
import com.knr.warikan.form.LoginForm;
import com.knr.warikan.repositories.MUserRepository;

@Controller
public class SignUpController extends BaseController {

	@Autowired
	MUserRepository repository;
	@Autowired
	HttpSession session;
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public String signup(@ModelAttribute("formModel") MUserEntity mUser, HttpServletRequest request, Model model) {
		
		if(!originHeaderCheck(request)) {
			ErrorForm eForm = new ErrorForm();
			eForm.setMsg("異なるドメインからのアクセスの可能性があります。");
			model.addAttribute("errorForm",eForm);
			return "error";
		}
		
		repository.saveAndFlush(mUser);
		session.setAttribute("user", mUser.getUser_id());
		return "index";
	}

	@ResponseBody
	@RequestMapping(value = "/signupCheck", method = RequestMethod.POST)
	public FlgForm signupCheck(@ModelAttribute LoginForm form, Model model) {
		
		Iterable<MUserEntity> list = repository.findAll();
		
		FlgForm flgForm = new FlgForm();
		flgForm.setFlg(true);
		for(MUserEntity mUser: list) {
			if(mUser.getUser_id().equals(form.getUser_id())) {
				flgForm.setFlg(false);
			}
		}
		model.addAttribute(flgForm);
		return flgForm;
	}
}
