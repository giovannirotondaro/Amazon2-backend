package com.techzon.data.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.techzon.data.entities.Provincia;
import com.techzon.data.entities.Regione;

@Repository
public interface ProvinciaDao extends JpaRepository<Provincia, Long>,JpaSpecificationExecutor<Provincia> {
	
	List<Provincia> findAll();
	
	default Specification<Provincia> theLast(String names){
		return (Root<Provincia> root,CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)->{
			Join<Provincia, Regione> tab1=root.join("regione");
			return tab1.get("nome").in(names);
		};
	}	
}
