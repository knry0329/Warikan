package com.knr.warikan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knr.warikan.entity.TExpenceEntity;
import com.knr.warikan.entity.TExpenceKeyEntity;

@Repository
public interface TExpenceRepository extends JpaRepository<TExpenceEntity, TExpenceKeyEntity> {

	public void deleteByKeyUserId(String userId);
	public List<TExpenceEntity> findByKeyUserId(String userId);
}
