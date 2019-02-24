package com.knr.warikan.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="t_expence")
public class TExpenceEntity {
	
	void TexpenceEntity() {}

	@EmbeddedId
	private TExpenceKeyEntity key;

	@Column(name = "pay_person", nullable = true)
	private String pay_person;

	@Column(name = "purpose", nullable = true)
	private String purpose;

	@Column(name = "expence", nullable = true)
	private Integer expence;

	public TExpenceKeyEntity getKey() {
		return key;
	}

	public void setKey(TExpenceKeyEntity key) {
		this.key = key;
	}

	public String getPay_person() {
		return pay_person;
	}

	public void setPay_person(String pay_person) {
		this.pay_person = pay_person;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Integer getExpence() {
		return expence;
	}

	public void setExpence(Integer expence) {
		this.expence = expence;
	}


}
