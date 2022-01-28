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

import com.techzon.data.entities.Citta;
import com.techzon.data.entities.Provincia;

@Repository
public interface CittaDao extends JpaRepository<Citta, Long>,JpaSpecificationExecutor<Citta>{
	
	List<Citta> findAll();

	default Specification<Citta> theLast(String names){
		return (Root<Citta> root,CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)->{
			Join<Citta, Provincia> tab1=root.join("provincia");
			return tab1.get("nome").in(names);
		};
	}
}
