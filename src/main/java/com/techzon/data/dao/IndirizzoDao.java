package com.techzon.data.dao;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.techzon.data.entities.Indirizzo;
import com.techzon.data.entities.User;

@Repository
public interface IndirizzoDao extends JpaRepository<Indirizzo, Long>{
	
	boolean existsByUser(User user);
	
	Indirizzo findByUser(User user);
	
	@Query("select max(i.id) from INDIRIZZO as i")
	Long findByMax();
	
	Optional<Indirizzo> findById(Long id);
}
