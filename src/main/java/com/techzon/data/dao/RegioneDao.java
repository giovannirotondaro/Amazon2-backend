package com.techzon.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techzon.data.entities.Regione;

@Repository
public interface RegioneDao extends JpaRepository<Regione,Long> {
	List<Regione> findAll();
}
