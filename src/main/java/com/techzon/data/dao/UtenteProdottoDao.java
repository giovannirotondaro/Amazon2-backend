package com.techzon.data.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techzon.data.entities.Corriere;
import com.techzon.data.entities.User;
import com.techzon.data.entities.UtenteProdotto;
import com.techzon.data.entities.UtenteProdotto.Stato;

@Repository
public interface UtenteProdottoDao extends JpaRepository<UtenteProdotto, Long>{
	
	List<UtenteProdotto> findAllByUser(User user);
	
	List<UtenteProdotto> findAllByStato(Stato stato);
	
	List<UtenteProdotto> findAllByCorriere(Corriere corriere);
	
	Optional<UtenteProdotto> findById(Long id);
	
	@Query("FROM UTENTE_PRODOTTO u WHERE u.prodotto.id =:id")
	Optional<UtenteProdotto>  trovaOrdine(@Param("id")Long id);
}
