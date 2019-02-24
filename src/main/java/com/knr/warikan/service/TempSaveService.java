package com.knr.warikan.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knr.warikan.entity.TExpenceEntity;
import com.knr.warikan.entity.TExpenceKeyEntity;
import com.knr.warikan.form.YenByPersonForm;
import com.knr.warikan.form.YenByPersonListForm;
import com.knr.warikan.repositories.TExpenceRepository;


@Service
public class TempSaveService {
	
	@Autowired
	HttpSession session;
	@Autowired
	TExpenceRepository repository;

	public boolean tempSave(YenByPersonListForm form) {
		Object obj = session.getAttribute("user");
		if(obj == null) {
			return false;
		}
		String userId = (String)obj;
		repository.deleteByKeyUserId(userId);
		
		List<TExpenceEntity> tExpenceEntityList = new ArrayList<TExpenceEntity>();
		int rowId = 1;
		for(YenByPersonForm personForm : form.getYenByPersonForm()) {
			TExpenceEntity tExpenceEntity = new TExpenceEntity();
			TExpenceKeyEntity tExpenceKeyEntity = new TExpenceKeyEntity();
			for (int i=0; i<personForm.getYen().size(); i++) {
				tExpenceKeyEntity.setUserId(userId);
				tExpenceKeyEntity.setRowId(rowId);
				tExpenceEntity.setKey(tExpenceKeyEntity);
				tExpenceEntity.setPay_person(personForm.getName());
				tExpenceEntity.setPurpose(personForm.getUse().get(i));
				tExpenceEntity.setExpence(personForm.getYen().get(i));
				tExpenceEntityList.add(tExpenceEntity);
				repository.saveAndFlush(tExpenceEntity);
				rowId++;
			}
		}
		return true;
	}
	
	public YenByPersonListForm restore() {
		YenByPersonListForm form = new YenByPersonListForm();
		String userId = (String) session.getAttribute("user");
		List<TExpenceEntity> entityList = new ArrayList<TExpenceEntity>();
		entityList = repository.findByKeyUserId(userId);
		List<TExpenceEntity> sortedList = entityList.stream().sorted(new Comparator<TExpenceEntity>() {
			
			@Override
			public int compare(TExpenceEntity t1, TExpenceEntity t2) {
				return t1.getKey().getUserId().compareTo(t2.getKey().getUserId());
			}
			
		}).collect(Collectors.toList());
		
		List<Integer> perRowList = new ArrayList<Integer>();
		String tmpName = "";
		int count=0;
		int allCount = 0;
		for(TExpenceEntity te : sortedList) {
			if(count==0) {
				tmpName = te.getPay_person();
				count++;
			} else {
				if(te.getPay_person().equals(tmpName)) {
					count++;
				} else {
					perRowList.add(count);
					count=0;
					tmpName = te.getPay_person();
					count++;
				}
			}
			allCount++;
			if(allCount == sortedList.size()) {
				perRowList.add(count);
			}
		}
		List<YenByPersonForm> tmpListForm = new ArrayList<YenByPersonForm>();
		int isum = 0;
		for(int i : perRowList) {
			int isumBef = isum;
			isum = isumBef + i;
			YenByPersonForm tmpForm = new YenByPersonForm();
			List<String> useList = new ArrayList<String>();
			List<Integer> yenList  = new ArrayList<Integer>();
			tmpForm.setName(sortedList.get(isumBef).getPay_person());
			for(int j=isumBef; j<isum; j++) {
				useList.add(sortedList.get(j).getPurpose());
				yenList.add(sortedList.get(j).getExpence());
			}
			tmpForm.setUse(useList);
			tmpForm.setYen(yenList);
			tmpListForm.add(tmpForm);
		}
		form.setYenByPersonForm(tmpListForm);
		return form;
		
	}

}
