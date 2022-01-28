package com.techzon.data.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techzon.data.entities.Prodotto;
import com.techzon.data.entities.Recensione;
import com.techzon.data.entities.User;

@Repository
public interface RecensioneDao extends JpaRepository<Recensione,Long> {
	
	List<Recensione> findByCreataDa(User creataDa);
	
	List<Recensione> findAllByProdottoRecensito(Prodotto p);
	
	List<Recensione> findAllByApprovata(boolean approvata);
}

