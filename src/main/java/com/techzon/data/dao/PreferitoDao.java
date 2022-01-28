package com.techzon.data.dao;

import com.techzon.data.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PreferitoDao extends JpaRepository<Preferito, Long>{
	
	List<Preferito> findAllByUser(User user);
	
	Preferito findByProdotto(Prodotto prodotto);
}
