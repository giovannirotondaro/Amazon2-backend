package com.techzon.data.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techzon.data.entities.Prodotto;
import com.techzon.data.entities.User;

@Repository
public interface ProdottoDao extends JpaRepository<Prodotto, Long>{
	
	List<Prodotto> findAll();
	
	Optional<Prodotto> findById(Long id);
	
	List<Prodotto> findAllByCategoria(String categoria);
	
	List<Prodotto> findAllByTitolo(String titolo);
	
	List<Prodotto> findByVendutoDa(User vendutoDa);
} 
