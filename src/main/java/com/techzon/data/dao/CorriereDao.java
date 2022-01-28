package com.techzon.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techzon.data.entities.Corriere;

@Repository
public interface CorriereDao extends JpaRepository<Corriere, Long> {
	
	Corriere findByUsername(String username);
	Corriere findByEmail(String email);
	
}
