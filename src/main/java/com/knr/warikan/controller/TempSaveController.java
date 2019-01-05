package com.knr.warikan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.knr.warikan.entity.TExpenceEntity;
import com.knr.warikan.entity.TExpenceKeyEntity;
import com.knr.warikan.form.FlgForm;
import com.knr.warikan.form.YenByPersonForm;
import com.knr.warikan.form.YenByPersonListForm;
import com.knr.warikan.repositories.TExpenceRepository;

@Controller
public class TempSaveController {
	
	@Autowired
	HttpSession session;
	@Autowired
	TExpenceRepository repository;
	
	@ResponseBody
	@RequestMapping(value = "/tmpsave", method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public FlgForm result(@ModelAttribute @Validated YenByPersonListForm form, BindingResult bindingResult, Model model) {
	
		List<TExpenceEntity> tExpenceEntityList = new ArrayList<TExpenceEntity>();
		int rowId = 1;
		for(YenByPersonForm personForm : form.getYenByPersonForm()) {
			TExpenceEntity tExpenceEntity = new TExpenceEntity();
			TExpenceKeyEntity tExpenceKeyEntity = new TExpenceKeyEntity();
			for (int i=0; i<personForm.getYen().size(); i++) {
				tExpenceKeyEntity.setUser_id((String) session.getAttribute("user"));
				tExpenceKeyEntity.setRow_id(rowId);
				tExpenceEntity.setKey(tExpenceKeyEntity);
//				tExpenceEntity.setUser_id((String) session.getAttribute("user"));
//				tExpenceEntity.setRow_id(rowId);
				tExpenceEntity.setPay_person(personForm.getName());
				tExpenceEntity.setPurpose(personForm.getUse().get(i));
				tExpenceEntity.setExpence(personForm.getYen().get(i));
				tExpenceEntityList.add(tExpenceEntity);
				repository.saveAndFlush(tExpenceEntity);
				rowId++;
			}
		}
		
		FlgForm flgForm = new FlgForm();
		flgForm.setFlg(false);
		model.addAttribute(flgForm);

		return flgForm;
	}
}
