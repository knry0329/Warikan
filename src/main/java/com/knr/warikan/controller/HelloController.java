package com.knr.warikan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.knr.warikan.entity.MUserEntity;
import com.knr.warikan.repositories.MUserRepository;

@Controller
public class HelloController {

	@Autowired
	MUserRepository repository;
	
	@RequestMapping("/")
	public String hello() {
		
		Iterable<MUserEntity> list = repository.findAll();

		return "index";
	}
}
