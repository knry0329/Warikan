package com.knr.warikan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.knr.warikan.form.PayByPerson;
import com.knr.warikan.form.ResultForm;
import com.knr.warikan.form.YenByPersonForm;
import com.knr.warikan.form.YenByPersonListForm;

@Service
public class WarikanService {
	public ResultForm warikanLogic(YenByPersonListForm form) {
		
		ResultForm resultForm = new ResultForm();
		
		//formから入力情報を取得
//		//yenがNULLだったら0に置換
//		for(YenByPersonForm f : form.getYenByPersonForm()) {
//		}
		//人数を取得
		int nop = form.getYenByPersonForm().size();
		
		//参加者ごとに支払った金額を算出
//		List<Integer> sum = new ArrayList<Integer>();
		int sumByPerson[] = new int[nop];
		int allSum = 0;
		int count = 0;
		for(YenByPersonForm yenByPersonForm : form.getYenByPersonForm()) {
			for(int yen : yenByPersonForm.getYen()) {
				sumByPerson[count] = sumByPerson[count] + yen;
				allSum = allSum + yen;
			}
			count++;
		}
		
		//平均を取得
		int ave = allSum / nop;
		
		//各参加者がもらうべき費用(+ならもらうべき金額、-なら支払うべき金額）
		int gettableYen[] = new int[nop];
		for (int i = 0; i < gettableYen.length; i++) {
			gettableYen[i] = sumByPerson[i] - ave;
		}
		
		List<PayByPerson> payByPersonList = new ArrayList<PayByPerson>(); 
		
		for (int i = 0; i < nop; i++) {
			PayByPerson payByPerson = new PayByPerson();
			Map<String, Integer> yenByTargetName = new HashMap<String, Integer>();
			//支払われる参加者ごとにループ
			if(gettableYen[i] > 0) {
				payByPerson.setName(form.getYenByPersonForm().get(i).getName());
				//支払う参加者を取得
				for (int j = 0; j < nop; j++) {
					if(gettableYen[j] < 0) {
						if(gettableYen[i] + gettableYen[j] > 0) {
							//支払わなければいけないすべての金額を支払う場合
							yenByTargetName.put(form.getYenByPersonForm().get(j).getName(), - gettableYen[j]);
							gettableYen[i] = gettableYen[i] + gettableYen[j];
							gettableYen[j] = 0;
						} else {
							//支払わなければいけないうち一部の金額を支払う場合
							yenByTargetName.put(form.getYenByPersonForm().get(j).getName(), gettableYen[i]);
							gettableYen[j] = gettableYen[i] + gettableYen[j];
							gettableYen[i] = 0;
						}
					}
					if(gettableYen[i] == 0) {
						break;
					}
				}
				payByPerson.setYenByTargetName(yenByTargetName);
				payByPersonList.add(payByPerson);
			}
		}
		
		resultForm.setPayByPersonList(payByPersonList);
		
		return resultForm;
	}
}
