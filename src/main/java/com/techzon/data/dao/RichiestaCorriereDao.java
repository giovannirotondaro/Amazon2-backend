package com.techzon.data.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techzon.data.entities.RichiestaCorriere;

@Repository
public interface RichiestaCorriereDao extends JpaRepository<RichiestaCorriere, Long>{
	Optional<RichiestaCorriere> findById(Long id);
	RichiestaCorriere findByEmail(String email);
	List<RichiestaCorriere> findAllByhannoColloquio(boolean hannoColloquio);
	
}
