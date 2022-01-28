package com.techzon.data.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techzon.data.entities.Carrello;
import com.techzon.data.entities.User;

@Repository
public interface CarrelloDao extends JpaRepository<Carrello, Long>{
	
	Carrello findByUser(User user);
	
	Optional<Carrello> findById(Long id);
	
	@Query("select max(c.id) from CARRELLO as c")
	Long findByMax();
}
