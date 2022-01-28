package com.techzon.data.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techzon.data.entities.CartaCredito;


@Repository
public interface CartaCreditoDao extends JpaRepository<CartaCredito, Long>{
	
	List<CartaCredito> findAll();
	
	boolean existsByNumeroCarta(String numeroCarta);
	
	CartaCredito findByNumeroCarta(String numeroCarta);
	
	void deleteById(Long id);
	
	Optional<CartaCredito> findById(Long id);
}
