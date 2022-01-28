package com.techzon.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techzon.data.entities.SegnalazioneUtente;
import com.techzon.data.entities.User;

@Repository
public interface SegnalazioneUtenteDao extends JpaRepository<SegnalazioneUtente, Long> {
	
	List<SegnalazioneUtente> findAll();
	
	List<SegnalazioneUtente> findByUtenteCheFaSegnalazione(User utenteCheFaSegnalazione);
	
	List<SegnalazioneUtente> findByUtenteSegnalato(User utenteSegnalato);
}
