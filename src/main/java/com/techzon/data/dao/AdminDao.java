package com.techzon.data.dao;

import org.springframework.stereotype.Repository;
import com.techzon.data.entities.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AdminDao extends JpaRepository<Admin,Long> {
	
	Admin findByUsername(String username);	
}
