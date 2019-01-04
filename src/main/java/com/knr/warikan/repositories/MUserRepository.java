package com.knr.warikan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knr.warikan.entity.MUserEntity;

@Repository
public interface MUserRepository extends JpaRepository<MUserEntity, Long> {

}
