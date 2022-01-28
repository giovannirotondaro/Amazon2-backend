package com.techzon.data.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techzon.data.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	Optional<User> findById(Long id);
	
	List<User> findAll();
}
