package com.techzon.data.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techzon.data.entities.Carrello;
import com.techzon.data.entities.Prodotto;
import com.techzon.data.entities.ProdottoCarrello;

@Repository
public interface ProdottoCarrelloDao extends JpaRepository<ProdottoCarrello, Long>{
	
	List<ProdottoCarrello> findAllByCarrello(Carrello carrello);
	
	Optional<ProdottoCarrello> findById(Long id);
	
	ProdottoCarrello findByCarrelloAndProdotto(Carrello carrello, Prodotto prodotto);
	
	boolean existsByCarrelloAndProdotto(Carrello carrello, Prodotto prodotto);
	
	void deleteByCarrelloAndProdotto(Carrello carrello, Prodotto prodotto);
}
